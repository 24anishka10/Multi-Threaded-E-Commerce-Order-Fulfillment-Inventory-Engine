package com.ecommerce;

import com.ecommerce.config.DatabaseConfig;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.OrderProcessor;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig.initializeDatabase();
        ProductRepository repository = new ProductRepository();
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("  WELCOME TO THE SYNCED E-COMMERCE ENGINE   ");
        System.out.println("==================================================");

        while (true) {
            System.out.println("\n1. View Inventory");
            System.out.println("2. Run Concurrent Multi-Threaded Order Simulation");
            System.out.println("3. Exit Engine");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                List<Product> items = repository.getAllProducts();
                System.out.println("\n--- Current Inventory Status ---");
                for (Product p : items) {
                    System.out.println(p);
                }
            } else if (choice == 2) {
                System.out.println("\n--- Initialising High-Contention Stress Test ---");
                System.out.println("Scenario: 3 users trying to buy 'Mechanical Kbd' (Only 3 in stock!)...\n");

                // Spinning up explicit parallel worker threads
                Thread t1 = new Thread(() -> new OrderProcessor("Aadarsh", 103, 2).executeSecureTransaction(), "Buyer-Thread-1");
                Thread t2 = new Thread(() -> new OrderProcessor("Aditya", 103, 1).executeSecureTransaction(), "Buyer-Thread-2");
                Thread t3 = new Thread(() -> new OrderProcessor("Akshat", 103, 2).executeSecureTransaction(), "Buyer-Thread-3");

                t1.start();
                t2.start();
                t3.start();

                try {
                    t1.join();
                    t2.join();
                    t3.join();
                } catch (InterruptedException e) {
                    System.err.println("Simulation interrupted.");
                }

                System.out.println("\nSimulation complete. Check the '/invoices' folder for generated log files!");
            } else {
                System.out.println("System shutdown. Happy evaluation!");
                break;
            }
        }
        scanner.close();
    }
}