package TransactionHandling.BankTransactions;
import java.sql.*;
public class CreditClass {
    public void credit(Connection connection, int accountNumber, double amount) throws SQLException {
        try {
            String query = "Update accounts set balance=balance + ? where acc_num=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1,amount);
            ps.setInt(2,accountNumber);
            int rows=ps.executeUpdate();
            if(rows==0){
                throw new SQLException("Receiver Account Not Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
