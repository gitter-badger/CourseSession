package sis.studentinfo;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;


public class CourseSessionTest extends SessionTest {

	@Test
	public void testCourseDates() {
		Date startDate = DateUtil.createDate(2003, 1, 6);
		Session session = createSession(createCourse(), startDate);
		Date sixteenWeeksOut = DateUtil.createDate(2003, 4, 25);
		assertEquals(sixteenWeeksOut, session.getEndDate());		
	}
	
	@Test
	public void testCount() {
		CourseSession.resetCount();
		createSession(createCourse(), new Date());
		assertEquals(1, CourseSession.getCount());
		createSession(createCourse(), new Date());
		assertEquals(2, CourseSession.getCount());
	}

	@Override
	protected Session createSession(Course course,	Date startDate) {
		return CourseSession.create(course, startDate);
	}
	
	private Course createCourse() {
		return new Course("ENGL", "200");
	}
}
