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

            
    private static void findReservations()
    {
      /*
    confirm_num INT NOT NULL,
    kennel VARCHAR NOT NULL,
    dog VARCHAR NOT NULL,
    owner INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
       */
      try {
        String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("myconfig.properties");
        props.load(in);
        in.close();
        
        Connection cn = DriverManager.getConnection(url, props);
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        
        System.out.print("Date(YYYY-MM-DD)....................................:");
        Scanner reader = new Scanner(System.in);
        Date code1 = Date.valueOf(reader.nextLine());
        System.out.print("Company....................................:");
        String code2 = reader.nextLine();
        
        String q = "SELECT * FROM reservation WHERE start_date <= ? AND end_date >= ? AND kennel = ? ORDER BY start_date ASC, end_date ASC";
        PreparedStatement st = cn.prepareStatement(q);
        st.setDate(1, code1);
        st.setDate(2, code1);
        st.setString(3, code2);
        ResultSet rs = st.executeQuery();
        int x = 0;
        System.out.println();
            
        while(rs.next()) {
          int confirm_num = rs.getInt("confirm_num");
          String kennel = rs.getString("kennel");
          String dog = rs.getString("dog");
          int owner = rs.getInt("owner");
          Date start = rs.getDate("start_date");
          Date end = rs.getDate("end_date");
          String print = "| Dog: " + dog + " | Owner: " + owner + " | Confirmation #: " + confirm_num + " | Company: " + kennel + " | Start Date: " + start + " | End Date: " + end + " |";
          System.out.println(print);
          System.out.println();
          x++;
        }

        if(x == 0)
        {
          System.out.println();
          System.out.println("Sorry, there are no reservations over this date for Company: " + code2 + "!");
        }
  
        rs.close();
        st.close();
        cn.close();
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
    

        
    private static void searchForDog()
    {
      /*
    name VARCHAR NOT NULL, -
    owner INT NOT NULL, -
    vet VARCHAR NOT NULL,
    breed VARCHAR NOT NULL, -
    birthday DATE NOT NULL, -
    exercise INT NOT NULL, -
    e_contact INT NOT NULL,
    color VARCHAR NOT NULL, -
    food INT NOT NULL, -
       */
      try {
        String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("myconfig.properties");
        props.load(in);
        in.close();
        
        Connection cn = DriverManager.getConnection(url, props);
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        
        System.out.print("Dog name....................................:");
        Scanner reader = new Scanner(System.in);
        String code1 = reader.nextLine();
        
        String q = "SELECT * FROM dog WHERE name = ? ORDER BY color ASC, owner ASC, breed ASC, birthday ASC";
        PreparedStatement st = cn.prepareStatement(q);
        st.setString(1, code1);
        ResultSet rs = st.executeQuery();
        int x = 0;
        System.out.println();
            
        while(rs.next()) {
          int owner = rs.getInt("owner");
          String name = rs.getString("name");
          int exercise = rs.getInt("exercise");
          String vet = rs.getString("vet");
          String breed = rs.getString("breed");
          Date birthday = rs.getDate("birthday");
          int contact = rs.getInt("e_contact");
          String color = rs.getString("color");
          int food = rs.getInt("food");
          String print = "| Name: " + name + " | Owner: " + owner + " | Color: " + color + " | Breed: " + breed + " | Birthday: " + birthday + " | Food: " + food + " | Exercise: " + exercise + " | Veterinarian: " + vet + " | Emercency Contact: " + contact + " |";
          System.out.println(print);
          System.out.println();
          x++;
        }

        if(x == 0)
        {
          System.out.println();
          System.out.println("Sorry, there are no dogs with this name in our system! ");
        }
  
        rs.close();
        st.close();
        cn.close();
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }



    
    private static void searchForCustomer()
    {
      try {
        String url = "jdbc:postgresql://cps-postgresql.gonzaga.edu/awilliams19_db";
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("myconfig.properties");
        props.load(in);
        in.close();
        
        Connection cn = DriverManager.getConnection(url, props);
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        
        System.out.print("Customer name....................................:");
        Scanner reader = new Scanner(System.in);
        String code1 = reader.nextLine();
        
        String q = "SELECT * FROM customer WHERE name = ? ORDER BY id ASC";
        PreparedStatement st = cn.prepareStatement(q);
        st.setString(1, code1);
        ResultSet rs = st.executeQuery();
        int x = 0;
        System.out.println();
            
        while(rs.next()) {
          int id = rs.getInt("id");
          String name = rs.getString("name");
          int balance = rs.getInt("balance");
          String card = rs.getString("card");
          String print = "| Customer #" + id + " | Name: " + name + " | Account Balance = " + balance + " | Card Number: " + card + " |";
          System.out.println(print);
          x++;
        }

        if(x == 0)
        {
          System.out.println();
          System.out.println("Sorry, there are no customers with this name in our system! ");
        }
  
        rs.close();
        st.close();
        cn.close();
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    private static void updateMealPlan()
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
        PreparedStatement st = cn.prepareStatement(q);
        st.setString(1, code1);
        st.setInt(2, code2);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
          rs.close();

          System.out.print("New mean plan....................:");
          int code3 = Integer.parseInt(reader.nextLine());

          q = "UPDATE dog SET food = ? WHERE name = ? AND owner = ?";
          st = cn.prepareStatement(q);
          st.setInt(1, code3);
          st.setString(2, code1);
          st.setInt(3, code2);
          st.execute();
          
          st.close();
          cn.close();

          System.out.println();
          System.out.println("[ Updated "+ code1 + "'s Meal Plan ]");
          System.out.println();
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println();
        }
        else
        {
        System.out.println("This dog does not exist in our system!");
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


    
    private static void updateCustomerBalance()
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
        
        System.out.print("Customer id....................................:");
        Scanner reader = new Scanner(System.in);
        int code1 = Integer.parseInt(reader.nextLine());
        
        String q = "SELECT * FROM customer WHERE id = ?";
        PreparedStatement st = cn.prepareStatement(q);
        st.setInt(1, code1);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
          rs.close();

          System.out.print("New account balance....................:");
          int code2 = Integer.parseInt(reader.nextLine());

          q = "UPDATE customer SET balance = ? WHERE id = ?";
          st = cn.prepareStatement(q);
          st.setInt(1, code2);
          st.setInt(2, code1);
          st.execute();
          
          st.close();
          cn.close();

          System.out.println();
          System.out.println("[ Updated Customer "+ code1 + "'s Account Balance ]");
          System.out.println();
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println();
        }
        else
        {
        System.out.println("This customer does not exist in our system!");
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
                    1.  List Old Dogs
                    2.  List Meal Plans
                    3.  Add New Customer
                    4.  Add New Dog
                    5.  Remove Dog (RIP)
                    6.  Remove Employee from Company
                    7.  Update Dog's Meal Plan
                    8.  Update Customer Account Balance
                    9 . Search for Customer
                    10. Search for Dog
                    11. Find Reservations
                    12. Exit
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
                case 7 -> updateMealPlan();
                case 8 -> updateCustomerBalance();
                case 9 -> searchForCustomer();
                case 10 -> searchForDog();
                case 11 -> findReservations();
                case 12 -> System.out.println("Exiting!");
                    }
                
                } while (choice != 7);
    }

}