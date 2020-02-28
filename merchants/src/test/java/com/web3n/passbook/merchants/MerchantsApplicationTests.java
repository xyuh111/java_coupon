package com.web3n.passbook.merchants;

import com.web3n.passbook.MerchantsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 测试用例入口
 * */
@SpringBootApplication
@ComponentScan("com.web3n")
class MerchantsApplicationTests {
    public static void main(String[] args) {
        SpringApplication.run(MerchantsApplication.class, args);
    }
}
