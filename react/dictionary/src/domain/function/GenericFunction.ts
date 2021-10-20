export default interface GenericFunction<I, O> {

    apply(input: I): O;

}