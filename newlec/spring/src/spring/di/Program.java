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
// 1) �����͸� ������ Ŭ���� - exam
// 2) Ŭ������ ����� ������ Ŭ���� - NewlecExam
		
		/* ���������� �����ϴ� ������� ����
		Exam exam = new NewlecExam();
		
//		ExamConsole console = new InlineExamExamConsole(exam);
		// �����ڸ� �̿��� DI
		ExamConsole console = new GridExamConsole();
		
		// setter�� �̿��� DI
		console.setExam(exam);
		*/
		
		ApplicationContext context =
				new AnnotationConfigApplicationContext(NewlecDIConfig.class);
				// new ClassPathXmlApplicationContext("spring/di/setting.xml");
 
		// Id���� Class�� ��ȣ�ȴ�
		// Id�� �������� ��� - ĳ���� �ʿ�
		ExamConsole console = (ExamConsole) context.getBean("console");
		
		//Exam exam = context.getBean(Exam.class);
		//System.out.println(exam.toString());
		
		// Class�� �������� ��� - �ڷ��������� ��������
//		ExamConsole console = context.getBean(ExamConsole.class);
		console.print();
		 
//		List<Exam> exams = new ArrayList<>();
//		List<Exam> exams = (List<Exam>) context.getBean("exams");
		//exams.add(new NewlecExam(1,1,1,1));
//		for(Exam e : exams) System.out.println(e);
	}

}
