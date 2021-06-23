package kata.data;

import java.util.Objects;

import static java.util.Objects.hash;

public class Pair<L, R> {

    private final L left;
    private final R right;

    private Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Pair<L, R> of(final L left, final R right) {
        return new Pair<>(left, right);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final Pair<?, ?> anotherPair = (Pair<?, ?>) object;
        return Objects.equals(left, anotherPair.left) && Objects.equals(right, anotherPair.right);
    }

    @Override
    public int hashCode() {
        return hash(left, right);
    }

}