import java.util.*;

import static java.util.List.of;

public class PrintTree<T> {

    private static final String INITIAL_PREFIX = "";
    private static final String SPACE = " ";

    private T root;
    private final List<Relation<T>> relations;
    private final Map<T, Set<T>> graph;

    public PrintTree(final List<Relation<T>> relations) {
        if (relations == null || relations.isEmpty()) throw new IllegalArgumentException("No thanks.");
        this.relations = relations;
        this.root = this.relations.get(0).getParent();
        this.graph = new HashMap<>();
    }

    List<String> process() {
        this.parse();
        return this.format();
    }

    private void parse() {
        for (final Relation<T> relation : this.relations) {
            final T parent = relation.getParent();
            final T child = relation.getChild();
            if (!this.graph.containsKey(parent)) this.graph.put(parent, new HashSet<>(of(child)));
            else this.graph.get(parent).add(child);
            if (this.root.equals(child)) this.root = parent;
        }
    }

    private List<String> format() {
        final List<String> values = new ArrayList<>();
        this.dfs(values, this.root, INITIAL_PREFIX);
        return values;
    }

    private void dfs(final List<String> values, final T node, final String prefix) {
        if (node == null) return;
        add(values, prefix, node.toString());
        final Set<T> children = graph.get(node);
        if (children == null || children.isEmpty()) return;
        for (final T child : children) this.dfs(values, child, prefix + SPACE);
    }

    private static void add(final List<String> values, final String prefix, final String value) {
        values.add(prefix + value);
    }

}