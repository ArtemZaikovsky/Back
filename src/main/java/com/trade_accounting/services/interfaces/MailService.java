package com.trade_accounting.services.interfaces;

public interface MailService {

    void sendEmail(String to, String body, String topic);
}
