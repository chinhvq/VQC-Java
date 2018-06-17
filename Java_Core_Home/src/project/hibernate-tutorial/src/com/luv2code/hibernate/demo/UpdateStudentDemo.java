package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Student student = session.get(Student.class, 2);
			student.setEmail("john@gmail.com");
			session.getTransaction().commit();
			System.out.println(student);
			System.out.println("==============================");
			System.out.println("update students email");
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("UPDATE Student set email='foo@gmail.com' WHERE id < 2 OR id > 4").executeUpdate();
			session.getTransaction().commit();
			System.out.println("==============================");
			System.out.println("list out all student");
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> students = session.createQuery("from Student").getResultList();
			students.forEach(std -> System.out.println(std));
			System.out.println("Done");

		} finally {
			factory.close();
		}

	}

}
