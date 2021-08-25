import React, {ChangeEvent, FormEvent, useMemo, useState} from "react";

export default function CommentList() {

    const [buffer, setBuffer] = useState<string>("");
    const [comments, setComments] = useState<string[]>([]);

    const disablePost: boolean = useMemo(() => !buffer, [buffer]);

    const handleChangeBuffer = ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {
        const {value} = input;
        setBuffer(value);
    }

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {

        // Prevents the default behaviour of this event which is in our case submitting the data
        // to the server that causes a full page reload (which is something we want to avoid here) :
        event.preventDefault();

        const newComments: string[] = [...comments, buffer];
        setComments(newComments);
        setBuffer("");

    }

    return <React.Fragment>
        <form onSubmit={handleSubmit}>
            <input type="text" value={buffer} onChange={handleChangeBuffer}/>
            <input type="submit" value="Post" disabled={disablePost}/>
        </form>
        <ul>
            {
                comments.map(function (comment: string, index: number) {
                    return <li key={index}>{comment}</li>
                })
            }
        </ul>
    </React.Fragment>
}