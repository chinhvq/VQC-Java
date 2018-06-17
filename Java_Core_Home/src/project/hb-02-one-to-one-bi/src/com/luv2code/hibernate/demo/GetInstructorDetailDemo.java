package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			int id = 10;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			if (instructorDetail != null) {
				System.out.println("Instructor Detail : \n\t" + instructorDetail);
				System.out.println("Instructor : \n\t" + instructorDetail.getInstructor());
			}
			session.getTransaction().commit();
			System.out.println("Done");

		} finally {
			session.close();
			factory.close();
		}

	}

}
