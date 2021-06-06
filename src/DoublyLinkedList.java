import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public Object next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    private static class Node<T> {
        private T data;
        private Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // Add an element to the tail of the list
    public void add(T elem) {
        addLast(elem);
    }

    // Add a node to the tail of the list
    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            Node<T> newNode = new Node<T>(elem, tail, null);
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    // Add an element to the beginning of the list
    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            Node<T> newNode = new Node<T>(elem, null, head);
            head.prev = newNode;
            head = head.prev;
        }
        size++;
    }

    // Add an element to the specified index
    public void addAt(int index, T data) throws Exception{
        if (index < 0) throw new Exception("Illegal index");
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        Node<T> newNode = new Node<T>(data, temp, temp.next);
        temp.next.prev = newNode;
        temp.next = newNode;

        size++;
    }

    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    // Remove the first element at the head of the list
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");

        T data = head.data;
        head = head.next;
        --size;

        if (isEmpty()) tail = null;
        else head.prev = null;

        return data;
    }

    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");

        T data = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty()) head = null;
        else tail.next = null;

        return data;
    }

    public T remove(Node<T> node) {
        if (node.prev == null) removeFirst();
        if (node.next == null) removeLast();

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;
        node.data = null;
        node.prev = node.next = null;
        --size;

        return data;
    }

    public T removeAt(int rem_index) {
        if (rem_index < 0 || rem_index >= size) throw new IllegalArgumentException("Invalid index");

        int i;
        Node<T> trav;
        if (rem_index < size / 2) {
            for (i = 0, trav = head; i != rem_index; i++) {
                trav = trav.next;
            }
        } else {
            for (i = size - 1, trav = tail; i != rem_index; i--) {
                trav = trav.prev;
            }
        }

        return remove(trav);
    }

    public boolean remove(Object obj) {
        Node<T> trav = head;
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav;

        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next, index++) {
                if (trav.data == null) {
                    return index;
                }
            }
        } else {
            for (trav = head; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data);
            if (trav.next != null) {
                sb.append(" -> ");
            }
            trav = trav.next;
        }
        return sb.append("]").toString();
    }
}
