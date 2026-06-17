package com.lake.booking;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wuming
 * @description: 龙湖游船预约系统服务端启动入口
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.lake.booking.**.mapper")
@EnableScheduling
public class DragonLakeBoatBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DragonLakeBoatBookingApplication.class, args);
        log.info("JAVA后端服务启动成功！！！！！！");
    }

}
