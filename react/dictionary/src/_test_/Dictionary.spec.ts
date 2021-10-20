import Spliterator from "../domain/function/Spliterator";
import Dictionary from "../domain/Dictionary";
import Joiner from "../domain/function/Joiner";

describe("Technical requirements to fulfill in order to implement the dictionary.", () => {

    test("01 - Should return letters when splitting a word.", () => {

        // [Arrange]
        const word: string = "testing";
        const spliterator: Spliterator<string> = Dictionary.SPLITERATOR;

        // [Act]
        const actualLetters: string[] = spliterator.apply(word);

        // [Assert]
        const expectedLetters: string[] = ["t", "e", "s", "t", "i", "n", "g"];
        expect(actualLetters).toStrictEqual(expectedLetters);

    });

    test("02 - Should return a word when joining letters.", () => {

        // [Arrange]
        const letters: string[] = ["t", "e", "s", "t", "i", "n", "g"];
        const joiner: Joiner<string> = Dictionary.JOINER;

        // [Act]
        const actualWord: string = joiner.apply(letters);

        // [Assert]
        const expectedWord: string = "testing";
        expect(actualWord).toStrictEqual(expectedWord);

    });

    // 03 - contains

    // 04 - suggestions

});