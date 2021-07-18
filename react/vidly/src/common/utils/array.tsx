import Sorting from "../table/domain/sorting";

export function range(size: number, startAt: number = 0): number[] {
    return [...Array(size).keys()].map(i => i + startAt);
}

/**
 * Works for a property on the first or second level, not deeper (no need for our exercise).
 * Not applicable to multiple columns sorting.
 */
export function orderBy(sorting: Sorting) {

    return function innerSort(object: any, anotherObject: any) {

        let first: any = object;
        let second: any = anotherObject;
        let property: string = sorting.property;

        const index: number = property.indexOf(".");
        if (index > -1) {
            const firstProperty: string = property.substring(0, index);
            const secondProperty: string = property.substring(index + 1);
            first = object[firstProperty];
            second = anotherObject[firstProperty];
            property = secondProperty;
        }

        let comparison = 0;
        if (first[property] > second[property])
            comparison = 1;
        else if (first[property] < second[property])
            comparison = -1;

        return sorting.order === 'asc' ? comparison : comparison * -1;

    };
}