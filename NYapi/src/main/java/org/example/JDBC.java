package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class JDBC {

    public static void articaleTable() {

        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + Main.databaseName + ";" + "encrypt=true;"
                + "trustServerCertificate=true";

        String user = /* "sa" */ Main.databaseUsername;
        String pass = /* "root" */ Main.databasePass;

        Connection con = null;
        try {

            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(url, user, pass);

            String sql = """
                    IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'articles')
                    BEGIN
                    CREATE TABLE articles(
                    Article_Title VARCHAR(MAX),
                    Author VARCHAR(MAX),
                    Article_Date VARCHAR(MAX),
                    Category VARCHAR(MAX),
                    Content VARCHAR(MAX)
                    );
                    END
                    MERGE INTO articles AS target
                    USING (SELECT ?, ?, ?, ?, ?) AS source (Article_Title, Author, Article_Date, Category, Content)
                    ON (target.Article_Title = source.Article_Title)
                    WHEN MATCHED THEN
                    UPDATE SET Article_Title = source.Article_Title, Author = source.Author, Article_Date = source.Article_Date, Category = source.Category, Content = source.Content
                    WHEN NOT MATCHED THEN
                    INSERT (Article_Title, Author, Article_Date, Category, Content)
                    VALUES (source.Article_Title, source.Author, source.Article_Date, source.Category, source.Content);""";

            PreparedStatement statement = con.prepareStatement(sql);
            for(int i=0; i<API.articleList.size(); i++){
                for(int j=0; j<API.articleList.get(i).response.docs.length; j++){
                    statement.setString(1, API.articleList.get(i).response.docs[j].headline.main);
                    statement.setString(2, API.articleList.get(i).response.docs[j].byline.original);
                    statement.setString(3, API.articleList.get(i).response.docs[j].pub_date);
                    statement.setString(4, API.articleList.get(i).response.docs[j].section_name);
                    statement.setString(5, API.articleList.get(i).response.docs[j].lead_paragraph);
                    statement.executeUpdate();
                }
            }
            // Close the PreparedStatement object
            statement.close();

            con.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void searchSQL(){
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + Main.databaseName + ";" + "encrypt=true;"
                + "trustServerCertificate=true";

        String user = /* "sa" */ Main.databaseUsername;
        String pass = /* "root" */ Main.databasePass;

        Connection con = null;
        try {

            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(url, user, pass);

            String sql = "select *\r\n" + "From articles\r\n" + "WHERE Category ='" + Main.categorySearch + "'";

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String Article_Title = resultSet.getString("Article_Title");

                String Author = resultSet.getString("Author");

                String Article_Date = resultSet.getString("Article_Date");

                String Category = resultSet.getString("Category");

                String Content = resultSet.getString("Content");

                System.out.print("\tArticle Title: " + Article_Title);
                System.out.print("\n\tAuthor: " + Author);
                System.out.print("\n\tArticle Date: " + Article_Date);
                System.out.print("\n\tCategory: " + Category);
                System.out.println("\n\tContent: " + Content);
                for(int j=-9; j<Content.length(); j++){
                    System.out.print("-");
                }
                System.out.println("");
            }

            statement.close();
            con.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void sortByDataASC(){
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + Main.databaseName + ";" + "encrypt=true;"
                + "trustServerCertificate=true";

        String user = /* "sa" */ Main.databaseUsername;
        String pass = /* "root" */ Main.databasePass;

        Connection con = null;
        try {

            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(url, user, pass);

            String sql = "select *\n" +
                    "from articles\n" +
                    "Order by Article_Date ASC";

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String Article_Title = resultSet.getString("Article_Title");

                String Author = resultSet.getString("Author");

                String Article_Date = resultSet.getString("Article_Date");

                String Category = resultSet.getString("Category");

                String Content = resultSet.getString("Content");

                System.out.print("\tArticle Title: " + Article_Title);
                System.out.print("\n\tAuthor: " + Author);
                System.out.print("\n\tArticle Date: " + Article_Date);
                System.out.print("\n\tCategory: " + Category);
                System.out.println("\n\tContent: " + Content);
                for(int j=-9; j<Content.length(); j++){
                    System.out.print("-");
                }
                System.out.println("");
            }

            statement.close();
            con.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void sortByDataDESC(){
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + Main.databaseName + ";" + "encrypt=true;"
                + "trustServerCertificate=true";

        String user = /* "sa" */ Main.databaseUsername;
        String pass = /* "root" */ Main.databasePass;

        Connection con = null;
        try {

            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(url, user, pass);

            String sql = "select *\n" +
                    "from articles\n" +
                    "Order by Article_Date DESC";

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String Article_Title = resultSet.getString("Article_Title");

                String Author = resultSet.getString("Author");

                String Article_Date = resultSet.getString("Article_Date");

                String Category = resultSet.getString("Category");

                String Content = resultSet.getString("Content");

                System.out.print("\tArticle Title: " + Article_Title);
                System.out.print("\n\tAuthor: " + Author);
                System.out.print("\n\tArticle Date: " + Article_Date);
                System.out.print("\n\tCategory: " + Category);
                System.out.println("\n\tContent: " + Content);
                for(int j=-9; j<Content.length(); j++){
                    System.out.print("-");
                }
                System.out.println("");
            }

            statement.close();
            con.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void sortByCategoryASC(){
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + Main.databaseName + ";" + "encrypt=true;"
                + "trustServerCertificate=true";

        String user = /* "sa" */ Main.databaseUsername;
        String pass = /* "root" */ Main.databasePass;

        Connection con = null;
        try {

            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(url, user, pass);

            String sql = "select *\n" +
                    "from articles\n" +
                    "Order by Category ASC";

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String Article_Title = resultSet.getString("Article_Title");

                String Author = resultSet.getString("Author");

                String Article_Date = resultSet.getString("Article_Date");

                String Category = resultSet.getString("Category");

                String Content = resultSet.getString("Content");

                System.out.print("\tArticle Title: " + Article_Title);
                System.out.print("\n\tAuthor: " + Author);
                System.out.print("\n\tArticle Date: " + Article_Date);
                System.out.print("\n\tCategory: " + Category);
                System.out.println("\n\tContent: " + Content);
                for(int j=-9; j<Content.length(); j++){
                    System.out.print("-");
                }
                System.out.println("");
            }

            statement.close();
            con.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void sortByCategoryDESC(){
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + Main.databaseName + ";" + "encrypt=true;"
                + "trustServerCertificate=true";

        String user = /* "sa" */ Main.databaseUsername;
        String pass = /* "root" */ Main.databasePass;

        Connection con = null;
        try {

            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(url, user, pass);

            String sql = "select *\n" +
                    "from articles\n" +
                    "Order by Category DESC";

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String Article_Title = resultSet.getString("Article_Title");

                String Author = resultSet.getString("Author");

                String Article_Date = resultSet.getString("Article_Date");

                String Category = resultSet.getString("Category");

                String Content = resultSet.getString("Content");

                System.out.print("\tArticle Title: " + Article_Title);
                System.out.print("\n\tAuthor: " + Author);
                System.out.print("\n\tArticle Date: " + Article_Date);
                System.out.print("\n\tCategory: " + Category);
                System.out.println("\n\tContent: " + Content);
                for(int j=-9; j<Content.length(); j++){
                    System.out.print("-");
                }
                System.out.println("");
            }

            statement.close();
            con.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}