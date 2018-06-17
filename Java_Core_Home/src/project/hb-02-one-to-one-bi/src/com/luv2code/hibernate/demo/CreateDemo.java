package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
//			Instructor instructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
//			InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code");
			Instructor instructor = new Instructor("Madu", "Patel", "madu@luv2code.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://youtube", "Guitar");
			instructor.setInstructorDetail(instructorDetail);
			
			session.beginTransaction();
			session.save(instructor);
			session.getTransaction().commit();
			System.out.println("Done");

		} finally {
			factory.close();
		}

	}

}
