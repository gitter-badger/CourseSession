package sis.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordGeneratorTest {

	public class MockRandom extends java.util.Random {
		private int i;
		public MockRandom(char startCharValue) {
			i = startCharValue - PasswordGenerator.LOW_END_PASSWORD_CHAR;
		}
		protected int next(int bits) {
			return i++;
		}
	}
	
	@Test
	public void testGeneraterPassword() {
		PasswordGenerator generator = new PasswordGenerator();
		generator.setRandom(new MockRandom('A'));
		assertEquals("ABCDEFGH", generator.generatePassword());
		
		generator.setRandom(new MockRandom('C'));
		assertEquals("CDEFGHIJ", generator.generatePassword());
		
	}

}
