package TransactionHandling.TravelBusBooking;
import java.sql.*;
public class ReduceSeatCount {
    public void reduceSeats(Connection connection,int bus_id,int seats_required)throws SQLException{
        String query="Update buses set available_seats=available_seats-? where bus_id=?";
        PreparedStatement ps=connection.prepareStatement(query);
        ps.setInt(1,seats_required);
        ps.setInt(2,bus_id);
        int rows=ps.executeUpdate();
        if(rows==0){
            throw new SQLException("Bus not found");
        }
    }
}
