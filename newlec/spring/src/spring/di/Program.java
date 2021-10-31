package spring.di;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;
import spring.di.ui.ExamConsole;

public class Program {

	public static void main(String[] args) {
// 1) 데이터를 가지는 클래스 - exam
// 2) 클래스의 출력을 가지는 클래스 - NewlecExam
		
		/* 스프링에게 지시하는 방법으로 구현
		Exam exam = new NewlecExam();
		
//		ExamConsole console = new InlineExamExamConsole(exam);
		// 생성자를 이용한 DI
		ExamConsole console = new GridExamConsole();
		
		// setter를 이용한 DI
		console.setExam(exam);
		*/
		
		ApplicationContext context =
				new AnnotationConfigApplicationContext(NewlecDIConfig.class);
				// new ClassPathXmlApplicationContext("spring/di/setting.xml");
 
		// Id보다 Class가 선호된다
		// Id로 가져오는 경우 - 캐스팅 필요
		ExamConsole console = (ExamConsole) context.getBean("console");
		
		//Exam exam = context.getBean(Exam.class);
		//System.out.println(exam.toString());
		
		// Class로 가져오는 경우 - 자료형명으로 가져오기
//		ExamConsole console = context.getBean(ExamConsole.class);
		console.print();
		 
//		List<Exam> exams = new ArrayList<>();
//		List<Exam> exams = (List<Exam>) context.getBean("exams");
		//exams.add(new NewlecExam(1,1,1,1));
//		for(Exam e : exams) System.out.println(e);
	}

}
