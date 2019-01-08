package com.cinema.api.cinema.model.event;

import com.cinema.api.cinema.model.entity.Booking;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@ToString(callSuper = true)
public class OnBookingCreatedEvent extends ApplicationEvent {

     private Booking booking;
     private Locale locale;

     public OnBookingCreatedEvent(Object source, Booking booking, Locale locale) {
          super(source);
          this.booking = booking;
          this.locale = locale;
     }
}
