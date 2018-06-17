package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Course course = new Course("Pacman - How to score one millions points");
			course.addReview(new Review("Greate course .... love it so much!"));
			course.addReview(new Review("Cool course .... job well done!"));
			course.addReview(new Review("What a dumb course"));
			session.save(course);
			System.out.println("Save course :\n\t" + course);

			Student student1 = new Student("John", "Doe", "john@luv2code.com");
			Student student2 = new Student("Marry", "Public", "mary@luv2code.com");
			course.addStudent(student1);
			course.addStudent(student2);
			session.save(student1);
			session.save(student2);
			System.out.println("Saved Student" + course.getStudents());

			session.getTransaction().commit();

			System.out.println("Done");

		} finally {
			session.close();
			factory.close();
		}

	}

}
