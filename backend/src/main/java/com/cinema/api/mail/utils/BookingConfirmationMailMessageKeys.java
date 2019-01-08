package com.cinema.api.mail.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingConfirmationMailMessageKeys {

     public static final String TITLE = "mail.booking.confirmation.title";
     public static final String STARTING_GREETER_TEMPLATE = "mail.booking.confirmation.startingGreeterTemplate";
     public static final String MAIN_MESSAGE_TEMPLATE = "mail.booking.confirmation.mainMessageTemplate";

     public static final String ACTION_URL_VARIABLE = "actionURL";
     public static final String STARTING_GREETER_VARIABLE = "startingGreeter";
     public static final String MAIN_MESSAGE_VARIABLE = "mainMessage";
     public static final String SEATS = "bookingSeats";

     public static final String TEMPLATE_NAME = "BookingConfirmationEmail";
}