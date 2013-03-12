package sis.studentinfo;

import sis.studentinfo.Student.Grade;

public class HonorsGradingStrategy extends BasicGradingStrategy {

	@Override
	public int getGradePointsFor(Student.Grade grade) {
		int points = super.getGradePointsFor(grade);
		if (points > 0) {
			points += 1;
		}
		return points;
	}


}
