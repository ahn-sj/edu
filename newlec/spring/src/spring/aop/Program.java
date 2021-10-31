package spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.aop.entity.Exam;

public class Program {

	public static void main(String[] args) {
		ApplicationContext context =
				// new AnnotationConfigApplicationContext(NewlecDIConfig.class); // used annotation 
				 new ClassPathXmlApplicationContext("spring/aop/setting.xml");	 // used xml
		
		Exam exam = (Exam) context.getBean("exam");
		
		System.out.println("total is " + exam.total());
		System.out.println("avg is " + exam.avg());
		 
		
	}

}
