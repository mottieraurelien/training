export enum SortingOrder {
    ASC = "asc",
    DESC = "desc",
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
        return this.order === SortingOrder.ASC;
    }

    isDesc() {
        return this.order === SortingOrder.DESC;
    }

    toggle() {
        if (this.isAsc()) {
            this.order = SortingOrder.DESC;
        } else if (this.isDesc()) {
            this.order = SortingOrder.ASC;
        }
    }

}