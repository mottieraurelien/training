import Row from "./row";
import Identifiable from "./identifiable";
import Labelable from "./labelable";

export default class Column implements Identifiable, Labelable {

    _id: string;
    content: any;
    title?: string;
    sortable?: boolean;

    constructor(_id: string, content: any, title?: string, sortable?: boolean) {
        this._id = _id;
        this.content = content;
        this.title = title;
        this.sortable = sortable;
    }

    getKey(): string {
        return this._id;
    }

    getLabel(): string {
        return this.title || "";
    }

    getCellContent(entity: Row): any {
        return this.content(entity);
    }

    isSortable(): boolean {
        return this.sortable || false;
    }

}