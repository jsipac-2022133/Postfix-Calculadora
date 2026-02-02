package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import src.VectorPila;

public class VectorPilaTest {
    private VectorPila<Integer> pila;

    @Before  
    // Crea una pila nueva y vacía antes de cada prueba
    public void setUp() {
        pila = new VectorPila<>();
    }

    @Test
    // Test para comprobar que la pila crezca correctamente al hacer push
    public void testPushYSize() {
        assertEquals(0, pila.size());
        
        pila.push(10);
        assertEquals(1, pila.size());
        
        pila.push(20);
        assertEquals(2, pila.size());
        
        pila.push(30);
        assertEquals(3, pila.size());
    }

    @Test
    // Test para comprobar que al hacer pop se obtienen los elementos en orden LIFO
    // Los últimos salen de primero
    public void testPop() {
        pila.push(10);
        pila.push(20);
        pila.push(30);
        
        assertEquals(30, (int)pila.pop());
        assertEquals(2, pila.size());
        
        assertEquals(20, (int)pila.pop());
        assertEquals(1, pila.size());
        
        assertEquals(10, (int)pila.pop());
        assertEquals(0, pila.size());
    }

    @Test
    // Similar al test de pop, pero sin remover el elemento
    public void testPeek() {
        pila.push(10);
        pila.push(20);
        
        assertEquals(20, (int)pila.peek());
        assertEquals(2, pila.size());
        
        assertEquals(20, (int)pila.peek());
        assertEquals(2, pila.size());
    }

    @Test
    // Test para comprobar si la pila está vacía o no
    public void testIsEmpty() {
        assertTrue(pila.isEmpty());
        
        pila.push(10);
        assertFalse(pila.isEmpty());
        
        pila.pop();
        assertTrue(pila.isEmpty());
    }
    // Si se hace pop teniendo la pila vacía, debe lanzar una excepción
    @Test(expected = IndexOutOfBoundsException.class)
    public void testPopEnPilaVacia() {
        pila.pop();
    }
    // Lo mismo pero con peek
    @Test(expected = IndexOutOfBoundsException.class)
    public void testPeekEnPilaVacia() {
        pila.peek();
    }
    // assertTrue indica que la condición debe ser verdadera
    // Se hacen las mismas pruebas pero con Strings
    @Test
    public void testPilaConStrings() {
        VectorPila<String> pilaStrings = new VectorPila<>();
        
        assertTrue(pilaStrings.isEmpty());
        
        pilaStrings.push("Hola");
        pilaStrings.push("Mundo");
        
        assertEquals(2, pilaStrings.size());
        assertEquals("Mundo", pilaStrings.peek());
        assertEquals("Mundo", pilaStrings.pop());
        assertEquals("Hola", pilaStrings.pop());
        assertTrue(pilaStrings.isEmpty());
    }

    // Se usa Peek y Pop para comprobar que la pila aumente y disminuya correctamente
    @Test
    public void testMultiplesOperaciones() {
        pila.push(1);
        pila.push(2);
        assertEquals(2, (int)pila.pop());
        
        pila.push(3);
        pila.push(4);
        assertEquals(4, (int)pila.peek());
        assertEquals(3, pila.size());
        
        pila.pop();
        pila.pop();
        assertEquals(1, (int)pila.peek());
        assertEquals(1, pila.size());
    }

    // Pop para verificar el orden LIFO
    @Test
    public void testOrdenLIFO() {
        pila.push(1);
        pila.push(2);
        pila.push(3);
        pila.push(4);
        pila.push(5);
        
        assertEquals(5, (int)pila.pop());
        assertEquals(4, (int)pila.pop());
        assertEquals(3, (int)pila.pop());
        assertEquals(2, (int)pila.pop());
        assertEquals(1, (int)pila.pop());
        assertTrue(pila.isEmpty());
    }
}