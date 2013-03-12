package sis.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class KeyFile {

	private Map<String, EntryData> keys = new HashMap<String, EntryData>();
	private File file;
	
	public KeyFile(String filename) throws IOException {
		file = new File(filename);
		if (file.exists()) {
			load();
		}
	}

	private void load() throws IOException {
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(new FileInputStream(file));			
			try {
				keys = (Map<String, EntryData>) input.readObject();
			} catch (ClassNotFoundException e) {
			}
		} finally {
			input.close();
		}		
	}

	public void close() throws IOException, ClassNotFoundException {
		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(keys);
		} finally {
			output.close();
		}
	}

	public void add(String key, long position, int length) {
		EntryData entry = new EntryData(position, length);
		keys.put(key, entry);
	}

	public boolean containsKey(String key) {
		return keys.containsKey(key);
	}

	public long getPosition(String key) {
		EntryData entry =  keys.get(key);
		
		if (entry != null) {
			return entry.getPosition();
		}
		return 0L;
	}

	public int getLength(String key) {
		EntryData entry =  keys.get(key);
		
		if (entry != null) {
			return entry.getLength();
		}
		return 0;
	}

	static class EntryData implements Serializable {
		private long position;
		private int length;
		
		public EntryData(long position, int length) {
			this.position = position;
			this.length = length;
		}
		
		private long getPosition() {
			return position;
		}
		
		private int getLength() {
			return length;
		}
	}

	public int size() {
		return keys.size();
	}
}
