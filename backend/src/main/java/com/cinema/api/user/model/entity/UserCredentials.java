package com.cinema.api.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserCredentials {

     @Id
     @GeneratedValue
     private Long id;

     @Column(unique = true, nullable = false)
     private String username;

     @Column(unique = true, nullable = false)
     @Email
     private String email;

     @Embedded
     private HashedPassword password;

     @Builder.Default
     @Transient
     private Set<UserLogin> logins = new HashSet<>();

     private boolean verified;

     private boolean locked = true;

     @ManyToMany(fetch = FetchType.EAGER)
     private Collection<Role> roles;

     @CreatedDate
     private LocalDateTime created;

     @LastModifiedDate
     private LocalDateTime modified;
}
