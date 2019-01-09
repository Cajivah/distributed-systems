package com.cinema.api.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Validated
@ConfigurationProperties("spring.jpa")
public class DatabaseProperties {

     private Hibernate hibernate = new Hibernate();

     @Getter
     @Setter
     @Validated
     public static class Hibernate {

          @NotBlank
          private String ddlAuto;
     }

     private Properties properties = new Properties();

     @Getter
     @Setter
     @Validated
     public static class Properties {

          private Hibernate hibernate = new Hibernate();

          @Getter
          @Setter
          @Validated
          public static class Hibernate {

               @NotBlank
               private String dialect;
          }
     }
}
