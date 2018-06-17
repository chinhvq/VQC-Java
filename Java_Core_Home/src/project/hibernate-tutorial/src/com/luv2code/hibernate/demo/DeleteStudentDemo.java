package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			List<Student> students = session.createQuery("from Student s where s.lastName='Vuong'").list();
			for(Student student : students) {
				System.out.println("Deleting");
				System.out.println(student);
				session.delete(student);
			}			
			session.getTransaction().commit();
			
			System.out.println("==============================");
			displayRemainStudent(factory);
			
			System.out.println("==============================");
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("DELETE FROM Student WHERE email NOT LIKE '%foo@gmail.com'").executeUpdate();
			session.getTransaction().commit();
			
			System.out.println("==============================");
			displayRemainStudent(factory);
			System.out.println("Done");

		} finally {
			factory.close();
		}

	}

	private static void displayRemainStudent(SessionFactory factory) {
		Session session;
		List<Student> students;
		session = factory.getCurrentSession();
		session.beginTransaction();
		System.out.println("List of remain Student");
		students = session.createQuery("from Student").getResultList();
		students.forEach(student -> System.out.println(student));
		session.getTransaction().commit();
	}

}
