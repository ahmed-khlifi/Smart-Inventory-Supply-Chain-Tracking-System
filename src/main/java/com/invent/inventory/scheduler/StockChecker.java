package com.invent.inventory.scheduler;

import com.invent.inventory.entity.Product;
import com.invent.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockChecker {
    @Autowired
    private ProductService
            service;
    @Scheduled(cron = "0 0 9 * * *") // Every day at 9 AM
    public void checkStockLevels() {
        List<Product> lowStock = service.findAll().stream()
                .filter(p -> p.getQuantity() < 10)
                .toList();
        if (!lowStock.isEmpty()) {
            System.out.println("[ALERT] Low stock items:");
            lowStock.forEach(p -> System.out.printf("%s (%s): %d units\n", p.getName(), p.getSku(), p.getQuantity()));
        }
    }
}
