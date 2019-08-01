public interface Set<T> {
    int getSize();

    boolean isEmpty();

    void add(T e);

    void remove(T e);

    boolean contain(T e);
}
