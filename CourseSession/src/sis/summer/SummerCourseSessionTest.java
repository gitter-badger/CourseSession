package sis.summer;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import sis.studentinfo.*;

public class SummerCourseSessionTest extends SessionTest {

	@Test
	public void testEndDate() {
		Date startDate = DateUtil.createDate(2003, 6, 9);
		Session session = createSession(new Course("ENGL", "200"), startDate);
		Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
		assertEquals(eightWeeksOut, session.getEndDate());
	}

	@Override
	protected Session createSession(Course course, Date startDate) {
		return SummerCourseSession.create(course, startDate);
	}

}
