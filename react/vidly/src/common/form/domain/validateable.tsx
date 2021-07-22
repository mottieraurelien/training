import Joi from "joi";
import ValidationReport from "./validationReport";

export default abstract class Validateable {

    private readonly schema: any;

    protected constructor(schema: any) {
        this.schema = schema;
    }

    validate(): ValidationReport {

        // Defines an entire validator for the whole object, and ignore the fields that are not concerned :
        const options: Joi.ValidationOptions = {abortEarly: false, allowUnknown: true};
        const validator: Joi.ObjectSchema = Joi.object(this.schema);

        // Runs the validation :
        const {schema, ...object} = this;
        const {error} = validator.validate(object, options);

        // Builds a new report (since we are going to validate the whole
        // object, we don't need to consider the old report) :
        const report: ValidationReport = new ValidationReport();

        // Extracts the errors (if any) :
        if (error) {
            const {details: errors} = error;
            errors.map(error => {
                const [name] = error.path;
                return report.push(name, error.message);
            })
        }

        return report;

    }

    validateOnly(input: HTMLInputElement, previousValidationReport?: ValidationReport): ValidationReport {

        const {name: propertyName, value} = input;

        // Defines a specific validator for our specific value (not the whole object) :
        const validator: Joi.ObjectSchema = Joi.object({[propertyName]: this.schema[propertyName]});

        // Runs the validation :
        const {error} = validator.validate({[propertyName]: value});

        // Clones the previous report to not remove the report on other properties :
        const report: ValidationReport = previousValidationReport ? new ValidationReport(previousValidationReport.getErrorMessages()) : new ValidationReport({});

        // Removes the old errors related to the property we want to check :
        report.remove(propertyName);

        // Extracts the error (if any) related to the property :
        if (error) {
            const {details: errors} = error;
            errors.map(error => {
                const [name] = error.path;
                return report.push(name, error.message);
            })
        }

        return report;

    }

}