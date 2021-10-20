import TrieNode from "../data/TrieNode";
import Spliterator from "../function/Spliterator";
import Joiner from "../function/Joiner";

export default class Trie<T> {

    private numberOfEnds: number;
    private numberOfNodes: number;

    private readonly root: TrieNode<T>;
    private readonly spliterator: Spliterator<T>;
    private readonly joiner: Joiner<T>;

    constructor(spliterator: Spliterator<T>, joiner: Joiner<T>, values: T[], root: T) {
        this.numberOfEnds = 0;
        this.numberOfNodes = 0;
        this.root = new TrieNode<T>(root);
        this.spliterator = spliterator;
        this.joiner = joiner;
        this.load(values);
    }

    public getNumberOfNodes(): number {
        return this.numberOfNodes;
    }

    public getNumberOfEnds(): number {
        return this.numberOfEnds;
    }

    public add(value: T) {
        let node = this.root;
        const pieces: T[] = this.spliterator.apply(value);
        for (const piece of pieces) {
            const child = node.getChild(piece);
            if (child) node = child;
            else node = node.add(piece);
            this.numberOfNodes++;
        }
        node.isNowAnEnd();
        this.numberOfEnds++;
    }

    public contains(input: T): boolean {
        if (!input) return false;
        const pieces: T[] = this.spliterator.apply(input);
        const node: TrieNode<T> | undefined = this.recursiveFindLastNodeOf(this.root, pieces, 0);
        return !!node && node.ifIsAnEnd();
    }

    private recursiveFindLastNodeOf(node: TrieNode<T>, pieces: T[], index: number): TrieNode<T> | undefined {

        if (pieces.length === index)
            return node;

        const piece: T = pieces[index];
        const child: TrieNode<T> | undefined = node.getChild(piece);

        if (!child) return undefined;

        return this.recursiveFindLastNodeOf(child, pieces, index + 1);
    }

    public suggestions(prefix: T, maximumNumberOfSuggestions: number): T[] {
        const suggestions: T[] = [];
        if (!prefix) return suggestions;
        const pieces: T[] = this.spliterator.apply(prefix);
        const node: TrieNode<T> | undefined = this.recursiveFindLastNodeOf(this.root, pieces, 0);
        if (!node || node.isOrphanParent()) return suggestions;
        this.recursiveSuggestions(suggestions, maximumNumberOfSuggestions, pieces, node, true);
        return suggestions;
    }

    private recursiveSuggestions(suggestions: T[], maximumNumberOfSuggestions: number, pieces: T[], node: TrieNode<T>, starting: boolean) {

        if (suggestions.length === maximumNumberOfSuggestions) return;

        pieces[pieces.length - (starting ? 1 : 0)] = node.getValue();

        if (node.ifIsAnEnd()) {
            const suggestion: T = this.joiner.apply(pieces);
            suggestions.push(suggestion);
        }

        for (const child of node.getChildren()) {
            this.recursiveSuggestions(suggestions, maximumNumberOfSuggestions, pieces, child, false);
        }

    }

    private load(values: T[]) {
        for (const value of values) {
            this.add(value);
        }
    }

}
