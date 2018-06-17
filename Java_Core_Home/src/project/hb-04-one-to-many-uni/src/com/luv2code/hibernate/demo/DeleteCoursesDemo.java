package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteCoursesDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Course course = session.get(Course.class, 10);			
			System.out.println("course :\n\t" + course);
			System.out.println("\t" + course.getReviews());
			System.out.println("Deleting");
			session.delete(course);
			session.getTransaction().commit();
			System.out.println("Done");
		} finally {
			session.close();
			factory.close();
		}

	}

}
