import Identifiable from "./identifiable";

export default interface Row extends Identifiable {

    getContent(): any;

}