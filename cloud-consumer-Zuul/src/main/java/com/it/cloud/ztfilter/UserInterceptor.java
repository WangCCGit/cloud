package com.it.cloud.ztfilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//拦截器拦截
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("请求url={}", request.getRequestURL().toString());

		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			CheckToken methodAnnotation = method.getMethodAnnotation(CheckToken.class);
			if (methodAnnotation == null || !methodAnnotation.value()) {
				return true;
			}
			String ip = request.getHeader("X-Real-IP");
			log.info("ip-----:" + ip);
			//获取请求头 Access-Token对应的值  也就是tiken
			String token = request.getHeader(UcenterConstants.HEADER_ACCESS_TOKEN);
			// 获取请求头的 User-Id  用户的id
			String userId = request.getHeader(UcenterConstants.HEADER_USER_ID);
			log.info("token:{}", token);
			log.info("userId:{}", userId);
			// 解决service为null无法注入问题  获取bean
			BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
			RedisUtil redisUtil = (RedisUtil) factory.getBean("redisUtil");
			ITbUserSV userSV = (ITbUserSV) factory.getBean("tbUserSVImpl");

			//根据token 查看redis中是否存在
			Object obj = redisUtil.get(Constants.ACCESS_TOKEN_USER + token);

			// 根据token查询缓存 查看是否有会员信息
			if (obj != null) {
				// 验证token  转换为json字符串
                JSONObject tokenInfo = JSON.parseObject(obj.toString());

                //设置过期时间   获取accessToken字段
				redisUtil.set(Constants.ACCESS_TOKEN_USER + tokenInfo.getString("accessToken"), tokenInfo,
                        Constants.ACCESS_TOKEN_EXPIRE_TIME_OUT);
				if (userId != null) {
					//根据id查询数据库获取用户
					TbUser user = userSV.getByPrimaryKey(Long.valueOf(userId));
					if (user != null) {
						// 1.验证userId和token里的UserId是否相等
						// 2.检查用户是否禁用
						// 获取accessToken字段 获取userId
						String[] tokenString = tokenInfo.getString("accessToken").split("_");
						if (userId.equals(tokenString[0]) && "0".equals(user.getUserStatus())) {
							return true;
						} else {
							ajaxReturn(response, 4000, "用户禁用或登录过期");
							return false;
						}
					}
					ajaxReturn(response, 4000, "参数异常");
					return false;
				} else {
					ajaxReturn(response, 4000, "参数异常");
					return false;
				}
			} else {
				ajaxReturn(response, 4000, "当前登录状态过期，请您重新登录！");
				return false;
			}
		}
		return true;
	}

	public void ajaxReturn(HttpServletResponse response, int code, String msg) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		MessageResult<String> messageResult = new MessageResult<String>();
		messageResult.setReturnCode(code + "");
		messageResult.setReturnMessage(msg);
		messageResult.setObject("");
		out.print(JSONObject.toJSONString(messageResult));
		out.flush();
		out.close();
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {}
}
