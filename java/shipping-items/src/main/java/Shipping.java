import static java.lang.Math.min;

public class Shipping {

    private static final int ITEMS_PER_LARGE_PACKAGE = 5;
    private static final int ITEMS_PER_SMALL_PACKAGE = 1;

    public int minimalNumberOfPackages(final int items, final int availableLargePackages, final int availableSmallPackages) {

        final int maxNumberOfItems = availableLargePackages * ITEMS_PER_LARGE_PACKAGE + availableSmallPackages * ITEMS_PER_SMALL_PACKAGE;

        if (items > maxNumberOfItems) return -1;

        final int numberOfLargePackagesNeeded = min(items / ITEMS_PER_LARGE_PACKAGE, availableLargePackages);
        final int remainingItemsToPack = items - numberOfLargePackagesNeeded * ITEMS_PER_LARGE_PACKAGE;
        final int minimalNumberOfPackagesNeeded = remainingItemsToPack / ITEMS_PER_SMALL_PACKAGE;

        return numberOfLargePackagesNeeded + minimalNumberOfPackagesNeeded;
    }

    public static void main(final String[] args) {
        final Shipping shipping = new Shipping();
        System.out.println(shipping.minimalNumberOfPackages(16, 2, 10));
    }

}