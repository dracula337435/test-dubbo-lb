package io.dracula.test.dubbo.lb.test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import io.dracula.test.dubbo.lb.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dk
 */
@RunWith(SpringRunner.class)
public class TestServiceTest {

    private static Logger logger = LoggerFactory.getLogger(TestServiceTest.class);

    @Reference(group = "*")
    private TestService testService;

    @Test
    public void test(){
        while(true){
            logger.info(testService.sayHello("gxk"));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @EnableDubbo
    @Configuration
    @PropertySource("application.properties")
    public static class Config {}

}
