import React from "react";

interface Context {
    user: any,
    theme: string,
    language: string
}

const UserContext = React.createContext<Context>({user: {}, theme: "", language: ""});
UserContext.displayName = "UserContext";

export default UserContext;