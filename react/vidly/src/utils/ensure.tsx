export default function ensure<T>(argument: T | undefined | null, message: string = 'The value must exist.'): T {
    if (argument === undefined || argument === null) {
        throw new TypeError(message);
    }
    return argument;
}