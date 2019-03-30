package com.test;

import com.yxr.service.RetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetryServiceTest {
    @Autowired
    RetryService retryService;

    @Test
    public void testRetry() {
        int i = retryService.retry(-1);
//        int i = retryService.A(-1);
        System.out.println("数据是： "+ i );
    }
}
