export default class TrieNode<T> {

    private readonly children: Map<T, TrieNode<T>>;

    constructor() {
        this.children = new Map<T, TrieNode<T>>();
    }

    public getChild(childValue: T): TrieNode<T> | undefined {
        return this.children.get(childValue);
    }

    public add(childValue: T): TrieNode<T> {
        const childNode: TrieNode<T> = new TrieNode<T>();
        this.children.set(childValue, childNode);
        return childNode;
    }

}