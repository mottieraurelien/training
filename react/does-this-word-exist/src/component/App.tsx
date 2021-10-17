import React, {ChangeEvent, useState} from 'react';
import Dictionary from '../domain/Dictionary';
import './App.css';

export default function App() {

    const [dictionary, setDictionary] = useState<Dictionary>();
    const [word, setWord] = useState<string>("");
    const [wordExists, setWordExists] = useState<boolean>();

    const change = ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {

        // Saves the user input :
        const {value} = input;
        setWord(value);

        // We don't prevent the user from writing a word but we can't look for anything.
        if (!dictionary) return;

        // Checks if the word exists :
        const start: number = performance.now();
        const result: boolean = dictionary.contains(value);
        const end: number = performance.now();
        console.log("Word found in " + (end - start).toFixed(0) + "ms");

        // Displays the result (and the link) :
        setWordExists(result);

    }

    const load = async ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {
        if (!input.files || !input.files[0]) return;
        const file: File = input.files[0];
        const content = await file.text();
        const words: string[] = content.split("\n");
        const start: number = performance.now();
        setDictionary(new Dictionary(words));
        const end: number = performance.now();
        console.log("Loaded in " + (end - start).toFixed(0) + "ms");
    }

    /*
    const open = () => {
        // https://dictionary.cambridge.org/dictionary/english/testing
    }
    */

    return (
        <div className="dictionary">
            <input className="dictionary-upload" type="file" accept=".txt" onChange={load}/>
            <div className="dictionary-header">
                <h1>Does this word exist?</h1>
            </div>
            <div className="dictionary-body">
                <input className="dictionary-input" type="text" onChange={change} value={word}/>
            </div>
            {wordExists === true && <h1>OK!</h1>}
        </div>
    );

}