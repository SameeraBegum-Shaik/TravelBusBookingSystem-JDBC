package TransactionHandling.TravelBusBooking;
import TransactionHandling.TravelBusBooking.CheckSeats;

import java.sql.*;
import java.util.Scanner;

public class TravelBusBookingSystem {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String password = "sameera@7866";

    public static void main(String[] args) {
        CheckSeats c=new CheckSeats();
        ReduceSeatCount r=new ReduceSeatCount();
        CreateBookings cb=new CreateBookings();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter passenger name: ");
        String passenger = sc.next();
        System.out.println("Enter bus id: ");
        int bus_id = sc.nextInt();
        System.out.println("Enter seats required: ");
        int seats_required = sc.nextInt();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            try {
                if(!c.isSufficientSeats(connection,bus_id,seats_required)){
                    System.out.println("Required number of seats are not available!");
                    connection.rollback();
                    return;
                }
                r.reduceSeats(connection,bus_id,seats_required);
                cb.book(connection,passenger,bus_id,seats_required);
                connection.commit();
                System.out.println("Booking successful");
                }catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
            connection.close();
        }catch (Exception e) {
                e.printStackTrace();
        }
    }
}

