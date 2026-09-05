package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.util.InvoiceGenerator;

public class OrderProcessor implements Runnable {
    private static final ProductRepository repo = new ProductRepository();
    private final String customerName;
    private final int productId;
    private final int quantity;

    public OrderProcessor(String customerName, int productId, int quantity) {
        this.customerName = customerName;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        processOrder();
    }

    // Explicit Synchronization handles multi-threaded contention perfectly
    private static synchronized void processOrder() {
        String threadName = Thread.currentThread().getName();
    }

    public void executeSecureTransaction() {
        synchronized (repo) {
            Product p = repo.getProductById(productId);
            if (p == null) {
                System.out.println("[" + Thread.currentThread().getName() + "] Order Failed: Product ID " + productId + " not found.");
                return;
            }

            if (p.getStock() >= quantity) {
                // Simulate processing delay
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}

                int updatedStock = p.getStock() - quantity;
                repo.updateStock(productId, updatedStock);
                double total = p.getPrice() * quantity;

                System.out.println("✅ [" + Thread.currentThread().getName() + "] Success! " + customerName + " bought " + quantity + "x " + p.getName());
                InvoiceGenerator.generateInvoice(customerName, p.getName(), quantity, total);
            } else {
                System.out.println("❌ [" + Thread.currentThread().getName() + "] Failed! " + customerName + " requested " + quantity + "x " + p.getName() + " (Only " + p.getStock() + " left)");
            }
        }
    }
}