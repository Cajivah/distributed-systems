package com.cinema.api.config.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

     @Bean
     public ResourceBundleMessageSource messageSource() {
          ResourceBundleMessageSource source = new ResourceBundleMessageSource();
          source.setBasenames("i18n/messages");
          source.setUseCodeAsDefaultMessage(true);
          return source;
     }
}
