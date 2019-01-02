package com.cinema.api.mail.factory;

import com.cinema.api.cinema.model.dto.BookingConfirmationMailDTO;
import com.cinema.api.cinema.model.entity.BookingSeat;
import com.cinema.api.cinema.model.entity.Seance;
import com.cinema.api.cinema.model.entity.Seat;
import com.cinema.api.config.properties.RoutingProperties;
import com.cinema.api.mail.config.properties.MailExtendedProperties;
import com.cinema.api.mail.utils.LocalizedMessageSource;
import com.cinema.api.mail.utils.MultipartMimeMessageBuilder;
import com.cinema.api.util.date.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

import static com.cinema.api.mail.utils.BookingConfirmationMailMessageKeys.ACTION_URL_VARIABLE;
import static com.cinema.api.mail.utils.BookingConfirmationMailMessageKeys.MAIN_MESSAGE_TEMPLATE;
import static com.cinema.api.mail.utils.BookingConfirmationMailMessageKeys.MAIN_MESSAGE_VARIABLE;
import static com.cinema.api.mail.utils.BookingConfirmationMailMessageKeys.SEATS;
import static com.cinema.api.mail.utils.BookingConfirmationMailMessageKeys.STARTING_GREETER_TEMPLATE;
import static com.cinema.api.mail.utils.BookingConfirmationMailMessageKeys.STARTING_GREETER_VARIABLE;
import static com.cinema.api.mail.utils.BookingConfirmationMailMessageKeys.TEMPLATE_NAME;
import static com.cinema.api.mail.utils.BookingConfirmationMailMessageKeys.TITLE;

@Component
@AllArgsConstructor
@EnableConfigurationProperties( {MailExtendedProperties.class, RoutingProperties.class})
public class BookingConfirmationFactory {

     private final MailExtendedProperties mailExtendedProperties;
     private final RoutingProperties routingProperties;
     private final TemplateEngine templateEngine;
     private final MessageSource messageSource;
     private final JavaMailSender javaMailSender;

     public MimeMessage createMessage(BookingConfirmationMailDTO mailDTO) throws MessagingException {
          final LocalizedMessageSource localizedMessageSource =
               new LocalizedMessageSource(messageSource, mailDTO.getLocale());

          return MultipartMimeMessageBuilder.of(javaMailSender)
                                            .from(mailExtendedProperties.getNoReplyAddress())
                                            .to(mailDTO.getAddress())
                                            .subject(localizedMessageSource.get(TITLE))
                                            .htmlText(createHTMLContent(localizedMessageSource, mailDTO))
                                            .getMessage();
     }

     private String createHTMLContent(LocalizedMessageSource messages, BookingConfirmationMailDTO mail) {
          final Map<String, Object> variables = buildVariables(messages, mail);
          Context context = new Context(messages.getLocale());
          context.setVariables(variables);
          return templateEngine.process(TEMPLATE_NAME, context);
     }

     private Map<String, Object> buildVariables(LocalizedMessageSource messages, BookingConfirmationMailDTO mail) {
          return Map.ofEntries(
               Map.entry(ACTION_URL_VARIABLE, createActionURL(mail.getToken())),
               Map.entry(STARTING_GREETER_VARIABLE, createStartingGreeter(messages, mail.getOwner().getFirstName())),
               Map.entry(MAIN_MESSAGE_VARIABLE, createMainMessage(messages, mail)),
               Map.entry(SEATS, mail.getSeats())
          );
     }

     private String createActionURL(String token) {
          return String.format(routingProperties.getBookingDeletionTemplate(), token);
     }

     private String createStartingGreeter(LocalizedMessageSource messages, String username) {
          return String.format(messages.get(STARTING_GREETER_TEMPLATE), Optional.ofNullable(username).orElse(""));
     }

     private String createMainMessage(LocalizedMessageSource messages, BookingConfirmationMailDTO mailDTO) {
          Set<BookingSeat> seats = mailDTO.getSeats();
          int seatsCount = seats.size();
          Seance seance = mailDTO.getSeance();
          String movieTitle = seance.getMovie().getTitle();
          String dateTime = DateUtils.toHumanFormatWithoutSecs(seance.getStart());
          String mainMessageTemplate = messages.get(MAIN_MESSAGE_TEMPLATE);

          return String.format(mainMessageTemplate, seatsCount, movieTitle, dateTime);
     }
}

