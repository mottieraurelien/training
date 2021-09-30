/**
 * Usually we would simply use an AtomicInteger, it's not the purpose of this exercise though.
 * (=> writing a unit test to check a very old-school thread-safe counter).
 */
public class Counter {

    private static int value = 0;

    public static synchronized int incrementAndGet() {
        value++;
        return value;
    }

    public static synchronized int get() {
        return value;
    }

}