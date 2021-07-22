export enum SortingOrder {
    ASCENDING = "asc",
    DESCENDING = "desc",
    DEFAULT = "asc"
}

export default class Sorting {

    property: string;
    order: SortingOrder;

    constructor(property: string, order: SortingOrder) {
        this.property = property;
        this.order = order;
    }

    getProperty(): string {
        return this.property;
    }

    getOrder(): SortingOrder {
        return this.order;
    }

    isAsc() {
        return this.order === SortingOrder.ASCENDING;
    }

    isDesc() {
        return this.order === SortingOrder.DESCENDING;
    }

    toggle() {
        if (this.isAsc()) {
            this.order = SortingOrder.DESCENDING;
        } else if (this.isDesc()) {
            this.order = SortingOrder.ASCENDING;
        }
    }

}