public class BSTSet<T extends Comparable<T>> implements Set<T> {
    private BST<T> bstSet;

    public BSTSet() {
        bstSet = new BST<>();
    }

    @Override
    public int getSize() {
        return bstSet.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bstSet.isEmpty();
    }

    @Override
    public void add(T e) {
        bstSet.add(e);
    }

    @Override
    public void remove(T e) {
        bstSet.remove(e);
    }

    @Override
    public boolean contain(T e) {
        return false;
    }
}
