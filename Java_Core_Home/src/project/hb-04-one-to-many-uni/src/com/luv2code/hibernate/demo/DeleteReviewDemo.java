package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class DeleteReviewDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Review review = session.get(Review.class, 4);
			if (review != null)
				session.delete(review);
			session.getTransaction().commit();

			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Review> reviews = session.createQuery("FROM Review").getResultList();
			reviews.forEach(rev -> System.out.println(rev));
			session.getTransaction().commit();
			System.out.println("Done");
		} finally {
			session.close();
			factory.close();
		}

	}

}
