package com.yxr.myComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 从高到低 ERROR/WARN/INFO/DEBUG   若是定义的INFO的级别，那么DEBUG级别的日志打印不出来
 */
public class TestFuture {
    private static final Logger logger = LoggerFactory.getLogger(TestFuture.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        logger.info("程序开始");
        //有返回值的多线程
        Callable<Chuju> onlineShop = new Callable<Chuju>() {
            @Override
            public Chuju call() throws Exception {
                logger.info("1、下单成功");
                logger.info("1、等待收货");
                Chuju chuju = new Chuju();
                Thread.sleep(5000);
                logger.info("1、配送成功");
                return chuju;
            }
        };
        //执行购买操作
        FutureTask<Chuju> task = new FutureTask<Chuju>(onlineShop);
        logger.info("1、用户购买餐具");
        new Thread(task).start();
        //第二步，用户购买食材
        logger.info("2、用户购买食材");
        Shicai shicai = new Shicai();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("2、食材到位...");
        //用户询问线程（厨具）是否调用成功？
        if(!task.isDone()){
            logger.info("2、厨具还没到达...");
        }
        try {
            Chuju chuju = task.get();  //阻塞状态
            logger.info("3、厨具到达"+chuju);
            cook(chuju,shicai);
            long endTime = System.currentTimeMillis();
            logger.info("3、用时共:"+(endTime-startTime)+" s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    static void cook(Chuju chuju, Shicai shicai) {
    }

    //厨具类
    static class Chuju {
    }

    //食材类
    static class Shicai {
    }
}
