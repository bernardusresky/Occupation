package com.amateur.occupation;

import com.amateur.occupation.constant.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@Order(value = 1)
public class OccupationRunner implements ApplicationRunner {
    @Value("${server.port}")
    private int port;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.debug("runner init the arguments");
        Const.PORT = port;
    }
}
