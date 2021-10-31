package spring.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LogBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
//	Around Advice와 달리 주 업무의 위치를 기입하지 않아도 된다
		System.out.println("Before --> 앞에서 실행될 로직");
	}

}
