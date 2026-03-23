package com.aydog4nn.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1004","Kayıt Bulunamadı"),
    TOKEN_IS_EXPIRED("1005","Tokenin süresi bitmiştir."),
    GENEREAL_EXCEPTION("9999","Genel bir hata oluştu"),
    USERNAME_NOT_FOUND("1006","Username bulunamadı"),
    USERNAME_OR_PASSWORD_INVALID("1007","Kullanıcı adı ya da şifre hatalı "),
    REFRESH_TOKEN_NOT_FOUND("1008","Refresh token bulunamadı!"),
    REFRESH_TOKEN_EXPIRED("1009","Refresh tokenın süresi doldu!"),
    CURRENCY_RATE_IS_OCCURED("1010","Döviz kuru alınamadı!");


    private String code;

    private String message;

    MessageType(String code,String message){
        this.code = code;
        this.message = message;
    }

}
