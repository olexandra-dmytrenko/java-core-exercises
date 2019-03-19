package ua.procamp;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
    private Node first;
    private Node last;
    private int size = 0;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        List<T> result = new LinkedList<>();
        for (int i = 0; i < elements.length; i++) {
            result.add(elements[i]);
        }
        return result;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if (size == 0) {
            Node<T> newNode = new Node<>(null, null, element);
            this.first = newNode;
            last = this.first;
        } else {
            Node<T> newLast = new Node<>(last, null, element);
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            Node<T> newNode = new Node<>(null, first, element);
            first = newNode;
        } else if (index == size) {
            Node<T> newNode = new Node<>(last, null, element);
            last.next = newNode;
            last = newNode;
        } else {
            Node<T> currentEl = getNodeByIndex(index);
            Node<T> newNode = new Node<>(currentEl.previous, currentEl, element);
            currentEl.previous.next = newNode;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> curNode = getNodeByIndex(index);
        curNode.content = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if (index >= size || size <= 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentEl = getNodeByIndex(index);
        return currentEl.content;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {
        if (index >= size || size == 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            first = first.next;
            first.previous = null;
        } else if (index == size - 1) {
            last = last.previous;
            last.next = null;
        } else {
            Node<T> currentEl = getNodeByIndex(index);
            currentEl.previous.next = currentEl.next;
            currentEl.next.previous = currentEl.previous;
        }
        size--;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node currentEl = first;

        for (int i = 0; i < size; i++) {
            if (currentEl.content.equals(element))
                return true;
            currentEl = currentEl.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> currentEl = first;
        for (int i = 0; i < index; i++) {
            currentEl = currentEl.next;
        }
        return currentEl;
    }

    private static class Node<T> {

        Node<T> previous;
        Node<T> next;
        T content;

        public Node(Node<T> previous, Node<T> next, T content) {
            this.previous = previous;
            this.next = next;
            this.content = content;
        }
    }
}
