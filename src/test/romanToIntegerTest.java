package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.RomanToInteger;

class romanToIntegerTest {
	/**
	 * @throws java.lang.Exception
	 */
	RomanToInteger r2i = null;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		r2i = new RomanToInteger();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception
	{
		//Garbage collector shall clear the objects
	}
	
	
	@Test
	void test_positive_flow1() {
		
		String input = "XIX";

		r2i.r2i(input);
		int output = r2i.getResult();
		
		assertEquals(19, output, "Invalid");
	}
	
	@Test
	void test_positive_flow2() {
		
		String input = "XVIII";

		r2i.r2i(input);
		int output = r2i.getResult();
		
		assertEquals(18, output, "Invalid");

	}
	
	
	@Test
	void test_positive_flow3() {
		
		String input = "XVIII";

		r2i.r2i(input);
		int output = r2i.getResult();
			
		assertEquals(18, output, "Invalid");

	}
	
	@Test
	void test_negativeCase_RULE1_validation_fail() {
		
		String input = "IXX";

		Exception exception = assertThrows(
				NumberFormatException.class,
				() -> r2i.r2i(input));

		assertTrue(exception.getMessage().contains("RULE-1"));
	}
	
	@Test
	void test_negativeCase_SUBRULE2_validation_fail() {
		
		String input = "IL";
		
		Exception exception = assertThrows(
				NumberFormatException.class,
				() -> r2i.r2i(input));

		assertTrue(exception.getMessage().contains("SUBRULE-2"));
	}
	
	@Test
	void test_negativeCase_SUBRULE3_validation_fail() {
		
		String input = "XD";
		
		Exception exception = assertThrows(
				NumberFormatException.class,
				() -> r2i.r2i(input));

		assertTrue(exception.getMessage().contains("SUBRULE-3"));
	}
	
	
	@Test
	void test_positive_flow4() {
		
		String input = "XXXXIX";

		r2i.r2i(input);
		int output = r2i.getResult();
		
		assertEquals(49, output, "Invalid");
	}
	
	@Test
	void test_positive_flow5() {
		
		String input = "MDCVI";
		
		assertEquals(1606, r2i.r2i(input), "Invalid");
	}
}
