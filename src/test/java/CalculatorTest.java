import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
	@Test
	public void testCalculationWithoutParameter(){
		Calculation cal = new Calculation();
		assertEquals(0.0, cal.add(), 0.00001);
		assertEquals(0.0, cal.sub(), 0.00001);
		assertEquals(0.0, cal.mult(), 0.00001);
		assertEquals(Double.NaN, cal.div(), 0.00001);
		assertNotEquals(5.0, cal.add(), 0.00001);
	}

	@Test
	public void testCalculationWithParameters(){
		Calculation cal = new Calculation(5.0, 10.0);
		assertEquals(15.0, cal.add(), 0.00001);
		assertEquals(-5.0, cal.sub(), 0.00001);
		assertEquals(50.0, cal.mult(), 0.00001);
		assertEquals(0.50, cal.div(), 0.00001);
		assertNotEquals(5.0, cal.add(), 0.00001);
	}
	
	@Test
	public void testCalculationWithSecondParametersEqualZero(){
		Calculation cal = new Calculation(5.0, 0.0);
		assertEquals(5.0, cal.add(), 0.00001);
		assertEquals(5.0, cal.sub(), 0.00001);
		assertEquals(0.0, cal.mult(), 0.00001);
		assertEquals(Double.NaN, cal.div(), 0.00001);
		assertNotEquals(Double.NaN, cal.add(), 0.00001);
	}

	@Test
	public void testCalculationWithFirstParametersEqualZero(){
		Calculation cal = new Calculation(0.0, 100.0);
		assertEquals(100.0, cal.add(), 0.00001);
		assertEquals(-100.0, cal.sub(), 0.00001);
		assertEquals(0.0, cal.mult(), 0.00001);
		assertEquals(0.0, cal.div(), 0.00001);
		assertNotEquals(Double.NaN, cal.add(), 0.00001);
	}
}
