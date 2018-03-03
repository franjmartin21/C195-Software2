package threads.siete_dos;

import java.util.concurrent.*;

public class SchedulerExecutorServiceExample {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        Runnable task1 = () -> System.out.println("Hello Zoo");
        Callable<String> task2 = () -> "Monkey";

        Future<?> result1 = service.schedule(task1, 10, TimeUnit.SECONDS);
        Future<?> result2 = service.schedule(task2, 8, TimeUnit.MINUTES);
    }
}
