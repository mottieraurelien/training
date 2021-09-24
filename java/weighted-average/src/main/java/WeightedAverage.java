public class WeightedAverage {

    private final int[] numbers;
    private final int[] weights;

    public WeightedAverage(final int[] numbers, final int[] weights) {
        this.numbers = numbers;
        this.weights = weights;
    }

    public double mean() {

        if (this.numbers == null || this.weights == null) throw new IllegalArgumentException();
        if (this.numbers.length != this.weights.length) throw new IllegalArgumentException();

        double total = 0;
        double totalWeight = 0;

        for (int i = 0; i < this.numbers.length; i++) {
            total += this.numbers[i] * this.weights[i];
            totalWeight += this.weights[i];
        }

        return total / totalWeight;
    }

}