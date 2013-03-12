package sis.studentinfo;
import java.util.*;

/**
 * Provides a representation of a single-semester
 * session of a specific university course.
 * @author Administrator
 */
public class CourseSession extends Session {
	
	private String department;
	private String number;
	private int numberOfStudents = 0;
	private ArrayList<Student> students = new ArrayList<Student>();
	private Date startDate;
	private int numberOfCredits;
	private static int count;


	public static Session create(Course course, Date startDate) {
		incrementCount();
		return new CourseSession(course, startDate);
	}
	
	protected CourseSession(Course course, Date startDate) {
		super(course, startDate);
	}

	@Override
	protected int getSessionLength() {
		return 16;
	}
	
	static private void incrementCount()
	{
		++count;
	}
	
	static void resetCount()
	{
		count = 0;
	}
	
	static int getCount()
	{
		return count;
	}

	@Override
	public Iterator<Student> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
 }

