public class CalculadoraPostfix implements Calculadora {

    private Pila<Integer> pila;

    public CalculadoraPostfix() {
        pila = new VectorPila<>();
    }

    private boolean esOperador(String componente) {
        return componente.equals("+") || componente.equals("-") ||
                componente.equals("*") || componente.equals("/") ||
                componente.equals("%");
    }

    private boolean esNumero(String componente) {
        try {
            Integer.parseInt(componente);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int realizarOperacion(int a, int b, String operador) {
        if (operador.equals("+")) {
            return a + b;
        } else if (operador.equals("-")) {
            return a - b;
        } else if (operador.equals("*")) {
            return a * b;
        } else if (operador.equals("/")) {
            if (b == 0) {
                throw new IllegalArgumentException("No se puede dividir entre cero");
            }
            return a / b;
        } else if (operador.equals("%")) {
            if (b == 0) {
                throw new IllegalArgumentException("No se puede hacer módulo por cero");
            }
            return a % b;
        } else {
            throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }

    @Override
    public int evaluar(String expresion) {
        String[] componentes = expresion.split(" ");

        for (String componente : componentes) {
            if (esNumero(componente)) {
                pila.push(Integer.parseInt(componente));
            } else if (esOperador(componente)) {
                if (pila.size() < 2) {
                    throw new IllegalArgumentException("Operandos insuficientes");
                }
                int b = pila.pop();
                int a = pila.pop();
                int resultado = realizarOperacion(a, b, componente);
                pila.push(resultado);
            } else {
                throw new IllegalArgumentException(
                        "Carácter inválido en la expresión: " + componente);
            }
        }

        if (pila.size() != 1) {
            throw new IllegalArgumentException(
                    "Expresión postfix mal formada");
        }

        return pila.pop();
    }
}
