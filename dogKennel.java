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
import java.sql.Date;
import java.util.Properties;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;

public class dogKennel {

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
            System.out.println();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            
            while(rs.next()) {
              int count = rs.getInt("count");
              System.out.println();
              String print = "There are " + count + " old dogs in the system currently!";
              System.out.println(print);
              System.out.println();
              System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
              System.out.println();
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
            System.out.println();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println();
            
            while(rs.next()) {
              int id = rs.getInt("id");
              String breakfast = rs.getString("breakfast");
              String lunch = rs.getString("lunch");
              String dinner = rs.getString("dinner");
              String print = "Meal Plan " + id + "-- Breakfast: " + breakfast + " | Lunch: " + lunch + " | Dinner: " + dinner;
              System.out.println(print);
              
            }
            System.out.println();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
              System.out.println();
            rs.close();
            st.close();
            cn.close();
          }
          catch(Exception e) {
            e.printStackTrace();
          }
    }

    private static void addNewDog()
    {
      /*
        name VARCHAR NOT NULL,
        owner INT NOT NULL,
        vet VARCHAR NOT NULL,
        breed VARCHAR NOT NULL,
        birthday DATE NOT NULL,
        exercise INT NOT NULL,
        e_contact INT NOT NULL,
        color VARCHAR NOT NULL,
        food INT NOT NULL,
       */
        try {
            String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("myconfig.properties");
            props.load(in);
            in.close();
            
            Connection cn = DriverManager.getConnection(url, props);
            System.out.println();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println();
            
            System.out.print("Dog's Name....................................:");
            Scanner reader = new Scanner(System.in);
            String code1 = reader.nextLine();

            System.out.print("Owner id....................................:");
            int code2 = Integer.parseInt(reader.nextLine());
            
            String q = "SELECT * FROM dog WHERE name = ? AND owner = ?";
            PreparedStatement st = cn.prepareStatement(q);
            st.setString(1, code1);
            st.setInt(2, code2);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
              System.out.println("This dog is already in our system!");
              rs.close();
              st.close();
              cn.close();
              System.exit(1);
            }
            rs.close();
      
            System.out.print("Veterinarian name....................................:");
            String code3 = reader.nextLine();
            System.out.print("Dog's breed....................:");
            String code4 = reader.nextLine();
            System.out.print("Dog's birthday.........................:");
            Date code5 = Date.valueOf(reader.nextLine());
            System.out.print("Exercise Plan....................................:");
            int code6 = Integer.parseInt(reader.nextLine());
            System.out.print("Emergency Contact id....................................:");
            int code7 = Integer.parseInt(reader.nextLine());
            System.out.print("Dog's color....................:");
            String code8 = reader.nextLine();
            System.out.print("Meal plan....................................:");
            int code9 = Integer.parseInt(reader.nextLine());

            q = "INSERT INTO dog VALUES (?,?,?,?,?,?,?,?,?)";
            st = cn.prepareStatement(q);
            st.setString(1, code1);
            st.setInt(2, code2);
            st.setString(3, code3);
            st.setString(4, code4);
            st.setDate(5, code5);
            st.setInt(6, code6);
            st.setInt(7, code7);
            st.setString(8, code8);
            st.setInt(9, code9);

            st.execute();
            
            st.close();
            cn.close();
            System.out.println();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println();
          }
          catch(Exception e) {
            e.printStackTrace();
          }
    }

    private static void addNewCustomer()
    {
    //id INT NOT NULL,
    //name VARCHAR NOT NULL,
    //balance int = 0 to start,
    //card VARCHAR NOT NULL,
        try {
            String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("myconfig.properties");
            props.load(in);
            in.close();
            
            Connection cn = DriverManager.getConnection(url, props);
            System.out.println();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println();
            
            System.out.print("id....................................:");
            Scanner reader = new Scanner(System.in);
            int code1 = Integer.parseInt(reader.nextLine());

            System.out.print("name....................................:");
            String code2 = reader.nextLine();

            int code3 = 0;

            System.out.print("card....................................:");
            String code4 = reader.nextLine();

            String q = "INSERT INTO customer VALUES (?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(q);
            st.setInt(1, code1);
            st.setString(2, code2);
            st.setInt(3, code3);
            st.setString(4, code4);
            st.execute();
            
            st.close();
            cn.close();
            System.out.println();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println();
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

    private static void removeDog()
    {
      try {
        String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("myconfig.properties");
        props.load(in);
        in.close();
        
        Connection cn = DriverManager.getConnection(url, props);
        
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        
        System.out.print("Dog name....................................:");
        Scanner reader = new Scanner(System.in);
        String code1 = reader.nextLine();

        System.out.print("Owner id....................................:");
        int code2 = Integer.parseInt(reader.nextLine());
        
        String q = "SELECT * FROM dog WHERE name = ? AND owner = ?";
        PreparedStatement st1 = cn.prepareStatement(q);
        st1.setString(1, code1);
        st1.setInt(2, code2);
        ResultSet rs = st1.executeQuery();
        if (rs.next()) {
          rs.close();

          q = "DELETE FROM dog WHERE name = ? AND owner = ?";
          PreparedStatement st = cn.prepareStatement(q);
          st.setString(1, code1);
          st.setInt(2, code2);
          st.execute();
          
          st.close();
          cn.close();
          
          System.out.println();
          System.out.println("[ Removed "+ code1 + " ]");
          System.out.println();
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println();
        }
        else
        {
        System.out.println("This dog does not exist in our system!");
        
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
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

    private static void removeEmployeeFromCompany()
    {
      try {
        String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("myconfig.properties");
        props.load(in);
        in.close();
        
        Connection cn = DriverManager.getConnection(url, props);
        
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        
        System.out.print("Employee id....................................:");
        Scanner reader = new Scanner(System.in);
        int code1 = Integer.parseInt(reader.nextLine());

        System.out.print("Company name....................................:");
        String code2 = reader.nextLine();
        
        String q = "SELECT * FROM working WHERE employee = ? AND company = ?";
        PreparedStatement st1 = cn.prepareStatement(q);
        st1.setInt(1, code1);
        st1.setString(2, code2);
        ResultSet rs = st1.executeQuery();
        if (rs.next()) {
          rs.close();

          q = "DELETE FROM working WHERE employee = ? AND company = ?";
          PreparedStatement st = cn.prepareStatement(q);
          st.setInt(1, code1);
          st.setString(2, code2);
          st.execute();
          
          st.close();
          cn.close();
          
          System.out.println();
          System.out.println("[ Removed Employee "+ code1 + " ]");
          System.out.println();
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println();
        }
        else
        {
        System.out.println("This employee does not work at the given company!");
        
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
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
                    3. Add New Customer
                    4. Add New Dog
                    5. Remove Dog (RIP)
                    6. Remove Employee from Company
                    7. Exit
                    """);

            System.out.print("Enter your choice (1-7): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> listOldDogs();
                case 2 -> listMealPlans();
                case 3 -> addNewCustomer();
                case 4 -> addNewDog();
                case 5 -> removeDog();
                case 6 -> removeEmployeeFromCompany();
                case 7 -> System.out.println("Exiting!");
                    }
                
                } while (choice != 7);
    }

}