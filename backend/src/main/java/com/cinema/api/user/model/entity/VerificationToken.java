package com.cinema.api.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class VerificationToken {

     @Id
     @GeneratedValue
     private Long id;

     @Column(nullable = false)
     private String token;

     @CreatedDate
     @Column(nullable = false)
     private LocalDateTime created;

     @OneToOne(optional = false)
     private UserCredentials user;
}
