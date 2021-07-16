import React from "react";
import Group from "../domain/Group";

export default function Groups({...props}) {

    const {groups, selectGroup, selectedGroupId} = props;

    const inactiveGroupClasses: string = "list-group-item list-group-item-action";
    const activeGroupClasses: string = inactiveGroupClasses + " active";

    return (
        <div className="list-group">
            <button type="button"
                    onClick={() => selectGroup(null)}
                    className={!selectedGroupId ? activeGroupClasses : inactiveGroupClasses}>
                All
            </button>
            {groups.map(function (group: Group) {
                return <button key={group.getId()} type="button"
                               onClick={() => selectGroup(group.getId())}
                               className={group.getId() === selectedGroupId ? activeGroupClasses : inactiveGroupClasses}>
                    {group.getLabel()}
                </button>
            })
            }
        </div>
    )

}