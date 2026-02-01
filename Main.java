import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        CalculadoraPostfix calculadora = new CalculadoraPostfix();

        try {
            Scanner archivo = new Scanner(new File("datos.txt"));
            int numeroLinea = 1;

            System.out.println("---Claculadora Postfix---");
            System.out.println("");

            while (archivo.hasNextLine()) {
                String linea = archivo.nextLine().trim();

                if (linea.isEmpty()) {
                    continue;
                }
                System.out.print("Expresión " + numeroLinea + ": " + linea + " = ");

                try {
                    int resultado = calculadora.evaluar(linea);
                    System.out.println(resultado);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                numeroLinea++;
            }

            archivo.close();

        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo 'datos.txt'");
        }
    }
}