package sis.studentinfo;

import java.util.*;
import java.util.logging.Logger;

import javax.swing.plaf.basic.BasicBorders.SplitPaneBorder;

public class Student {
	public enum Grade { 
		A(4), 
		B(3), 
		C(2), 
		D(1), 
		F(0);
		
		private int points;
		
		Grade(int points) {
			this.points = points;
		}
		
		public int getPoints() {
			return points;
		}
	}

	public enum Flag {
		ON_CAMPUS(1),
		TAX_EXEMPT(2),
		MINOR(4),
		TROUBLEMAKER(8);
		
		private int mask;
		
		Flag(int mask) {
			this.mask = mask;
		}
	}
	private String name;
	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private String id;
	private int credits;
	static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
	public static final String IN_STATE = "CO";
	public static final int MAX_NAME_PARTS = 3;
	private String state = "";
	private ArrayList<Grade> grades = new ArrayList<Grade>();
	private List<Integer> charges = new ArrayList<Integer>();
	
	private GradingStrategy gradingStrategy = new BasicGradingStrategy();
	
	final static Logger logger = Logger.getLogger(Student.class.getName());
	
	private int settings = 0x0;
	
	public Student(String fullName) {
		this.name = fullName;
		credits = 0;

		List<String> nameParts = tokenize(fullName);

		if (nameParts.size() > MAX_NAME_PARTS) {
			String message = "Student name '" + fullName + "' contains more than " 
						+ MAX_NAME_PARTS + " parts";
			Student.logger.info(message);
			throw new StudentNameFormatException(message);
		}
		setName(nameParts);
	}
	
	public String getName() {
		return name;
	}

	private List<String> tokenize(String string) {
		
		List<String> result = new ArrayList<String>();
		
		StringBuffer word = new StringBuffer();
		int index = 0;
		
		while (index < string.length()) {
			char ch = string.charAt(index);
			if (!Character.isWhitespace(ch))
				word.append(ch);
			else
				if (word.length() > 0) {
					result.add(word.toString());
					word = new StringBuffer();
				}
			index++;
		}
		if (word.length() > 0)
			result.add(word.toString());
		return result;
	}
	
	private void setName(List<String> nameParts) {
		this.lastName = removeLast(nameParts);
		String name = removeLast(nameParts);
		if (nameParts.isEmpty()) {
			this.firstName = name;
		}
		else {
			this.middleName = name;
			this.firstName = removeLast(nameParts);
		}
	}

	private String removeLast(List<String> list) {
		if (list.isEmpty())
			return "";		
		return list.remove(list.size() - 1);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isFullTime() {
		return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
	}

	public int getCredits() {
		return credits;
	}

	public void addCredits(int credits) {
		this.credits += credits;
	}

	public boolean isInState() {
		return state.toUpperCase().equals(Student.IN_STATE);
	}

	public void setState(String state) {
		this.state = state;
	}

	public void addGrade(Grade grade) {
		grades.add(grade);
	}

	public double getGpa() {
		if (grades.isEmpty()) {
			return 0.0;
		}
		
		double total = 0.0;
		for (Grade grade: grades) {
			total += gradingStrategy.getGradePointsFor(grade);
		}
		
		return total / grades.size();
	}

	private int gradePointsFor(Grade grade) {
		return gradingStrategy.getGradePointsFor(grade);
	}
	
	private int basicGradePointsFor(Grade grade) {
		if (grade == Grade.A) return 4;
		if (grade == Grade.B) return 3;
		if (grade == Grade.C) return 2;
		if (grade == Grade.D) return 1;
		return 0;
	}
	
	public void setGradingStrategy(GradingStrategy gradingStrategy) {
		this.gradingStrategy = gradingStrategy;
	}
	
	public void addCharge(int charge) {
		charges.add(charge);
	}
	
	public int totalCharges() {
		int total = 0;
		
		for (Integer charge: charges) {
			total += charge;
		}
		return total;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void set(Flag... flags) {
		for (Flag flag: flags)
			settings |= flag.mask;
	}
	
	public void unset(Flag... flags) {
		for (Flag flag: flags)
			settings &= ~flag.mask;
	}
	
	public boolean isOn(Flag flag) {
		return (settings & flag.mask) == flag.mask;
	}
	
	public boolean isOff(Flag flag) {
		return !isOn(flag);
	}

}
