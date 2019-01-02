package com.cinema.api.mail.service;

import com.cinema.api.cinema.model.dto.BookingConfirmationMailDTO;
import com.cinema.api.mail.exception.MailSendingException;
import com.cinema.api.mail.factory.BookingConfirmationFactory;
import com.cinema.api.mail.factory.PasswordResetFactory;
import com.cinema.api.mail.factory.VerifyRegistrationFactory;
import com.cinema.api.user.model.dto.PasswordResetMailDTO;
import com.cinema.api.user.model.dto.VerificationMailDTO;
import io.vavr.CheckedConsumer;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {

     private final JavaMailSender javaMailSender;
     private final PasswordResetFactory passwordResetFactory;
     private final VerifyRegistrationFactory verifyRegistrationFactory;
     private final BookingConfirmationFactory bookingConfirmationFactory;

     public void sendResetPasswordMail(PasswordResetMailDTO mailDTO) {
          Try.of(() -> passwordResetFactory.createMessage(mailDTO))
             .andThenTry((CheckedConsumer<? super MimeMessage>) javaMailSender::send)
             .getOrElseThrow(MailSendingException::ofThrowable);
     }

     public void sendVerificationMail(VerificationMailDTO mailDTO) {
          Try.of(() -> verifyRegistrationFactory.createMessage(mailDTO))
               .andThenTry((CheckedConsumer<? super MimeMessage>) javaMailSender::send)
               .getOrElseThrow(MailSendingException::ofThrowable);
     }

     public void sendBookingConfirmationMail(BookingConfirmationMailDTO mailDTO) {
          Try.of(() -> bookingConfirmationFactory.createMessage(mailDTO))
             .andThenTry((CheckedConsumer<? super MimeMessage>) javaMailSender::send)
             .getOrElseThrow(MailSendingException::ofThrowable);
     }
}
