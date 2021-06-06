import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> {
    private int size;
    private int capacity;
    private Object[] data;

    public ArrayStack() {
        capacity = 10;
        data = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T elem) {
        if (size == capacity) {
            increaseCapacity();
        }
        data[size++] = elem;
    }

    public void increaseCapacity() {
        capacity *= 2;
        data = Arrays.copyOf(data, capacity);
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T elem = (T) data[--size];
        data[size] = null;
        return elem;
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return (T) data[size - 1];
    }
}
