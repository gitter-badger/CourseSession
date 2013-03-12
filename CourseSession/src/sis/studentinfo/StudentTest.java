package sis.studentinfo;
import static org.junit.Assert.*;

import java.util.logging.Handler;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import sis.studentinfo.Student.Flag;
import sis.studentinfo.Student.Grade;


public class StudentTest {

	private static final double GRADE_TOLERANCE = 0.05;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreate() {
		final String firstStudentName = "Jane Doe";
		Student firstStudent = new Student(firstStudentName);
		assertEquals("Jane", firstStudent.getFirstName());
		assertEquals("", firstStudent.getMiddleName());
		assertEquals("Doe", firstStudent.getLastName());
		
		final String secondStudentName = "Blow";
		Student secondStudent = new Student(secondStudentName);
		assertEquals("", secondStudent.getFirstName());
		assertEquals("", secondStudent.getMiddleName());
		assertEquals("Blow", secondStudent.getLastName());
		
		final String thirdStudentName = "Raymond Douglas Davies";
		Student thirdStudent = new Student(thirdStudentName);
		assertEquals("Raymond", thirdStudent.getFirstName());
		assertEquals("Douglas", thirdStudent.getMiddleName());
		assertEquals("Davies", thirdStudent.getLastName());
	}

	@Test
	public void testFullTime() {
		Student student = new Student("a");
		assertFalse(student.isFullTime());
	}
	
	@Test
	public void testCredits() {
		Student student = new Student("a");
		assertEquals(0, student.getCredits());
		student.addCredits(3);
		assertEquals(3, student.getCredits());
		student.addCredits(4);
		assertEquals(7, student.getCredits());
	}
	
	@Test
	public void testStudentStatus() {
		Student student = new Student("a");
		assertEquals(0, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(3);
		assertEquals(3, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(4);
		assertEquals(7, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(5);
		assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
		assertTrue("not enough credits for FT status", student.isFullTime());
	}
	
	@Test
	public void testInState() {
		Student student = new Student("a");
		assertFalse(student.isInState());
		student.setState(Student.IN_STATE);
		assertTrue(student.isInState());
		student.setState("MD");
		assertFalse(student.isInState());
		student.setState("co");
		assertTrue(student.isInState());
	}
	
	@Test
	public void testCalculateGpa() {
		Student student = new Student("a");
		assertGpa(student, 0.0);
		student.addGrade(Student.Grade.A);
		assertGpa(student, 4.0);
		student.addGrade(Student.Grade.B);
		assertGpa(student, 3.5);
		student.addGrade(Student.Grade.C);
		assertGpa(student, 3.0);
		student.addGrade(Student.Grade.D);
		assertGpa(student, 2.5);
		student.addGrade(Student.Grade.F);
		assertGpa(student, 2.0);
	}

	@Test
	public void testCalculateHonorsStudentGpa() {
		assertGpa(createHonorsStudent(), 0);
		assertGpa(createHonorsStudent(Student.Grade.A), 5.0);
		assertGpa(createHonorsStudent(Student.Grade.B), 4.0);
		assertGpa(createHonorsStudent(Student.Grade.C), 3.0);
		assertGpa(createHonorsStudent(Student.Grade.D), 2.0);
		assertGpa(createHonorsStudent(Student.Grade.F), 0.0);
	}
	
	private Student createHonorsStudent(Student.Grade grade) {
		Student student = createHonorsStudent();
		student.addGrade(grade);
		return student;
	}

	private Student createHonorsStudent() {
		Student student = new Student("a");
		student.setGradingStrategy(new HonorsGradingStrategy());
		return student;
	}

	private void assertGpa(Student student, double gpa) {
		assertEquals(gpa, student.getGpa(), GRADE_TOLERANCE);		
	}
	
	@Test
	public void testBadlyFormattedName() {
		Handler handler = new TestHandler();
		Student.logger.addHandler(handler);
		
		final String studentName = "a b c d";
		try {
			new Student(studentName);
			fail("expected exception from 4-part name");
		} catch (StudentNameFormatException expectedException) {
			String message = String.format("Student name '%s' contains more than %d parts", 
					studentName, Student.MAX_NAME_PARTS);
			assertEquals(message, expectedException.getMessage());
			assertEquals(message, ((TestHandler)handler).getMessage());
		}
	}

	private boolean wasLogged(String message, TestHandler handler) {
		return message.equals(handler.getMessage());
	}
	
	@Test
	public void testLoggingHierarchy() {
		Logger logger = Logger.getLogger("sis.studentinfo.Student");
		assertTrue(logger == Logger.getLogger("sis.studentinfo.Student"));
		
		Logger parent = logger.getLogger("sis.studentinfo");
		assertEquals(parent, logger.getParent());
		assertEquals(Logger.getLogger("sis"), 	parent.getParent());
	}
	
	@Test
	public void testFlags() {
		Student student = new Student("a");
		
		student.set(
				Student.Flag.ON_CAMPUS,
				Student.Flag.TAX_EXEMPT,
				Student.Flag.MINOR);
		
		assertTrue(student.isOn(Student.Flag.ON_CAMPUS));
		assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
		assertTrue(student.isOn(Student.Flag.MINOR));
		
		assertFalse(student.isOff(Student.Flag.ON_CAMPUS));
		assertTrue(student.isOff(Student.Flag.TROUBLEMAKER));
		
		student.unset(Student.Flag.ON_CAMPUS);
		assertTrue(student.isOff(Student.Flag.ON_CAMPUS));
		assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
		assertTrue(student.isOn(Student.Flag.MINOR));
	}
}
