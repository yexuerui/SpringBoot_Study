package com.test;

import java.util.concurrent.CountDownLatch;

public class TestClient implements Runnable {

    private TestThreadLocal local;
    private CountDownLatch latch;
    private String threadName;

    public TestClient(String threadName) {
        this.threadName = threadName;
    }

    public TestClient(TestThreadLocal local, CountDownLatch latch, String threadName) {
        this.local = local;
        this.latch = latch;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        latch.countDown();  //计数器-1
        System.out.println("计数器的数量："+latch.getCount());
//        local.set_encode(threadName);
        local.setOne(threadName);
        local.setRequestId("Thread:"+threadName);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(threadName+" : "+local.get_encode());
        System.out.println(threadName+" : "+local.getOne());
        System.out.println(threadName+" : "+local.getRequestId());
        local.remove();
    }

    public static void main(String[] args) {
        TestThreadLocal local = new TestThreadLocal();
        CountDownLatch latch = new CountDownLatch(5);

        TestClient testClient = new TestClient("Thread");
        for (int i = 0; i < 5; i++) {
            new Thread(new TestClient(local, latch, "Thread:" + i)).start();
        }

    }
}
