package com.threads;

import static com.threads.ThreadColor.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "Hi from the main thread");
        Thread anotherThread = new AnotherThread();
        anotherThread.setName("== Another Thread ==");
        anotherThread.start();

        new Thread(() -> System.out.println(ANSI_GREEN + "Hello from the anonymous class run()")).start();

        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ANSI_CYAN + "Hello from the anonymous class of MyRunnable");
                try {
                    anotherThread.join();
                    System.out.println(ANSI_RED + "Another thread terminated so I am running again");
                } catch(InterruptedException e) {
                    System.out.println(ANSI_RED + "I couldn't wait after all. I was interrupted");
                }
            }
        });
        myRunnableThread.start();
        anotherThread.interrupt();
        System.out.println(ANSI_PURPLE + "Hello again from the main thread");
    }
}
