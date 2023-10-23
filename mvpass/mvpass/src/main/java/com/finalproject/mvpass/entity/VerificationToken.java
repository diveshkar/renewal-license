package com.finalproject.mvpass.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
//
//@Entity
//@Data
//@NoArgsConstructor
//@Table(name = "VerificationToken")
//public class
//VerificationToken {
//
//    private static final int EXPIRATION_TIME = 10;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long userid;
//    private String token;
//    private Date expirationTime;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id",nullable = false,foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
//    private User user;
//
//    public VerificationToken(User user, String token){
//        super();
//        this.token= token;
//        this.user= user;
//        this.expirationTime = (Date) calculateExpirationDate(EXPIRATION_TIME);
//    }
//
//    public VerificationToken(String token){
//        super();
//        this.token = token;
//        this.expirationTime = (Date) calculateExpirationDate(EXPIRATION_TIME);
//
//    }
//
//    private Date calculateExpirationDate(int expirationTime){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(new Date().getTime());
//        calendar.add(Calendar.MINUTE,expirationTime);
//        return new Date(calendar.getTime().getTime());
//    }
//}


import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@Table(name = "VerificationToken")
public class VerificationToken {

    private static final int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String token;
    private Instant expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
    private User user;

    public VerificationToken(User user, String token) {
        this.token = token;
        this.user = user;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    public VerificationToken(String token) {
        this.token = token;
        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
    }

    private Instant calculateExpirationDate(int expirationTime) {
        return Instant.now().plus(expirationTime, ChronoUnit.MINUTES);
    }
}
//
