package com.cinema.api.mail.factory;

import com.cinema.api.config.properties.RoutingProperties;
import com.cinema.api.mail.config.properties.MailExtendedProperties;
import com.cinema.api.mail.utils.LocalizedMessageSource;
import com.cinema.api.mail.utils.MultipartMimeMessageBuilder;
import com.cinema.api.user.model.dto.PasswordResetMailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

import static com.cinema.api.mail.utils.PasswordResetMailMessageKeys.ACTION_URL_VARIABLE;
import static com.cinema.api.mail.utils.PasswordResetMailMessageKeys.STARTING_GREETER_TEMPLATE;
import static com.cinema.api.mail.utils.PasswordResetMailMessageKeys.STARTING_GREETER_VARIABLE;
import static com.cinema.api.mail.utils.PasswordResetMailMessageKeys.TEMPLATE_NAME;
import static com.cinema.api.mail.utils.PasswordResetMailMessageKeys.TITLE;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties( {MailExtendedProperties.class, RoutingProperties.class})
public class PasswordResetFactory {

     private final MailExtendedProperties mailExtendedProperties;
     private final RoutingProperties routingProperties;
     private final TemplateEngine templateEngine;
     private final MessageSource messageSource;
     private final JavaMailSender javaMailSender;

     public MimeMessage createMessage(PasswordResetMailDTO mailDTO) throws MessagingException {
          final LocalizedMessageSource localizedMessageSource =
                  new LocalizedMessageSource(messageSource, mailDTO.getLocale());

          return MultipartMimeMessageBuilder.of(javaMailSender)
                                            .from(mailExtendedProperties.getNoReplyAddress())
                                            .to(mailDTO.getAddress())
                                            .subject(localizedMessageSource.get(TITLE))
                                            .htmlText(createHTMLContent(localizedMessageSource, mailDTO))
                                            .getMessage();
     }

     private String createHTMLContent(LocalizedMessageSource messages, PasswordResetMailDTO mail) {
          final Map<String, Object> variables = buildVariables(messages, mail);
          Context context = new Context(messages.getLocale());
          context.setVariables(variables);
          return templateEngine.process(TEMPLATE_NAME, context);
     }

     private Map<String, Object> buildVariables(LocalizedMessageSource messages, PasswordResetMailDTO mail) {
          return ofEntries(
                  entry(ACTION_URL_VARIABLE, createActionURL(mail.getToken())),
                  entry(STARTING_GREETER_VARIABLE, createStartingGreeter(messages, mail.getUsername()))
          );
     }

     private String createActionURL(String token) {
          return String.format(routingProperties.getChangePasswordTemplate(), token);
     }

     private String createStartingGreeter(LocalizedMessageSource messages, String username) {
          return String.format(messages.get(STARTING_GREETER_TEMPLATE), username);
     }
}