package spring.aop.advice;

import org.springframework.aop.ThrowsAdvice;

public class LogAfterThrowingAdvice implements ThrowsAdvice {
	// 어떤 예외냐에 따라 형식이 달라지기 때문에 
	
	public void afterThrowing(IllegalArgumentException e ) throws Throwable {
		System.out.println("After throwing --> 예외가 발생하였습니다." + e.getMessage());
	}

}
