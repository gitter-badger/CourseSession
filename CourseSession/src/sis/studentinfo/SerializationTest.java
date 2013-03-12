package sis.studentinfo;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class SerializationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoadToNewVersion() throws Exception {
		CourseCatalog catalog = new CourseCatalog();
		catalog.load("CourseCatalogTest.testAdd.txt");
		assertEquals(2,  catalog.getSessions().size());
	}
}
