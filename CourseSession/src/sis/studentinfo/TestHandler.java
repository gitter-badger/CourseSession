package sis.studentinfo;

import java.util.logging.*;

public class TestHandler extends Handler {
	private LogRecord record;
	
	@Override
	public void publish(LogRecord record) {
		this.record = record;
	}

	@Override
	public void flush() {
		
	}

	@Override
	public void close() throws SecurityException {
		
	}

	String getMessage() {
		return record.getMessage();
	}
}
