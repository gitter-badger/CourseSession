package sis.studentinfo;

import java.io.IOException;
import java.util.*;
import sis.db.*;

public class StudentDirectory {

	private static final String DIR_BASENAME = "studentDir";
	private DataFile db;
	
	public StudentDirectory() throws IOException {
		db = DataFile.open(DIR_BASENAME);
	}
	
	public void add(Student student) throws IOException {
		db.add(student.getId(), student);
	}

	public Student findById(String id) throws IOException {
		return (Student) db.findBy(id);
	}

	public void remove() {
		db.deleteFiles();
	}

	public void close() throws ClassNotFoundException, IOException {
		db.close();
	}

}
