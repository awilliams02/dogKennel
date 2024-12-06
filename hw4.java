/*======================================================================
 * 
 *  NAME: Alexa Williams
 *  ASSIGN:  Final Project
 *  COURSE:  CPSC 321, Fall 2024
 *  DESC:  this file creates and handles the application for users
 *              to interact with the database
 * 
 *======================================================================*/
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.Date;

public class hw4 {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
        scanner.close();
    }

    private static void listOldDogs()
    {
        try {
            String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("myconfig.properties");
            props.load(in);
            in.close();
            System.out.println(props);
            Connection cn = DriverManager.getConnection(url, props);
      
            Statement st = cn.createStatement();
      
            String q = "SELECT COUNT(*) AS count FROM dog WHERE birthday <= '2014-01-01'";
            ResultSet rs = st.executeQuery(q);
            
            while(rs.next()) {
              int count = rs.getInt("count");
              String print = "There are " + count + " old dogs in the system currently!";
              System.out.println(print);
            }
      
            rs.close();
            st.close();
            cn.close();
          }
          catch(Exception e) {
            e.printStackTrace();
          }
    }

    private static void listMealPlans()
    {
        try {
            String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("myconfig.properties");
            props.load(in);
            in.close();
            System.out.println(props);
            Connection cn = DriverManager.getConnection(url, props);
      
            Statement st = cn.createStatement();
      
            String q = "SELECT * FROM food";
            ResultSet rs = st.executeQuery(q);
            
            while(rs.next()) {
              int id = rs.getInt("id");
              String breakfast = rs.getString("breakfast");
              String lunch = rs.getString("lunch");
              String dinner = rs.getString("dinner");
              String print = "Meal Plan " + id + "-- Breakfast: " + breakfast + " | Lunch: " + lunch + " | Dinner: " + dinner;
              System.out.println(print);
            }
      
            rs.close();
            st.close();
            cn.close();
          }
          catch(Exception e) {
            e.printStackTrace();
          }
    }

    private static void addCountry()
    {
        try {
            String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("myconfig.properties");
            props.load(in);
            in.close();
            
            Connection cn = DriverManager.getConnection(url, props);
            
            System.out.print("Country code....................................:");
            Scanner reader = new Scanner(System.in);
            String code = reader.nextLine();
            
            String q = "SELECT * FROM country WHERE country_code = ?";
            PreparedStatement st = cn.prepareStatement(q);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
              System.out.println("This country code is already taken");
              rs.close();
              st.close();
              cn.close();
              System.exit(1);
            }
            rs.close();
      
            System.out.print("Country name....................................:");
            String name = reader.nextLine();
            System.out.print("Country per capita gdp (USD)....................:");
            int gdp = Integer.parseInt(reader.nextLine());
            System.out.print("Country inflation (pct).........................:");
            double inflation = Double.parseDouble(reader.nextLine());

            q = "INSERT INTO country VALUES (?,?,?,?)";
            st = cn.prepareStatement(q);
            st.setString(1, code);
            st.setString(2, name);
            st.setInt(3, gdp);
            st.setDouble(4, inflation);
            st.execute();
            
            st.close();
            cn.close();
          }
          catch(Exception e) {
            e.printStackTrace();
          }
    }

    private static void addBorder()
    {
        try {
            String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("myconfig.properties");
            props.load(in);
            in.close();
            
            Connection cn = DriverManager.getConnection(url, props);
            
            System.out.print("Country code 1....................................:");
            Scanner reader = new Scanner(System.in);
            String code1 = reader.nextLine();

            System.out.print("Country code 2....................................:");
            String code2 = reader.nextLine();
            
            String q = "SELECT * FROM border WHERE (country_code_1 = ? AND country_code_2 = ?) OR (country_code_1 = ? AND country_code_2 = ?)";
            PreparedStatement st = cn.prepareStatement(q);
            st.setString(1, code1);
            st.setString(2, code2);
            st.setString(3, code2);
            st.setString(4, code1);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
              System.out.println("This border already exists!");
              rs.close();
              st.close();
              cn.close();
              System.exit(1);
            }
            rs.close();
      
            System.out.print("Border length....................................:");
            int length = Integer.parseInt(reader.nextLine());

            q = "INSERT INTO border VALUES (?,?,?)";
            st = cn.prepareStatement(q);
            st.setString(1, code1);
            st.setString(2, code2);
            st.setInt(3, length);
            st.execute();
            
            st.close();
            cn.close();
          }
          catch(Exception e) {
            e.printStackTrace();
          }
    }

    private static void findCountriesByGDPAndInflation()
    {
      try {
        String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("myconfig.properties");
        props.load(in);
        in.close();
        
        Connection cn = DriverManager.getConnection(url, props);
        
        System.out.print("Minimum per capita gdp (USD)....................................:");
        Scanner reader = new Scanner(System.in);
        int min_GDP = Integer.parseInt(reader.nextLine());
        System.out.print("Maximum per capita gdp (USD)....................................:");
        int max_gdp = Integer.parseInt(reader.nextLine());
        System.out.print("Minimum inflation (pct)....................................:");
        double min_inflation = Double.parseDouble(reader.nextLine());
        System.out.print("Maximum inflation (pct)....................................:");
        double max_inflation = Double.parseDouble(reader.nextLine());
        
        String q = "SELECT * FROM country WHERE (gdp BETWEEN ? AND ?) AND (inflation BETWEEN ? AND ?) ORDER BY gdp DESC, inflation ASC";
        PreparedStatement st = cn.prepareStatement(q);
        st.setInt(1, min_GDP);
        st.setInt(2, max_gdp);
        st.setDouble(3, min_inflation);
        st.setDouble(4, max_inflation);
        ResultSet rs = st.executeQuery();
            
        while(rs.next()) {
          int gdp = rs.getInt("gdp");
          double inflation = rs.getDouble("inflation");
          String name = rs.getString("country_name");
          String code = rs.getString("country_code");
          String print = name + " (" + code + "), per capita gdp $" + gdp + ", inflation rate " + inflation + "%";
          System.out.println(print);
        }
  
        rs.close();
        st.close();
        cn.close();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    private static void updateCountryGDPAndInflation()
    {
      try {
        String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("myconfig.properties");
        props.load(in);
        in.close();
        
        Connection cn = DriverManager.getConnection(url, props);
        
        System.out.print("Country code....................................:");
        Scanner reader = new Scanner(System.in);
        String code = reader.nextLine();
        
        String q = "SELECT * FROM country WHERE country_code = ?";
        PreparedStatement st = cn.prepareStatement(q);
        st.setString(1, code);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
          rs.close();

          System.out.print("Country per capita gdp (USD)....................:");
          int gdp = Integer.parseInt(reader.nextLine());
          System.out.print("Country inflation (pct).........................:");
          double inflation = Double.parseDouble(reader.nextLine());

          q = "UPDATE country SET gdp = ?, inflation = ? WHERE country_code = ?";
          st = cn.prepareStatement(q);
          st.setInt(1, gdp);
          st.setDouble(2, inflation);
          st.setString(3, code);
          st.execute();
          
          st.close();
          cn.close();
        }
        else
        {
        System.out.println("This country does not exist!");
          rs.close();
          st.close();
          cn.close();
          System.exit(1);
        }
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    private static void removeBorder()
    {
      try {
        String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("myconfig.properties");
        props.load(in);
        in.close();
        
        Connection cn = DriverManager.getConnection(url, props);
        
        System.out.print("Country code 1....................................:");
        Scanner reader = new Scanner(System.in);
        String code1 = reader.nextLine();

        System.out.print("Country code 2....................................:");
        String code2 = reader.nextLine();
        
        String q = "SELECT * FROM border WHERE (country_code_1 = ? AND country_code_2 = ?) OR (country_code_1 = ? AND country_code_2 = ?)";
        PreparedStatement st1 = cn.prepareStatement(q);
        st1.setString(1, code1);
        st1.setString(2, code2);
        st1.setString(3, code2);
        st1.setString(4, code1);
        ResultSet rs = st1.executeQuery();
        if (rs.next()) {
          rs.close();

          q = "DELETE FROM border WHERE (country_code_1 = ? AND country_code_2 = ?) OR (country_code_1 = ? AND country_code_2 = ?)";
          PreparedStatement st = cn.prepareStatement(q);
          st.setString(1, code1);
          st.setString(2, code2);
          st.setString(3, code2);
          st.setString(4, code1);
          st.execute();
          
          st.close();
          cn.close();
        }
        else
        {
        System.out.println("This border does not exist!");
          rs.close();
          st1.close();
          cn.close();
          System.exit(1);
        }
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    private static void mainMenu(Scanner scanner) {
        int choice;
            do{
            
            System.out.println("""
                    1. List Old Dogs
                    2. List Meal Plans
                    3. Add border
                    4. Find countries based on GDP and inflation
                    5. Update country's GDP and inflation
                    6. Remove border
                    7. Exit
                    """);

            System.out.print("Enter your choice (1-7): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> listOldDogs();
                case 2 -> listMealPlans();
                case 3 -> addBorder();
                case 4 -> findCountriesByGDPAndInflation();        
                case 5 -> updateCountryGDPAndInflation();
                case 6 -> removeBorder();
                case 7 -> System.out.println("Exiting!");
                    }
                
                } while (choice != 7);
    }

}