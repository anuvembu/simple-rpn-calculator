package rpnCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class RPNTest {

	@Test
	void testCompute() {
		String input = "7 12 2 /";
		String result = RPN.compute(input);
		String expected = "7 6"; 
		assertEquals(expected, result);
		result = RPN.compute("*");
		
		expected = "42";
		result = RPN.compute("4 /");
		expected = "10.5";
		
		System.out.println(result);
		assertEquals(expected, result);
	}

}
