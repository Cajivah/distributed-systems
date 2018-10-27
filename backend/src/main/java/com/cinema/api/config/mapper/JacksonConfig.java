package com.cinema.api.config.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@AllArgsConstructor
public class JacksonConfig {

     private ObjectMapper objectMapper;

     @Bean
     @Primary
     public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
          MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
          jsonConverter.setObjectMapper(objectMapper);
          return jsonConverter;
     }
}
