public class Relation<T> {

    private final T parent;
    private final T child;

    public Relation(final T parent, final T child) {
        this.parent = parent;
        this.child = child;
    }

    T getParent() {
        return this.parent;
    }

    T getChild() {
        return this.child;
    }

}