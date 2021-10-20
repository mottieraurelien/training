import React, {ChangeEvent, useEffect, useState} from "react";
import {get} from "../infrastructure/client";
import Dictionary from '../domain/Dictionary';

interface Statistics {
    numberOfNodes?: number;
    numberOfWords?: number;
    sizeKo?: string;
    downloadingTimeMs?: string;
    readingTimeMs?: string;
    loadingTimeMs?: string;
}

/**
 * TODO 1 : triggering the OPEN method when selecting a suggestion from the dropdown.
 * TODO 2 : displaying statistics (somehow) so that the user has an idea about the dictionary size.
 * TODO 3 : writing unit tests for the classes in the domain package (at least).
 */
export default function App() {

    const [dictionary, setDictionary] = useState<Dictionary>();
    const [input, setInput] = useState<string>("");
    const [word, setWord] = useState<string | undefined>();
    const [suggestions, setSuggestions] = useState<string[]>();
    const [statistics, setStatistics] = useState<Statistics>();

    useEffect(() => {
        const download = async (statistics: Statistics): Promise<Blob> => {
            const url: string = "https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt";
            const start: number = performance.now();
            const {data: blob} = await get<Blob>(url, {responseType: "blob"});
            const end: number = performance.now();
            statistics["sizeKo"] = (blob.size / 1000).toFixed(0);
            statistics["downloadingTimeMs"] = (end - start).toFixed(0);
            return blob;
        }
        const read = async (blob: Blob, statistics: Statistics): Promise<string[]> => {
            const start: number = performance.now();
            const content = await blob.text();
            const words: string[] = content.split("\r\n");
            const end: number = performance.now();
            statistics["readingTimeMs"] = (end - start).toFixed(0);
            return words;
        }
        const load = (words: string[], statistics: Statistics): Dictionary => {
            const start: number = performance.now();
            const dic: Dictionary = new Dictionary(words);
            const end: number = performance.now();
            statistics["loadingTimeMs"] = (end - start).toFixed(0);
            statistics["numberOfNodes"] = dic.getNumberOfNodes();
            statistics["numberOfWords"] = dic.getNumberOfWords();
            return dic;
        }
        const init = async () => {
            const stats: Statistics = {};
            const blob: Blob = await download(stats);
            const words: string[] = await read(blob, stats);
            const dic: Dictionary = load(words, stats);
            setDictionary(dic);
            setStatistics(stats);
        }
        if (!dictionary) init();
    }, [dictionary])

    const change = ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {

        // Saves the user input :
        const {value} = input;
        setInput(value);

        // We don't prevent the user from writing a word but we can't look for anything.
        if (!dictionary) return;

        // Loads some suggestions :
        const suggestions: string[] = dictionary.suggestions(value, 5);
        setSuggestions(suggestions);

        // If the input is a real word :
        if (dictionary.contains(value)) setWord(value);
        else setWord(undefined);

    }

    const open = () => {
        if (!word) return;
        const definitionUrl: string = `https://dictionary.cambridge.org/dictionary/english/${word}`;
        const newTab = window.open(definitionUrl, '_blank', 'noopener,noreferrer');
        if (newTab) newTab.opener = null
    }

    return (
        <div className="dictionary">
            <div className="dictionary-body">
                <div className="dictionary-input-container">
                    <input list="input-suggestions-list"
                           className="dictionary-input"
                           type="text"
                           onChange={change}
                           value={input}
                           autoFocus/>
                </div>
                <div className="dictionary-suggestions-container">
                    <datalist id="input-suggestions-list">
                        {suggestions && suggestions.map((suggestion, key) =>
                            <option key={key} value={suggestion} className="dictionary-suggestion"/>
                        )}
                    </datalist>
                </div>
            </div>
        </div>
    );

}