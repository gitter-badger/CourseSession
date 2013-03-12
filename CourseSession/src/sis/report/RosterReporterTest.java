package sis.report;

import static org.junit.Assert.*;
import sis.studentinfo.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import sis.studentinfo.CourseSession;
import sis.studentinfo.DateUtil;
import sis.studentinfo.Student;
import static sis.report.ReportConstant.NEWLINE;

public class RosterReporterTest {

	private Session session;
	
	@Before
	public void setUp() throws Exception {
		Date date = DateUtil.createDate(2003,  1, 1);
		session = CourseSession.create(new Course("ENGL", "101"), date);
		
		session.enroll(new Student("A"));
		session.enroll(new Student("B"));
	}

	@Test
	public void testRosterReport() throws IOException {
		
		Writer writer = new StringWriter();
		new RosterReporter(session).writeReport(writer);
		String rosterReport = writer.toString();
		assertReportContents(writer.toString());
	}

	@Test
	public void testFileReport() throws IOException {
		final String filename = "testFileReport.txt";
		
		try {
			delete(filename);
			new RosterReporter(session).writeReport(filename);
			
			StringBuffer buffer = new StringBuffer();
			String line;
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				buffer.append(String.format(line + "%n"));
			}
			reader.close();
			
			assertReportContents(buffer.toString());
		}
		finally {
			delete(filename);
		}
	}
	
	private void delete(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			assertTrue("unable to delete " + filename, file.delete());
		}
	}

	private void assertReportContents(String rosterReport) {
		assertEquals(String.format(RosterReporter.ROSTER_REPORT_HEADER + 
				"A%n" +"B%n" +
				RosterReporter.ROSTER_REPORT_FOOTER , 2), rosterReport);
	}

}
