package cc.eslink;

import cc.eslink.util.HttpRequest;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@ClassName Test
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/22 18:31
 *@Version 1.0
 **/
public class Test {

    private static final ThreadLocal TL = new ThreadLocal();

    private static final String save_url = "http://127.0.0.1:8080/user/save";

    @org.junit.Test
    public void test() throws InterruptedException {
        System.out.println("开始执行");
        AtomicInteger success = new AtomicInteger(0);
        int loop = 1000;
        CountDownLatch latch = new CountDownLatch(loop);
        for (int i = 0; i < loop; i++) {
            int finalI = i + 1;
            new Thread(() -> {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    HttpRequest.postMethodURL(save_url, null);
                    success.getAndIncrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("第" + finalI + "次请求");
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.out.println(String.format("执行结束，执行%d次，成功%d次。", loop, success.get()));
    }

    @org.junit.Test
    public void test2() {
        while (true) {
            TL.set(new Date());
            TL.remove();
        }
    }

    @org.junit.Test
    public void test3() {
        AtomicInteger success = new AtomicInteger(0);
        while (true) {
            new Thread(() -> {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    HttpRequest.postMethodURL(save_url, null);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("第" + success.incrementAndGet() + "次请求");
                }
            }).start();
        }
    }
}
