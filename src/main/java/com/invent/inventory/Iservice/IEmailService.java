package com.invent.inventory.Iservice;

public interface IEmailService {
    void sendLowStockAlert(String subject, String body);
}
