public class MovingTotal {

    public void append(final int[] numbers) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    public boolean contains(final int total) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    public static void main(final String[] args) {

        final MovingTotal movingTotal = new MovingTotal();

        movingTotal.append(new int[]{1, 2, 3, 4});

        System.out.println(movingTotal.contains(6));
        System.out.println(movingTotal.contains(9));
        System.out.println(movingTotal.contains(12));
        System.out.println(movingTotal.contains(7));

        movingTotal.append(new int[]{5});

        System.out.println(movingTotal.contains(6));
        System.out.println(movingTotal.contains(9));
        System.out.println(movingTotal.contains(12));
        System.out.println(movingTotal.contains(7));

    }

}