package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			int id = 4;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			if (instructorDetail != null) {
				instructorDetail.getInstructor().setInstructorDetail(null);
				session.delete(instructorDetail);
			}
			session.getTransaction().commit();

			System.out.println("====================");
			System.out.println("Remaining Instructor in DB");
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Instructor> instructors = session.createQuery("from Instructor").getResultList();
			if (!instructors.isEmpty())
				instructors.forEach(instructor -> System.out.println(instructor));
			else
				System.out.println("Empty DB");
			session.getTransaction().commit();
			System.out.println("Done");

		} finally {
			session.close();
			factory.close();
		}

	}

}
