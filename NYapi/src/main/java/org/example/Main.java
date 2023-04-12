package org.example;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static String databaseName;
    static String databaseUsername;
    static String databasePass;
    static String category;
    static String categorySearch;

    static String tableName1;
    public static void main(String[] args) {
        boolean menue = true;

        System.out.print("Enter Database Name:      ");
        databaseName = sc.next();
        System.out.print("Enter Database Username:  ");
        databaseUsername= sc.next();
        System.out.print("Enter Database Password:  ");
        databasePass = sc.next();

        while(menue){

            System.out.println(" -----------");
            System.out.println("| Main Menu |");
            System.out.println(" -----------");
            System.out.println("[1] Search for Articles by Category");
            System.out.println("[2] Search for Articles by Category from SQL Server");
            System.out.println("[3] Sort");
            System.out.println("[3] Exit");
            System.out.println("---------------------------------------------------");
            System.out.print("Enter Number:  ");
            String option = sc.next();

            switch (option) {
                case "1":
                    System.out.print("Enter Category:  ");
                    category = sc.next();
                    API.API();
                    break;
                case "2":
                    System.out.print("Enter Category for Searching:  ");
                    categorySearch = sc.next();
                    JDBC.searchSQL();
                    break;
                case"3":
                    System.out.println("\n   [1] Date");
                    System.out.println("   [2] Category");
                    System.out.println("-------------------");
                    System.out.print("Enter Number:  ");
                    String select = sc.next();
                    switch (select) {
                        case "1":
                            System.out.println("\n   [1] ASC");
                            System.out.println("   [2] DESC");
                            System.out.println("-------------------");
                            System.out.print("Enter Number:  ");
                            String select1 = sc.next();
                            switch (select1) {
                                case "1":
                                    JDBC.sortByDataASC();
                                    break;
                                case"2":
                                    JDBC.sortByDataDESC();
                                    break;
                                default:
                                    System.out.println("Invalid Input");
                                    break;
                            }
                            break;
                        case "2":
                            System.out.println("\n   [1] ASC");
                            System.out.println("   [2] DESC");
                            System.out.println("-------------------");
                            System.out.print("Enter Number:  ");
                            String select2 = sc.next();
                            switch (select2) {
                                case "1":
                                    JDBC.sortByCategoryASC();
                                    break;
                                case"2":
                                    JDBC.sortByCategoryDESC();
                                    break;
                                default:
                                    System.out.println("Invalid Input");
                                    break;
                            }
                            break;
                        default:
                            System.err.println("Invalid Input");
                            break;
                    }
                    break;
                case "4":
                    menue =false;
                    break;
                default:
                    System.err.println("Invalid Input");
                    break;
            }
        }
    }
}