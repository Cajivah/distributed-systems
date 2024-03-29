package com.cinema.api.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@ConfigurationProperties("routes")
public class RoutingProperties {

     @NotBlank
     private String changePasswordTemplate;

     @NotEmpty
     private String[] frontendOrigins;

     @NotBlank
     private String verificationTemplate;

     @NotBlank
     private String bookingDeletionTemplate;
}
