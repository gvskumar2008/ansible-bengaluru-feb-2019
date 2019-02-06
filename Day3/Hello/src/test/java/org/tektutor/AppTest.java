package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

	@Test
	public void testApp() {
		App app = new App();
		
		String actualResponse = app.sayHello();
		String expectedResponse = "Hello DevOps!";

		assertEquals ( expectedResponse, actualResponse );

	}

	@Test
	public void testFail() {
		assertEquals ( 100, 200 );
	}

}
