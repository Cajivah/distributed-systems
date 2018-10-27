package com.cinema.api.config.web;

import com.cinema.api.config.properties.RoutingProperties;
import com.cinema.api.config.web.resolver.FrontendResolver;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@AllArgsConstructor
@EnableConfigurationProperties( {RoutingProperties.class})
public class WebMvcConfig implements WebMvcConfigurer {

     private final MappingJackson2HttpMessageConverter converter;
     private final RoutingProperties routingProperties;

     @Override
     public void addCorsMappings(CorsRegistry registry) {
          registry
               .addMapping("/**")
               .allowedOrigins(routingProperties.getFrontendOrigins())
               .allowedMethods("POST", "GET", "PATCH", "DELETE", "OPTIONS")
               .allowCredentials(true);
     }

     @Override
     public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
          converters.add(converter);
     }

     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {

          registry.addResourceHandler("/app/", "/app/**")
                  .resourceChain(true)
                  .addResolver(new FrontendResolver());

          registry.addResourceHandler("/img/**")
                  .addResourceLocations("classpath:/static/img/");

          registry.addResourceHandler("/js/**")
                  .addResourceLocations("classpath:/static/js/");

          registry.addResourceHandler("/css/**")
                  .addResourceLocations("classpath:/static/css/");

          registry.addResourceHandler("swagger-ui.html")
                  .addResourceLocations("classpath:/META-INF/resources/");

          registry.addResourceHandler("/webjars/**")
                  .addResourceLocations("classpath:/META-INF/resources/webjars/");
     }


}
