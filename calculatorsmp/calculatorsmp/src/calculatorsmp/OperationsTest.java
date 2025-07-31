package calculatorsmp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OperationsTest {

	@Test
	@DisplayName("Verificar fórmula válida")
	void MF1() {
		String form = Operations.MakeFormula();
		assertNotNull(form,"la fórmula no puede ser nula");
		assertTrue(form.matches("\\d+(\\+|\\-|\\*|/)\\d+((\\+|\\-|\\*|/)\\d+)*"), "La fórmula " + form + " no sigue el formato correcto");
	}
	
	@Test
	@DisplayName("Verifica que la fórmula contenga operadores")
	void MF2() {
		String form = Operations.MakeFormula();
		assertTrue(form.contains("+") || form.contains("-") || form.contains("*") || form.contains("/"));
	}
	
	@Test
	@DisplayName("Verifica que la fórmula no contenga operaciones con 0")
	void MF3() {
		String form = Operations.MakeFormula();
		assertFalse(form.contains("+0")||form.contains("-0")||form.contains("/0"));
		
		//El código actual no permite el uso de 0 en la fórmula, pero se establece este test en caso de cambios en el código
		//para verificar que se siga cumpliendo dicha condicion
		//se toman en cuenta suma y resta (se pueden obviar de la fórmula) y división (indefinida)
		
	}
	
	@Test
	@DisplayName("Verifica la suma simple")
	void S01() {
		String solution = Operations.Solve("6+36");
		
		assertEquals("6+36=42",solution);
		
	}
	
	@Test
	@DisplayName("Verifica la prioridad de los operadores")
	void S02() {
		String sol = Operations.Solve("1+2*3+4");
		assertEquals("1+2*3+4=11",sol);
		
		sol = Operations.Solve("12-4*2");
		assertEquals("12-4*2=4",sol);
	}
	
	@Test
	@DisplayName("Verifica division entera")
	void S03() {
		String sol = Operations.Solve("25/3");
		assertEquals("25/3=8",sol);
	}
	
	@Test
	@DisplayName("Verifica division indeterminada")
	void S04() {
		ArithmeticException excpt = assertThrows(ArithmeticException.class,() -> {
			Operations.Solve("6/0");			
		});
		assertEquals("Division por 0 es indeterminada", excpt.getMessage());
	}
	
	@Test
	@DisplayName("Verifica formulas con espacios")
	void S05() {
		String sol = Operations.Solve("6 / 3");
		assertEquals("6/3=2",sol);
	}
	
	@Test
	@DisplayName("Verifica formulas con formato correcto")
	void s06() {
		Exception excpt = assertThrows(Exception.class, () -> {
	        Operations.Solve("6+++6");
	    });
	assertEquals("Operadores consecutivos no están permitidos", excpt.getMessage());
	}

}
