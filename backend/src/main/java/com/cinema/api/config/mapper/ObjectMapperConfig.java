package com.cinema.api.config.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectMapperConfig {

     @Bean
     @Primary
     public ObjectMapper objectMapper() {
          return new ObjectMapper()
               .registerModule(new JavaTimeModule())
               .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
               .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
     }
}
