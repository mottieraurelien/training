import GenericFunction from "./GenericFunction";

export default class Spliterator<T> implements GenericFunction<T, T[]> {

    private readonly spliterator: Function;

    constructor(spliterator: Function) {
        this.spliterator = spliterator;
    }

    apply(input: T): T[] {
        return this.spliterator.apply(this, [input]);
    }

}