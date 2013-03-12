package sis.summer;

import sis.studentinfo.*;

import java.util.*;

public class SummerCourseSession extends Session {

	public static Session create(Course course, Date startDate) {
		return new SummerCourseSession(course, startDate);
	}
	
	private SummerCourseSession(Course course, Date startDate) {
		super(course, startDate);
	}
	
	@Override
	protected int getSessionLength() {
		return 8;
	}

	@Override
	public int compareTo(Session o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Student> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
