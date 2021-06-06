import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class StackList<T> {
    private LinkedList<T> linkedList = new LinkedList<T>();

    public StackList() {}

    public StackList(T elem) {
        push(elem);
    }

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(T elem) {
        linkedList.addLast(elem);
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();

        return linkedList.removeLast();
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();

        return linkedList.peekLast();
    }

    public int search(T elem) {
        return linkedList.lastIndexOf(elem);
    }

    public Iterator<T> iterator() {
        return linkedList.iterator();
    }
}
