export default class TrieNode<T> {

    private isAnEnd: boolean;
    private readonly value: T;
    private readonly children: Map<T, TrieNode<T>>;

    constructor(value: T) {
        this.value = value;
        this.isAnEnd = false;
        this.children = new Map<T, TrieNode<T>>();
    }

    public getValue(): T {
        return this.value;
    }

    public ifIsAnEnd(): boolean {
        return this.isAnEnd;
    }

    public isNowAnEnd() {
        this.isAnEnd = true;
    }

    public isNotAnEndAnymore() {
        this.isAnEnd = false;
    }

    public getTheOnlyChild(): TrieNode<T> {
        return this.children.entries().next().value;
    }

    public hasNotOnlyOneChild(): boolean {
        return this.isOrphanParent() || this.children.size > 1;
    }

    public isOrphanParent(): boolean {
        return this.children.size === 0;
    }

    public getChild(childValue: T): TrieNode<T> | undefined {
        return this.children.get(childValue);
    }

    public getChildren(): IterableIterator<TrieNode<T>> {
        return this.children.values();
    }

    public add(childValue: T): TrieNode<T> {
        const childNode: TrieNode<T> = new TrieNode<T>(childValue);
        this.children.set(childValue, childNode);
        return childNode;
    }

}