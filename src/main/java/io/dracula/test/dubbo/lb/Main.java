package io.dracula.test.dubbo.lb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @author dk
 */
@SpringBootApplication
@EnableDubbo
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
