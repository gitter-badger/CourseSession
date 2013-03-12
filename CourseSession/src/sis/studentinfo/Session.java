package sis.studentinfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Session implements Comparable<Session>, Iterable<Student>, java.io.Serializable {

	private int numberOfStudents = 0;
	private transient ArrayList<Student> students = new ArrayList<Student>();
	private Date startDate;
	private int numberOfCredits;
	private static int count;
	private URL url;
	private Course course;
	
	/**
	 * Constructs a CourseSession starting on a specific data
	 * 
	 * @param department
	 * @param number
	 * @param startDate the date on which the CourseSession begins
	 */
	protected Session(Course course, Date startDate) {
		this.course = course;
		this.startDate = startDate;
	}
	
	public int compareTo(Session that) {
		int compare = this.getDepartment().compareTo(that.getDepartment());
		
		if (compare == 0) {
			return this.getNumber().compareTo(that.getNumber());
		}
		
		return compare;
	}
	
	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
	
	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	public String getDepartment() {
		return course.getDepartment();
	}
	
	public String getNumber() {
		return course.getNumber();
	}
	
	public void enroll(Student student) {
		numberOfStudents++;
		student.addCredits(numberOfCredits);
		students.add(student);
	}
	
	public Student get(int index) {
		return students.get(index);
	}

	protected Date getStartDate() {
		return startDate;
	}
	
	public ArrayList<Student> getAllStudents() {
		return students;
	}
	
	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	/**
	 * @return Date the last date of the course session
	 */
	public Date getEndDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getStartDate());
		final int daysInWeek = 7;
		final int daysFromFridayToMonday = 3;
		int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
		Date endDate = calendar.getTime();
		return endDate;
	}
	
	abstract protected int getSessionLength();

	public Date getDate() {
		return startDate;
	}

	public void setUrl(String urlString) throws SessionException {
		try {
			this.url = new URL(urlString);
		} catch (MalformedURLException e) {
			log(e);
			throw new SessionException(e);
		}
	}

	private void log(Exception e) {
		e.printStackTrace();
	}

	public URL getUrl() {
		return url;
	}
	
	private void writeObject(ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeInt(students.size());
		for (Student student: students) {
			output.writeObject(student.getLastName());
		}
	}
	
	private void readObject(ObjectInputStream input) throws Exception {
		input.defaultReadObject();
		students = new ArrayList<Student>();
		int size = input.readInt();
		for (int i = 0; i < size; i++) {
			String lastName = (String) input.readObject();
			students.add(new Student(lastName));
		}
	}
}
