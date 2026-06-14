package TransactionHandling.TravelBusBooking;
import java.sql.*;
public class CheckSeats {
    public boolean isSufficientSeats(Connection connection,int bus_id,int seats_required) throws SQLException{
     try{
         String query="Select available_seats from buses where bus_id=?";
         PreparedStatement ps=connection.prepareStatement(query);
         ps.setInt(1,101);
         ResultSet result=ps.executeQuery();
         if(result.next()){
             int available_seats=result.getInt("available_seats");
             return available_seats>=seats_required;
         }
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     return false;
    }
}
