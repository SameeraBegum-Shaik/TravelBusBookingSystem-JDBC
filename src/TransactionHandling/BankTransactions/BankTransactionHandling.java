package TransactionHandling.BankTransactions;
import java.sql.*;
import java.util.Scanner;
public class BankTransactionHandling {
    private static final String url="jdbc:mysql://localhost:3306/mydb";
    private static final String username="root";
    private static final String password="sameera@7866";

    public static void main(String[] args) {
        DebitClass d=new DebitClass();
        CreditClass c=new CreditClass();
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Sender Account Number: ");
        int senderAccount=sc.nextInt();
        System.out.print("\nEnter Receiver Account Number: ");
        int receiverAccount=sc.nextInt();
        System.out.print("\nEnter the amount to Transfer: ");
        double amount=sc.nextDouble();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
            try{
               if(!isSufficient(connection,senderAccount,amount)){
                   System.out.println("Insufficient Balance");
                   connection.rollback();
                   return;
               }
               d.debit(connection,senderAccount,amount);
               c.credit(connection,receiverAccount,amount);
               connection.commit();
                System.out.println("Transaction Successful");
            }catch(Exception e){
                connection.rollback();
                e.printStackTrace();
            }
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //check balance
    public static boolean isSufficient(Connection connection,int accountNumber,double amount) throws SQLException{
        String query="Select balance from accounts where acc_num=?";
        PreparedStatement ps=connection.prepareStatement(query);
        ps.setInt(1,accountNumber);
        ResultSet result=ps.executeQuery();
        if (result.next()) {
            double balance=result.getDouble("balance");
            return balance>=amount;
        }
        return false;
    }
}
