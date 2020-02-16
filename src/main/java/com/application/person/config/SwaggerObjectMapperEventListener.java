package com.application.person.config;

import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import springfox.documentation.schema.configuration.ObjectMapperConfigured;

@Component
public class SwaggerObjectMapperEventListener implements ApplicationListener<ObjectMapperConfigured>, Ordered {
    @Override
    public void onApplicationEvent(ObjectMapperConfigured event) {
    }

    @Override
    public int getOrder() {
        //Always runs last so it's the effective ObjectMapper configuration
        return LOWEST_PRECEDENCE;
    }
}
