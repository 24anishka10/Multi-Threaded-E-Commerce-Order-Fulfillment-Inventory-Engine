package com.ecommerce.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class InvoiceGenerator {
    public static synchronized void generateInvoice(String customerName, String productName, int qty, double total) {
        File dir = new File("invoices");
        if (!dir.exists()) {
            dir.mkdir();
        }

        String filename = "invoices/" + customerName.replaceAll("\\s+", "_") + "_invoice.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("==========================================");
            writer.println("         E-COMMERCE ORDER INVOICE         ");
            writer.println("==========================================");
            writer.println("Customer Name : " + customerName);
            writer.println("Item Purchased: " + productName);
            writer.println("Quantity      : " + qty);
            writer.println("Total Amount  : $" + String.format("%.2f", total));
            writer.println("==========================================");
            writer.println("        Thank you for shopping!           ");
        } catch (IOException e) {
            System.err.println("Failed to write invoice file: " + e.getMessage());
        }
    }
}
