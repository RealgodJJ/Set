public class LinkedList<T> {
    private class Node {
        T e;
        Node next;

        public Node(T e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(T e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    //构造初始化一个链表
    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addMiddle(int index, T e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add Failed. The index is illegal.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(T e) {
        addMiddle(0, e);
    }

    public void addLast(T e) {
        addMiddle(size, e);
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get Failed. The index is illegal.");

        Node current = dummyHead.next;
        for (int i = 0; i < index; i++)
            current = current.next;

        return current.e;
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size - 1);
    }

    public void set(T e, int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set Failed. The index is Illegal.");

        Node current = dummyHead.next;
        for (int i = 0; i < index; i++)
            current = current.next;

        current.e = e;
    }

    public boolean contains(T e) {
//        Node current = dummyHead.next;
//
//        while (current != null) {
//            if (current.e.equals(e))
//                return true;
//            current = current.next;
//        }

        for (Node current = dummyHead.next; current != null; current = current.next)
            if (current.e.equals(e))
                return true;
        return false;
    }

    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. The index is illegal.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        Node deleteNode = prev.next;
        prev.next = deleteNode.next;
        deleteNode.next = null;
        size--;

        return deleteNode.e;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除元素e
    public void removeElement(T e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Node current = dummyHead.next;
        while (current != null) {
            stringBuilder.append(current.e);
            if (current.next != null)
                stringBuilder.append("->");
            else
                stringBuilder.append("-> NULL");
            current = current.next;
        }

        return stringBuilder.toString();
    }
}
