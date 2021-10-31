package spring.aop.advice;

import org.springframework.aop.ThrowsAdvice;

public class LogAfterThrowingAdvice implements ThrowsAdvice {
	// � ���ܳĿ� ���� ������ �޶����� ������ 
	
	public void afterThrowing(IllegalArgumentException e ) throws Throwable {
		System.out.println("After throwing --> ���ܰ� �߻��Ͽ����ϴ�." + e.getMessage());
	}

}
