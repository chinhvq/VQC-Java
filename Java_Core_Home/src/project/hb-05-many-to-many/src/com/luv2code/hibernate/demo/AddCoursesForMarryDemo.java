package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForMarryDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Student mary = session.get(Student.class, 2);
			System.out.println("loaded student: " + mary);
			System.out.println("Current course of student: " + mary.getCourses());

			Course course1 = new Course("Rubik 's the Cube - How to Speed Cube");
			course1.addReview(new Review("Greate course .... love it so much!"));
			course1.addReview(new Review("Cool course .... job well done!"));
			course1.addReview(new Review("What a dumb course"));
			course1.addStudent(mary);
			session.save(course1);

			Course course2 = new Course("Atari 2600 - Game Development");
			course2.addReview(new Review("Greate course .... love it so much!"));
			course2.addReview(new Review("Cool course .... job well done!"));
			course2.addReview(new Review("What a dumb course"));
			course2.addStudent(mary);
			session.save(course2);

			session.getTransaction().commit();

			System.out.println("Done");

		} finally {
			session.close();
			factory.close();
		}

	}

}
