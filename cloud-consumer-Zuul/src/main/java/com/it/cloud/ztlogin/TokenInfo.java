package com.it.cloud.ztlogin;

import com.it.cloud.ztfilter.Constants;
import lombok.Data;
import com.it.cloud.ztfilter.TbUser;

/**
 * @description: TokenInfo
 * @author: MrGao
 * @create: 2018/11/07 09:45
 */

@Data
public class TokenInfo {

	private String accessToken;

	private Integer expiresIn;

	private AuthUser authUser;

	private String version;

	public static TokenInfo getTokenInfo(TbUser user) {
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setAccessToken(TokenHelper.generateToken(user.getId()));
		tokenInfo.setExpiresIn(Constants.ACCESS_TOKEN_EXPIRE_TIME_OUT);
		tokenInfo.setVersion("1.0");
		tokenInfo.setAuthUser(AuthUser.toAuthUser(user));
		return tokenInfo;
	}
}
