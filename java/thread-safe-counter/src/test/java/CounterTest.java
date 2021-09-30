import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {

    @Test
    void should_return_two_when_using_the_counter_in_two_different_thread() throws Exception {

        // [Arrange] Two Counter instances > to make sure these objects use the same AtomicInteger value (static).
        final FirstTask firstTask = new FirstTask();
        final SecondTask secondTask = new SecondTask();
        final ExecutorService executor = Executors.newFixedThreadPool(2);

        // [Act]
        // If there is an error during the execution > the test will fail.
        executor.invokeAll(asList(firstTask, secondTask));
        executor.shutdown();

        // [Assert]
        // FirstTask : increments the counter by 1 (without knowing which one exactly, can be 1, 2 or 3).
        // SecondTask : increments the counter by 1 and then 1 (without knowing which ones exactly, can be 2/3, 1/3 or 1/2).
        final int expectedLastValueConsumedByOneOfTheTasks = 3;
        assertThat(Counter.get()).isEqualTo(expectedLastValueConsumedByOneOfTheTasks);

    }

    static class FirstTask implements Callable<Integer> {

        @Override
        public Integer call() {
            System.out.println("First task calls counter and gets : " + Counter.incrementAndGet());
            return Counter.get();
        }

    }

    static class SecondTask implements Callable<Integer> {

        @Override
        public Integer call() {
            System.out.println("Second task calls counter and gets : " + Counter.incrementAndGet());
            System.out.println("Let's say that the second task does it another time : " + Counter.incrementAndGet());
            return Counter.get();
        }

    }

}