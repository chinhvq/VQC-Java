package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Course course = new Course("Pacman - How to score one millions points");
			course.addReview(new Review("Greate course .... love it so much!"));
			course.addReview(new Review("Cool course .... job well done!"));
			course.addReview(new Review("What a dumb course"));
			System.out.println("course :\n\t" + course);
			System.out.println("\t" + course.getReviews());
			session.save(course);
			session.getTransaction().commit();

			System.out.println("Done");

		} finally {
			session.close();
			factory.close();
		}

	}

}
