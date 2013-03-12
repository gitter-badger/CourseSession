package sis.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import sis.studentinfo.*;

public class StudentUI {

	public static final String MENU = "(A)dd or (Q)uit?";
	public static final String ADD_OPTOIN = "A";
	public static final String QUIT_OPTION = "Q";
	public static final String NAME_PROMPT = "Name:";
	public static final String ADDED_MESSAGE = "Added";
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private List<Student> students = new ArrayList<Student>();

	public StudentUI() {
		this.reader = new BufferedReader(new InputStreamReader(System.in));
		this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
	}

	public void run() throws IOException {
		String line;
		do {
			write(MENU);
			line = reader.readLine();
			if (line.equals(ADD_OPTOIN)) {
				addStudent();
			}
		} while (!line.equals(QUIT_OPTION));
	}

	private void addStudent() throws IOException {
		write(NAME_PROMPT);
		String name = reader.readLine();
		students.add(new Student(name));
		writeln(ADDED_MESSAGE);
	}

	private void writeln(String line) throws IOException {
		write(line);
		writer.newLine();
		writer.flush();
	}

	private void write(String line) throws IOException {
		writer.write(line, 0, line.length());
		writer.flush();
	}

	public List<Student> getAddedStudents() {
		return students;
	}
	
	public static final void main(String[] args) throws IOException {
		new StudentUI().run();
	}
}
