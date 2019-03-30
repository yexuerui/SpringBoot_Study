package com.yxr.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;


import java.time.LocalTime;

//spring-retry模块支持方法和类、接口、枚举级别的重试
//由于retry用到了aspect增强，所有会有aspect的坑，
// 就是方法内部调用，会使aspect增强失效，那么retry当然也会失效。
//当我们调用一个接口可能由于网络等原因造成第一次失败，再去尝试就成功了，
// 这就是重试机制，spring支持重试机制，
// 并且在Spring Cloud中可以与Hystaix结合使用，可以避免访问到已经不正常的实例。
// 但是切记非幂等情况下慎用重试
@Service
public class RetryService {
    //开启日志控制
    private final static Logger logger = LoggerFactory.getLogger(RetryService.class);
    private final int totalNum = 100;

    /**
     * value：出现Exception.class进行重试，指定特定的异常
     * • include：和value一样，默认为空，当exclude也为空时，默认所以异常
     * • exclude：指定不处理的异常
     * •maxAttempts：最大重试次数，默认3次
     * •backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，
     * 我们设置为2000L；multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，
     * 如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5
     *
     * @param num
     * @return
     */
    //若是使用@Retryable注解的方法被套嵌调用，则注解失效
    public int A(int num){
        int retry = retry(num);
        return retry;
    }

    @Retryable(value = Exception.class, maxAttempts = 3,
            backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    public int retry(int num) {
        logger.info("{}减库存开始", LocalTime.now());
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error("illegal");
        }
        if (num <= 0) {
            throw new IllegalArgumentException("数量不对");
        }
        logger.info("减库存执行结束" + LocalTime.now());
        return totalNum - num;
    }

    //当重试到达指定次数时，被注解的方法将被回调，
    // 可以在该方法中进行日志处理。需要注意的是发生的异常和入参类型一致时才会回调。
    @Recover
    public int recover(Exception e) {
        logger.warn("减库存失败！！！" + LocalTime.now());
        return totalNum;
    }
}
