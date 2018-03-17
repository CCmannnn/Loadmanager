package com.zeng.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Administrator
 */
@Api(value = "springboot服务",description="简单的计算服务API")
@Configuration
@EnableScheduling
@EnableAsync
@RestController
@SpringBootApplication
@EnableSwagger2
public class Application  extends SpringBootServletInitializer {

    @RequestMapping("/")
    String home() {

        return "sms server";
    }

    @ApiOperation("查询城市")
    @RequestMapping("/city")
    String city(String  id) {

        return "杭州";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }



}