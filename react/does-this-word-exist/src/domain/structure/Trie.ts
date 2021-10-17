import TrieNode from "../data/TrieNode";
import Spliterator from "./Spliterator";

export default class Trie<T> {

    private readonly root: TrieNode<T>;
    private readonly spliterator: Spliterator<T>;

    constructor(spliterator: Spliterator<T>, values: T[]) {
        this.root = new TrieNode<T>();
        this.spliterator = spliterator;
        this.load(values);
    }

    public add(value: T) {
        let node = this.root;
        const pieces: T[] = this.spliterator.apply(value);
        for (const piece of pieces) {
            const child = node.getChild(piece);
            if (child) node = child;
            else node = node.add(piece);
        }
    }

    public contains(value: T): boolean {
        if (!value) return false;
        let node: TrieNode<T> = this.root;
        const pieces: T[] = this.spliterator.apply(value);
        for (const piece of pieces) {
            const child = node.getChild(piece);
            if (child) node = child
            else return false;
        }
        return true;
    }

    private load(values: T[]) {
        for (const value of values) {
            this.add(value);
        }
    }

}
