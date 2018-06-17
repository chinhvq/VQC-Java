package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			System.out.println("==============================");
			System.out.println("All Student");
			List<Student> students = session.createQuery("from Student").getResultList();
			students.forEach(student -> System.out.println(student));
			System.out.println("==============================");
			System.out.println("All Student with last name is Vuong");
			students = session.createQuery("from Student s where s.lastName='Vuong'").getResultList();
			students.forEach(student -> System.out.println(student));
			System.out.println("==============================");
			System.out.println("All Student with last name is Vuong OR first name is Paul");
			students = session.createQuery("from Student s where s.lastName='Vuong' OR s.firstName='Paul'").getResultList();
			students.forEach(student -> System.out.println(student));
			System.out.println("==============================");
			System.out.println("All Student with email like @luv2code");
			students = session.createQuery("from Student s where s.email LIKE '%luv2code%'").getResultList();
			students.forEach(student -> System.out.println(student));
			session.getTransaction().commit();
			System.out.println("Done");

		} finally {
			factory.close();
		}

	}

}
