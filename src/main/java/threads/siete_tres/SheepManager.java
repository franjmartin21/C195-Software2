package threads.siete_tres;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager {
    //private int sheepCount = 0;//not thread-safe
    private AtomicInteger sheepCount = new AtomicInteger(0);
    private final Object lock = new Object();
    private void incrementAndReport() {
        //System.out.print((++sheepCount)+" ");//not thread-safe
        synchronized(lock) {
            System.out.print(sheepCount.incrementAndGet() + " ");
        }
    }
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager manager = new SheepManager();
            for(int i=0; i<10; i++)
                service.submit(() -> manager.incrementAndReport());
        } finally {
            if(service != null) service.shutdown();
        }
    }
}