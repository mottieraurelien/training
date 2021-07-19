import React from "react";
import queryString from "query-string";

const Posts = ({match, location}) => {

    const {year, month} = match.params;
    const queryParams = queryString.parse(location.search);
    console.log(queryParams);

    return (
        <div>
            <h1>Posts</h1>
            Year: {year}, Month:{month}
        </div>
    );
};

export default Posts;
