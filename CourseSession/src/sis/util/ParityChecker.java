package sis.util;

public class ParityChecker {

	public int checksum(byte[] data) {
		
		if (data.length == 0)
			return 0;
		
		int parity = data[0];
		for (int i = 1; i < data.length; i++) {
			parity ^= data[i];
		}
		return parity;
	}
}
