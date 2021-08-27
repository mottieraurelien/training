import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShippingTest {

    @Test
    void should_return_eight_when_computing_() {

        // [Arrange]
        final Shipping shipping = new Shipping();

        // [Act]
        final int actualNumberOfPackages = shipping.minimalNumberOfPackages(16, 2, 10);

        // [Assert]
        // [2 large ones => 10 items packed] + [6 small ones => 6 items packed] = [8 packages needed to ship 16 items].
        final int expectedNumberOfPackages = 8;
        assertThat(actualNumberOfPackages).isEqualTo(expectedNumberOfPackages);

    }

}