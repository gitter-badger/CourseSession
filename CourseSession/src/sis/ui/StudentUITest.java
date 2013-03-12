package sis.ui;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import sis.studentinfo.*;

public class StudentUITest {
	static private final String name = "Leo Xerces Schmoo";
	
	@Test
	public void testCreateStudent() throws IOException {
		StringBuffer expectedOutput = new StringBuffer();
		StringBuffer input = new StringBuffer();
		setup(expectedOutput, input);
		byte[] buffer = input.toString().getBytes();
		
		InputStream inputStream = new ByteArrayInputStream(buffer);
		OutputStream outputStream = new ByteArrayOutputStream();
			
		InputStream consoleIn = System.in;
		PrintStream consoleout = System.out;
		
		System.setIn(inputStream);
		System.setOut(new PrintStream(outputStream));
		try {
			StudentUI ui = new StudentUI();
			ui.run();
			
			assertEquals(expectedOutput.toString(), outputStream.toString());
			assertStudents(ui.getAddedStudents());			
		}
		finally {
			System.setIn(consoleIn);
			System.setOut(consoleout);
		}
	}

	private String line(String input) {
		return String.format("%s%n", input);
	}
	
	private void assertStudents(List<Student> students) {
		assertEquals(1, students.size());
		Student student = students.get(0);
		assertEquals(name, student.getName());
	}

	private void setup(StringBuffer expectedOutput, StringBuffer input) {
		expectedOutput.append(StudentUI.MENU);
		input.append(line(StudentUI.ADD_OPTOIN));
		expectedOutput.append(StudentUI.NAME_PROMPT);
		input.append(line(name));
		expectedOutput.append(line(StudentUI.ADDED_MESSAGE));
		expectedOutput.append(StudentUI.MENU);
		input.append(line(StudentUI.QUIT_OPTION));
	}
}
