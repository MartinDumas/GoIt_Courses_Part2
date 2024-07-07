package org.example.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) {
        ExecutorService  executorService = Executors.newFixedThreadPool(2);
        waitingTimeThread(executorService);


    }

    private static void waitingTimeThread(ExecutorService executorService) {
        executorService.execute(()-> {
            long currentTime = System.currentTimeMillis();
            while (true){
                
                try {
                    long elapsedTime = (System.currentTimeMillis() - currentTime)/1000;
                    System.out.println("Минуло " + elapsedTime + " секунд");
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        executorService.execute(() -> {
            try {
                while (true){System.out.println("5 seconds have been past");
                    executorService.awaitTermination(5, TimeUnit.SECONDS);}

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
