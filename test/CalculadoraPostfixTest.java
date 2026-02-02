package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import src.CalculadoraPostfix;

public class CalculadoraPostfixTest {
    private CalculadoraPostfix calculadora;

    @Before
    // Inicializa la calculadora antes de cada prueba
    public void setUp(){
        calculadora = new CalculadoraPostfix();
    }

    @Test
    //asserEquals tiene dos parámetros, el resultado esperado y 
    // la operación/dato a evaluar
    public void testSuma(){
        assertEquals(8, calculadora.evaluar("3 5 +"));
        assertEquals(100, calculadora.evaluar("50 50 +"));
        assertEquals(0, calculadora.evaluar("0 0 +"));
    }
    
    @Test
    public void testResta() {
        assertEquals(2, calculadora.evaluar("5 3 -"));
        assertEquals(-2, calculadora.evaluar("3 5 -"));
        assertEquals(0, calculadora.evaluar("10 10 -"));
    }

    @Test
    public void testMultiplicacion() {
        assertEquals(15, calculadora.evaluar("3 5 *"));
        assertEquals(0, calculadora.evaluar("0 5 *"));
        assertEquals(-20, calculadora.evaluar("4 -5 *"));
    }

    @Test
    public void testDivision() {
        assertEquals(2, calculadora.evaluar("10 5 /"));
        assertEquals(3, calculadora.evaluar("15 5 /"));
        assertEquals(1, calculadora.evaluar("7 7 /"));
    }

    @Test
    public void testModulo() {
        assertEquals(1, calculadora.evaluar("10 3 %"));
        assertEquals(0, calculadora.evaluar("10 5 %"));
        assertEquals(2, calculadora.evaluar("17 5 %"));
    }

    @Test
    public void testExpresionCompleja1() {
        assertEquals(16, calculadora.evaluar("3 5 + 2 *"));
    }

    @Test
    public void testExpresionCompleja2() {
        assertEquals(3, calculadora.evaluar("15 7 2 - /"));
    }

    @Test
    public void testExpresionCompleja3() {
        assertEquals(12, calculadora.evaluar("5 3 + 2 * 4 -"));
    }

    @Test
    public void testExpresionCompleja4() {
        assertEquals(19, calculadora.evaluar("10 5 2 * + 3 3 / -"));
    }

    @Test
    public void testExpresionConNumerosNegativos() {
        assertEquals(-8, calculadora.evaluar("-3 -5 +"));
        assertEquals(2, calculadora.evaluar("-3 -5 -"));
    }


    // Assert Throws verifica que cierto mensaje se muestre cuando
    // un bloque de código se ejecute

    @Test
    public void testDivisionPorCero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.evaluar("5 0 /");
        });
        assertEquals("No se puede dividir entre cero", exception.getMessage());
    }

    @Test
    public void testModuloPorCero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.evaluar("10 0 %");
        });
        assertEquals("No se puede hacer módulo por cero", exception.getMessage());
    }

    @Test
    public void testOperandosInsuficientes() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.evaluar("5 +");
        });
        assertEquals("Operandos insuficientes", exception.getMessage());
    }

    @Test
    public void testCaracterInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.evaluar("5 3 &");
        });
        assertTrue(exception.getMessage().contains("Carácter inválido"));
    }

    @Test
    public void testExpresionMalFormada() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.evaluar("5 3 2 +");
        });
        assertEquals("Expresión postfix mal formada", exception.getMessage());
    }

    @Test
    public void testExpresionVacia() {
       // prueba para null
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.evaluar("");
        });
    }

    @Test
    public void testSoloOperador() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.evaluar("+");
        });
    }

    @Test
    public void testSoloNumero() {
        // Debería retornar el mismo numero
        assertEquals(42, calculadora.evaluar("42"));
    }

    @Test
    public void testCero() {
        assertEquals(0, calculadora.evaluar("0"));
        assertEquals(5, calculadora.evaluar("0 5 +"));
        assertEquals(0, calculadora.evaluar("5 5 -"));
    }
}
