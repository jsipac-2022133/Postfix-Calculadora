import java.util.ArrayList;

public class VectorPila<T> implements Pila<T> {

    ArrayList<T> elementos = new ArrayList<>();

    @Override
    public void push(T elemento) {
        elementos.add(elemento);
    }

    @Override
    public T pop() {
        T valor = elementos.get(elementos.size() - 1);
        elementos.remove(elementos.size() - 1);
        return valor;
    }

    @Override
    public T peek() {
        T valor = elementos.get(elementos.size() - 1);
        return valor;
    }

    @Override
    public boolean isEmpty() {
        if (elementos.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        int cantidad = elementos.size();
        return cantidad;
    }
}
