package io.dracula.test.dubbo.lb;

import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dk
 */
@Service
public class TestServiceImpl implements TestService {

    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public String sayHello(String name) {
        logger.info("sayHello(String)");
        return "hello "+name;
    }

}
