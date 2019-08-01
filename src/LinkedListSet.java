public class LinkedListSet<T> implements Set<T> {
    private LinkedList<T> linkedList;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void add(T e) {
        if (!linkedList.contains(e))
            linkedList.addFirst(e);
    }

    @Override
    public void remove(T e) {
        linkedList.removeElement(e);
    }

    @Override
    public boolean contain(T e) {
        return linkedList.contains(e);
    }
}
