package com.web3n.passbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 测试程序入口
 */
@SpringBootApplication
@ComponentScan("com.web3n")
class PassbookApplication {
    public static void main(String[] args) {
        SpringApplication.run(PassbookApplication.class, args);
    }
}
