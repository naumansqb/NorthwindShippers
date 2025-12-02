package com.pluralsight;

import com.pluralsight.db.DataManager;
import com.pluralsight.model.Shipper;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args.length != 2){
            System.exit(1);
        }
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);

        DataManager dataManager = new DataManager(dataSource);
        Scanner scanner = new Scanner(System.in);

        System.out.println("ADD NEW SHIPPER");
        System.out.print("Enter company name: ");
        String companyName = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        int newId = dataManager.addShipper(companyName, phone);
        System.out.println("New shipper created with ID: " + newId);

        System.out.println("ALL SHIPPERS");
        for(Shipper shipper : dataManager.getAllShippers()) {
            System.out.println(shipper);
        }

        System.out.println("UPDATE SHIPPER PHONE");
        System.out.print("Enter shipper ID: ");
        int shipperId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine();

        dataManager.updateShipperPhone(shipperId, newPhone);
        System.out.println("Shipper updated!");

        System.out.println("ALL SHIPPERS");
        for(Shipper shipper : dataManager.getAllShippers()) {
            System.out.println(shipper);
        }

        System.out.println("DELETE SHIPPER");
        System.out.println("WARNING: Do not delete shippers 1-3 (they have related data)");
        System.out.print("Enter shipper ID: ");
        int deleteId = scanner.nextInt();

        dataManager.deleteShipper(deleteId);
        System.out.println("Shipper deleted!");

        System.out.println("ALL SHIPPERS");
        for(Shipper shipper : dataManager.getAllShippers()) {
            System.out.println(shipper);
        }

        scanner.close();
    }
}