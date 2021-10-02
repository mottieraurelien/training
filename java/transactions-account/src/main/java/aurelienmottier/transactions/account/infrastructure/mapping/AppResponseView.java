package aurelienmottier.transactions.account.infrastructure.mapping;

/**
 * Defines a Jackson view in order to select on the fields we want to see
 * in the response payload (since it's not a good practice to expose the
 * whole entities).
 */
public class AppResponseView {

    public interface Public {
    }

}