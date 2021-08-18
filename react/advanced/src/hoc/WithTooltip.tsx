import React, {useState} from "react";

export default function withTooltip(Component: any) {
    return function ComponentWithTooltip({...props}) {
        const [showTooltip, setShowTooltip] = useState<boolean>(false);
        const displayTooltip = () => setShowTooltip(true);
        const hideTooltip = () => setShowTooltip(false);
        return <div className="cursor-pointer" onMouseOver={displayTooltip} onMouseOut={hideTooltip}>
            <Component {...props} showTooltip={showTooltip}/>
        </div>
    }
}