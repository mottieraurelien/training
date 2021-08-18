import React from "react";
import useApi from "../hooks/useApi";

export default function Users() {

    const {data: users, error} = useApi({
        method: "GET",
        url: "users/"
    });

    if (error) return <span>{error}</span>

    return <div>
        <ul>
            {users?.map(function (user: any) {
                return <li key={user.id}>
                    {user.name}
                </li>
            })}
        </ul>
    </div>

}