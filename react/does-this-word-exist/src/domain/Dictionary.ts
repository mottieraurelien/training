import Trie from "./structure/Trie";
import Spliterator from "./structure/Spliterator";

export default class Dictionary {

    private static readonly spliterator: Spliterator<string> = new Spliterator<string>(
        (word: string): string[] => {
            return word.split("");
        }
    );

    private readonly trie: Trie<string>;

    constructor(value: string[]) {
        this.trie = new Trie<string>(Dictionary.spliterator, value);
    }

    public contains(word: string): boolean {
        return this.trie.contains(word);
    }

}