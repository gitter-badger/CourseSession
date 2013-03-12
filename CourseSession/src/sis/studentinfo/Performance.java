package sis.studentinfo;

public class Performance {

	private int[] tests = {};
	
	public void setNumberOfTests(int numberOfOffsets) {
		tests = new int[numberOfOffsets];
	}

	public void set(int testNumber, int score) {
		tests[testNumber] = score;
	}

	public int get(int testNumber) {
		return tests[testNumber];
	}

	public double average() {
		double total = 0.0;
		
		if (tests.length == 0) {
			return total;
		}
		
		for (int score: tests) {
			total += score;
		}
		return total/tests.length;
	}

//	public void setScores(int[] tests) {
//		this.tests = tests;
//	}

	public void setScores(int... tests) {
		this.tests = tests;
	}
}
