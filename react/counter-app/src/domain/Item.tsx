export default class Item {

    id: number;
    quantity: number;

    constructor(id: number, initialQuantity: number) {
        this.id = id;
        this.quantity = initialQuantity;
    }

    format() {
        return "badge m-2 badge-" + (this.quantity === 0 ? "warning" : "primary");
    }

    label() {
        return this.quantity === 0 ? "Zero" : this.quantity;
    }

    isDisabled() {
        return this.quantity === 0;
    }

}