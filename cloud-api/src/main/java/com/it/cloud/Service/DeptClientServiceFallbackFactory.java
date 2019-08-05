package com.it.cloud.Service;

import java.util.List;

import com.it.cloud.entity.Dept;
import org.springframework.stereotype.Component;
import feign.hystrix.FallbackFactory;

@Component // 不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>
{
	@Override
	public DeptClientService create(Throwable throwable)
	{
		return new DeptClientService() {

			@Override
			public boolean add(Dept dept)
			{
				return false;
			}

			@Override
			public Dept findById(Long dept) {
				return new Dept().setDeptno(dept).setDname("该ID：" + dept + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
						.setDbSource("no this database in MySQL");
			}

			@Override
			public List<Dept> findAll() {
				return null;
			}
		};
	}
}
