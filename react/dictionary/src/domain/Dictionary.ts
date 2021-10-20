import Trie from "./structure/Trie";
import Spliterator from "./function/Spliterator";
import Joiner from "./function/Joiner";

/**
 * Acts like a facade for the trie (this dictionary hides the complexity embedded in the trie).
 */
export default class Dictionary {

    private static readonly EMPTY_STRING: string = "";

    public static readonly SPLITERATOR: Spliterator<string> = new Spliterator<string>(
        (word: string): string[] => {
            return word.split(Dictionary.EMPTY_STRING);
        }
    );

    public static readonly JOINER: Joiner<string> = new Joiner<string>(
        (values: string[]): string => {
            return values.join(Dictionary.EMPTY_STRING);
        }
    );

    private readonly trie: Trie<string>;

    constructor(words: string[]) {
        this.trie = new Trie<string>(Dictionary.SPLITERATOR, Dictionary.JOINER, words, Dictionary.EMPTY_STRING);
    }

    public contains(word: string): boolean {
        return this.trie.contains(word);
    }

    public suggestions(prefix: string, maximumNumberOfSuggestions: number): string[] {
        return this.trie.suggestions(prefix, maximumNumberOfSuggestions);
    }

    public getNumberOfNodes(): number {
        return this.trie.getNumberOfNodes();
    }

    public getNumberOfWords(): number {
        return this.trie.getNumberOfEnds();
    }

}