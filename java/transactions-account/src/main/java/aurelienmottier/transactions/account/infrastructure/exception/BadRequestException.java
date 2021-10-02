package aurelienmottier.transactions.account.infrastructure.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(final String message) {
        super(message);
    }

}