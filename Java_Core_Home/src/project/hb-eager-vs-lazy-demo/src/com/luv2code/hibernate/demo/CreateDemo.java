package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			Instructor instructor = new Instructor("Susan", "Public", "madu@luv2code.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://youtube", "Video Games");
			instructor.setInstructorDetail(instructorDetail);

			session.beginTransaction();
			session.save(instructor);
			session.getTransaction().commit();

			System.out.println("===================");
			session = factory.getCurrentSession();
			session.beginTransaction();
			instructor = session.get(Instructor.class, 1);
			Course course1 = new Course("Air Guitar - The Ultimate Guide");
			Course course2 = new Course("The Pinball - Master Class");
			instructor.add(course1);
			instructor.add(course2);
			session.save(course1);
			session.save(course2);
			session.getTransaction().commit();
			System.out.println("Done");

		} finally {
			session.close();
			factory.close();
		}

	}

}
