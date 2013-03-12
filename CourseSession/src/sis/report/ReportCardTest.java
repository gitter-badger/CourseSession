package sis.report;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import sis.studentinfo.*;

public class ReportCardTest {

	private ReportCard card;
	
	@Before
	public void setUp() throws Exception {
		card = new ReportCard();
	}

	@Test
	public void testMessage() {		
		assertEquals(ReportCard.A_MESSAGE, card.getMessage(Student.Grade.A));
	}
	
	@Test
	public void testKeys() {
		Set<Student.Grade> expectedGrade = EnumSet.allOf(Student.Grade.class);
		
		Set<Student.Grade> grades = EnumSet.noneOf(Student.Grade.class);
		for (Student.Grade grade: card.getMessages().keySet())
			grades.add(grade);
		
		assertEquals(expectedGrade, grades);
	}
	
	@Test
	public void testValues() {
		List<String> expectedMessages = new ArrayList<String>();
		expectedMessages.add(ReportCard.A_MESSAGE);
		expectedMessages.add(ReportCard.B_MESSAGE);
		expectedMessages.add(ReportCard.C_MESSAGE);
		expectedMessages.add(ReportCard.D_MESSAGE);
		expectedMessages.add(ReportCard.F_MESSAGE);
		
		Collection<String> messages = card.getMessages().values();
		for (String message: messages) {
			assertTrue(expectedMessages.contains(message));
		}
		assertEquals(expectedMessages.size(), messages.size());
	}
	
	@Test
	public void testEntries() {
		Set<Entry> entries = new HashSet<Entry>();
		
		for (Map.Entry<Student.Grade, String> entry: card.getMessages().entrySet())
			entries.add(new Entry(entry.getKey(), entry.getValue()));
		
		Set<Entry> expectedEntries = new HashSet<Entry>();
		expectedEntries.add(new Entry(Student.Grade.A, ReportCard.A_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.B, ReportCard.B_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.C, ReportCard.C_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.D, ReportCard.D_MESSAGE));
		expectedEntries.add(new Entry(Student.Grade.F,  ReportCard.F_MESSAGE));
		
		assertEquals(expectedEntries, entries);
	}
}
