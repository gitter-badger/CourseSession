
package sis.report;
import java.io.*;

import sis.studentinfo.*;
import static sis.report.ReportConstant.NEWLINE;

public class RosterReporter {

	public static final String ROSTER_REPORT_HEADER = "Student %n-%n";
	public static final String ROSTER_REPORT_FOOTER = "%n# student = %d%n";
	
	private Session session;
	private Writer writer;
	
	public RosterReporter(Session session) {
		this.session = session;
	}

	void writeHeader() throws IOException {
		writer.append(String.format(ROSTER_REPORT_HEADER));
	}
	
	void writeBody() throws IOException {
		for (Student student: session.getAllStudents()) {
			writer.append(String.format(student.getName() + "%n"));
		}
	}
	
	void writeFooter() throws IOException {
		writer.append(String.format(ROSTER_REPORT_FOOTER, session.getAllStudents().size()));		
	}

	public void writeReport(Writer writer) throws IOException {
		this.writer = writer;
		writeHeader();
		writeBody();
		writeFooter();
	}

	public void writeReport(String filename) throws IOException {		
		Writer bufferedWriter = new BufferedWriter(new FileWriter(filename));
		try {
			writeReport(bufferedWriter);
		} finally {
			bufferedWriter.close();
		}
	}
 }
