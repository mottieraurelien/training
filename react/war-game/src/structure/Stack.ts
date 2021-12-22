/**
 * The Stack data structure does not exist natively, so let's implement a basic version of it.
 *
 * Reminder : a stack relies on the LIFO principle.
 * Example, stack of plates : the plate I put on top of the stack will be the first one to leave the stack.
 */
export default class Stack<T> {

    private readonly items: T[];

    constructor(items: T[]) {
        this.items = items;
    }

    push(item: T): void {
        if (!item) throw new Error("A valid item must be provided.");
        this.items.push(item);
    }

    pop(): T {
        const item: T | undefined = this.items.pop();
        if (!item) throw new Error("Cannot pop any item from an empty stack.");
        return item;
    }

    isNotEmpty(): boolean {
        return this.items.length > 0;
    }

}