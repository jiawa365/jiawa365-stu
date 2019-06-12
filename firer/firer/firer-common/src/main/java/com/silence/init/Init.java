package com.silence.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import javax.sql.DataSource;


public class Init implements ApplicationListener<ContextRefreshedEvent> {

    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }


}
