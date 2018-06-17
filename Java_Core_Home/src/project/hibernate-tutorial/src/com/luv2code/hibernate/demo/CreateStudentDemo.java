package com.luv2code.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			System.out.println("Creating a new student");
			String theDateOfBirthStr = "31/12/1998";
			Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			Student student = new Student("Paul", "Wall", "paul@luv2code.com", theDateOfBirth);
			session.beginTransaction();
			System.out.println("Saving student");
			session.save(student);
			session.getTransaction().commit();
			System.out.println("Done");

		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
