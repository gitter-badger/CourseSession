package sis.report;

import java.util.*;

import sis.studentinfo.*;
import static sis.report.ReportConstant.NEWLINE;

public class CourseReport {

	private ArrayList<CourseSession> sessions = new ArrayList<CourseSession>();
	
	public CourseReport() {
		
	}

	public void add(CourseSession session) {
		sessions.add(session);
	}

	public String text() {
		
		Collections.sort(sessions);
		StringBuilder buffer = new StringBuilder();
		
		for (CourseSession session: sessions) {
			buffer.append(session.getDepartment() + " " + 
					session.getNumber() + NEWLINE);
		}
		return buffer.toString();
	}
}
