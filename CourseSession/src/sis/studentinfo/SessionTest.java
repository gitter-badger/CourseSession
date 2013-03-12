package sis.studentinfo;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import static sis.studentinfo.DateUtil.createDate;

abstract public class SessionTest {
	
	private static final int CREDITS = 3;
	private Date startDate;
	private Session session;

	@Before
	public void setUp() throws Exception {
		startDate = createDate(2003, 1, 6);
		session = createSession(new Course("ENGL", "101"), startDate);
		session.setNumberOfCredits(CREDITS);
	}

	abstract protected Session  createSession(Course course, Date startDate);
	
	@Test
	public void testCreate() {
		assertEquals("ENGL", session.getDepartment());
		assertEquals("101", session.getNumber());
		assertEquals(0, session.getNumberOfStudents());
	}
	
	@Test
	public void testCompareable() {
		final Date date = new Date();
		Session sessionA = create("CMSC", "101", date);
		Session sessionB = create("ENGL", "101", date);
				
		assertTrue(sessionA.compareTo(sessionB) < 0);
		assertTrue(sessionB.compareTo(sessionA) > 0);
		
		Session sessionC = create("CMSC", "101", date);
		assertEquals(0, sessionA.compareTo(sessionC));
		
		Session sessionD = create("CMSC", "210", date);
		assertTrue(sessionC.compareTo(sessionD) < 0);
		assertTrue(sessionD.compareTo(sessionC) > 0);
	}
	
	private Session create(String department, String number, Date startDate) {
		return CourseSession.create(new Course(department, number), startDate) ;
	}

	@Test
	public void testEnrollStudents() {		
		Student student1 = new Student("Cain DiVoe");
		session.enroll(student1);
		assertEquals(CREDITS, student1.getCredits());
		assertEquals(1, session.getNumberOfStudents());	
		assertEquals(student1, session.get(0));
		
		Student student2 = new Student("Coralee DeVaughn");
		session.enroll(student2);
		assertEquals(CREDITS, student2.getCredits());
		assertEquals(2, session.getNumberOfStudents());
		assertEquals(student1, session.get(0));
		assertEquals(student2, session.get(1));
	}
	
	@Test
	public void testSessionUrl() throws SessionException {
		final String url = "http;//course.langrsoft.com/cmsc300";
		try {
			session.setUrl(url);
			assertEquals(url, session.getUrl().toString());
		} catch (SessionException e) {
			Logger logger = Logger.getLogger(SessionTest.class.getName());
			logger.info(e.getMessage());
		}
	}
	
	@Test
	public void testInvalidSessionUrl() {
		final String url = "httsp://course.langrsoft.com/cmsc300";
		try {
			session.setUrl(url);
			fail("expected exception due to invalid protocol in URL");
		} catch (SessionException expecException) {
			Throwable cause = expecException.getCause();
			assertEquals(MalformedURLException.class, cause.getClass());
		}
	}
}
