package com.shobhit.bankingsimulator.repository.jdbc;

import com.shobhit.bankingsimulator.db.DbConnection;
import com.shobhit.bankingsimulator.enums.AccountType;
import com.shobhit.bankingsimulator.model.Account;
import com.shobhit.bankingsimulator.repository.AccountRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Primary
public class DatabaseAccountRepository implements AccountRepository {

    @Override
    public void save(Account account){
       String query = "INSERT INTO accounts(account_number, holder_name, phone_number, account_type, balance)" +
                       "VALUES(?, ?, ?, ?, ?)";
        try(Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setLong(1, account.getAccountNumber());
            ps.setString(2, account.getHolderName());
            ps.setString(3, account.getPhoneNumber());
            ps.setString(4, account.getAccountType().name());
            ps.setDouble(5, account.getBalance());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Account findByAccountNumber(long accountNumber) {

     String query = "SELECT * FROM accounts WHERE account_number = ?";

     try(Connection connection = DbConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(query)){
         ps.setLong(1, accountNumber);

         try(ResultSet rs = ps.executeQuery()) {
         if(rs.next()){
             Account account = new Account();

             account.setAccountNumber(rs.getLong("account_number"));
             account.setAccountType(AccountType.valueOf(rs.getString("account_type")));
             account.setBalance(rs.getDouble("balance"));
             account.setPhoneNumber(rs.getString("phone_number"));
             account.setHolderName(rs.getString("holder_name"));
             return account;
         }
         return null;
         }
     }
      catch(SQLException e){
         throw new RuntimeException("Unable to Fetch Account");
      }
    }

    @Override
    public void deleteAccount(long accountNumber){

    String query = "DELETE FROM accounts WHERE account_number = ?";

        try(Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setLong(1, accountNumber);
            ps.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException("Unable to Fetch Account");
        }
    }

    @Override
    public void update(Account account){

        String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try(Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setDouble(1, account.getBalance());
            ps.setLong(2, account.getAccountNumber());
            ps.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException("Unable to update the balance");
        }
    }

    @Override
    public long getMaxAccountNumber(){

        String query = "SELECT MAX(account_number) as account_number FROM accounts";

        try(Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                long max = rs.getLong("account_number");
             if(rs.wasNull()){
                 return 1000;
             }
            return max;
            }
        }
        catch(SQLException e){
            throw new RuntimeException("Unable to get the account number");
        }
        return 1000;
    }
}
