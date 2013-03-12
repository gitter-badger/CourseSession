package sis.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class ParityCheckerTest {

	@Test
	public void testSingleByte() {
		ParityChecker checker = new ParityChecker();
		
		byte source1 = 10;
		byte source2 = 13;
		byte source3 = 2;
		
		byte[] data = new byte[] { source1, source2, source3 };
		
		byte checksum = 5;
		
		assertEquals(checksum, checker.checksum(data));
		
		data[1] = 14;
		
		assertFalse(checksum == checker.checksum(data));
	}
	
	@Test
	public void testCoinFlips() {
		final long seed = 100L;
		final int total = 10;
		Random random1 = new Random(seed);
		List<Boolean> flips1 = new ArrayList<Boolean>();
		for (int i = 0; i < total; i++) {
			flips1.add(random1.nextBoolean());
		}
		
		Random random2 = new Random(seed);
		List<Boolean> flips2 = new ArrayList<Boolean>();
		for (int i = 0; i < total; i++) {
			flips2.add(random2.nextBoolean());
		}
		
		assertEquals(flips1, flips2);
	}
}
