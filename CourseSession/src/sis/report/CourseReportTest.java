package sis.report;


import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import sis.studentinfo.*;
import static sis.report.ReportConstant.NEWLINE;

public class CourseReportTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReport() {
		final Date date = new Date();
		CourseReport report = new CourseReport();
		report.add(create("ENGL", "101", date));
		report.add(create("CZEC", "200", date));
		report.add(create("ITAL", "410", date));
		report.add(create("CZEC", "220", date));
		report.add(create("ITAL", "330", date));
		
		assertEquals(
				"CZEC 200" + NEWLINE +
				"CZEC 220" + NEWLINE +
				"ENGL 101" + NEWLINE +
				"ITAL 330" + NEWLINE +
				"ITAL 410" + NEWLINE,
				report.text());
	}

	private CourseSession create(String department, String number, Date startDate) {
		return (CourseSession) CourseSession.create(new Course(department, number), startDate);
	}

	@Test
	public void testSortStringInPlace() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Heller");
		list.add("Kafka");
		list.add("Camus");
		list.add("Boyle");
		java.util.Collections.sort(list);
		assertEquals("Boyle", list.get(0));
		assertEquals("Camus", list.get(1));
		assertEquals("Heller", list.get(2));
		assertEquals("Kafka", list.get(3));
	}
	
	@Test
	public void testSortsStringInNewList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Heller");
		list.add("Kafka");
		list.add("Camus");
		list.add("Boyle");
		ArrayList<String> sortedList = new ArrayList<String>(list);
		java.util.Collections.sort(sortedList);
		assertEquals("Boyle", sortedList.get(0));
		assertEquals("Camus", sortedList.get(1));
		assertEquals("Heller", sortedList.get(2));
		assertEquals("Kafka", sortedList.get(3));
		
		assertEquals("Heller", list.get(0));
		assertEquals("Kafka", list.get(1));
		assertEquals("Camus", list.get(2));
		assertEquals("Boyle", list.get(3));
	}	
	
	@Test
	public void testStringCompareTo() {
		assertTrue("A".compareTo("B") < 0);
		assertEquals(0, "A".compareTo("A"));
		assertTrue("B".compareTo("A") > 0);
	}
}
