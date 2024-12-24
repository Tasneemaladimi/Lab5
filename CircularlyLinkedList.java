public class CircularlyLinkedList<E> {
    private Node<E> tail = null; // Sentinel node

    public CircularlyLinkedList() { } // Constructs an initially empty list

    public int size() {
        int count = 0;
        Node<E> walk = tail;
        if (tail != null) {
            do {
                count++;
                walk = walk.getNext();
            } while (walk != tail);
        }
        return count;
    }

    public boolean isEmpty() {
        return tail == null;
    }

    public E first() {
        if (isEmpty()) return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

    public void addFirst(E e) {
        if (isEmpty()) {
            tail = new Node<>(e, tail); // Initially tail.getNext() == tail
        } else {
            tail.setNext(new Node<>(e, tail.getNext()));
        }
    }

    public void addLast(E e) {
        addFirst(e); // Simply rotate to make new node the tail
        tail = tail.getNext();
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail) // Only one node
            tail = null;
        else
            tail.setNext(head.getNext());
        return head.getElement();
    }

    // Homework 3: equals() method
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircularlyLinkedList<?> other = (CircularlyLinkedList<?>) o;

        if (size() != other.size()) return false;

        Node<E> walkA = this.tail.getNext();
        Node<?> walkB = other.tail.getNext();
        do {
            if (!walkA.getElement().equals(walkB.getElement())) {
                return false;
            }
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        } while (walkA != this.tail.getNext());

        return true;
    }

    // Homework 4: Check if two lists store the same sequence
    public static <T> boolean haveSameSequence(CircularlyLinkedList<T> list1, CircularlyLinkedList<T> list2) {
        if (list1.size() != list2.size()) return false;

        Node<T> walk1 = list1.tail.getNext();
        Node<T> walk2 = list2.tail.getNext();

        do {
            if (!walk1.getElement().equals(walk2.getElement())) {
                return false;
            }
            walk1 = walk1.getNext();
            walk2 = walk2.getNext();
        } while (walk1 != list1.tail.getNext());

        return true;
    }

    // Homework 5: Split a list with even number of nodes
    public void split(CircularlyLinkedList<E> other) {
        if (size() % 2 != 0) {
            throw new IllegalArgumentException("List size must be even for splitting.");
        }

        Node<E> mid = this.tail;
        for (int i = 0; i < size() / 2; i++) {
            mid = mid.getNext();
        }

        other.tail = mid;
        this.tail.setNext(mid.getNext());
        mid.setNext(other.tail);
    }

    // Homework 6: clone() method
    public CircularlyLinkedList<E> clone() {
        CircularlyLinkedList<E> other = new CircularlyLinkedList<>();
        if (this.tail != null) {
            other.tail = new Node<>(this.tail.getElement(), null);
            Node<E> walk = this.tail.getNext();
            Node<E> walkOther = other.tail;

            while (walk != this.tail) {
                walkOther.setNext(new Node<>(walk.getElement(), null));
                walkOther = walkOther.getNext();
                walk = walk.getNext();
            }
            walkOther.setNext(other.tail);
        }
        return other;
    }
}