export default class ValidationReport {

    errorMessages: any;

    constructor(errorMessages?: any) {
        this.errorMessages = errorMessages || {};
    }

    push(property: any, errorMessage: string) {
        this.errorMessages[property] = errorMessage;
    }

    remove(property: any) {
        delete this.errorMessages[property];
    }

    getErrorMessage(property: any): string {
        return this.errorMessages[property] || "";
    }

    hasAnError(): boolean {
        const keys: any[] = Object.keys(this.errorMessages);
        if (keys.length === 0) return false;
        return keys.find(property => !!this.errorMessages[property]);
    }

    getErrorMessages(): any {
        return this.errorMessages;
    }

}