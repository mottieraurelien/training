import React from "react";

interface Props {
    value: string;
}

export default function Comment({value}: Props) {
    return <li>{value}</li>
}