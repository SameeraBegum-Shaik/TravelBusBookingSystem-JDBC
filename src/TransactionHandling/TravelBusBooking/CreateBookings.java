package TransactionHandling.TravelBusBooking;
import java.sql.*;
public class CreateBookings {
    public void book(Connection connection,String passenger,int bus_id,int seats_booked) throws SQLException{
        String query="Insert into bookings (passenger_name,bus_id,seats_booked) values(?,?,?)";
        PreparedStatement ps=connection.prepareStatement(query);
        ps.setString(1,passenger);
        ps.setInt(2,bus_id);
        ps.setInt(3,seats_booked);
        int rows=ps.executeUpdate();
        if(rows==0){
            throw new SQLException("Booking could not be created");
        }
    }
}
