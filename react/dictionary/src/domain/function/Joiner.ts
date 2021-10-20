import GenericFunction from "./GenericFunction";

export default class Joiner<T> implements GenericFunction<T[], T> {

    private readonly joiner: Function;

    constructor(joiner: Function) {
        this.joiner = joiner;
    }

    apply(input: T[]): T {
        return this.joiner.apply(this, [input]);
    }

}