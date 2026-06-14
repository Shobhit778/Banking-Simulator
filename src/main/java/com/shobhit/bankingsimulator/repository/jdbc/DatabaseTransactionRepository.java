package com.shobhit.bankingsimulator.repository.jdbc;

import com.shobhit.bankingsimulator.db.DbConnection;
import com.shobhit.bankingsimulator.enums.TransactionType;
import com.shobhit.bankingsimulator.model.Transaction;
import com.shobhit.bankingsimulator.repository.TransactionRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static java.lang.String.valueOf;

@Repository
@Primary
public class DatabaseTransactionRepository implements TransactionRepository {

    @Override
    public void save(Transaction transaction){

    String query = "INSERT INTO Account_Transactions" +
                   "(transaction_id, account_number, transaction_type, transaction_amount, transaction_date)" +
                   "VALUES(?, ?, ?, ?, ?)";

    try(Connection connection = DbConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)){

        ps.setLong(1, transaction.getTransactionId());
        ps.setLong(2, transaction.getAccountNumber());
        ps.setString(3, valueOf(transaction.getTransactionType()));
        ps.setDouble(4, transaction.getAmount());
        ps.setTimestamp(5, Timestamp.valueOf(transaction.getTransactionDate()));
        ps.executeUpdate();
    }
    catch(SQLException e){
        throw new RuntimeException("Unable to Save the Transaction ");
    }
    }

    @Override
    public List<Transaction> getTransactionHistory(long accountNumber){

        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM Account_Transactions WHERE account_number = ? " +
                        "ORDER BY transaction_date DESC";

        try(Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setLong(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getLong("transaction_id"));
                transaction.setAccountNumber(rs.getLong("account_number"));
                transaction.setTransactionDate( rs.getTimestamp("transaction_date").toLocalDateTime());
                transaction.setTransactionType(TransactionType.valueOf(rs.getString("transaction_type")));
                transaction.setAmount(rs.getDouble("transaction_amount"));
                transactions.add(transaction);
            }
        }
        catch(SQLException e){
            throw new RuntimeException("Unable to Fetch Transaction History");
        }
        return transactions;
    }

    @Override
    public long getMaxTransactionId(){

        String query = "SELECT MAX(transaction_id) as transaction_id FROM Account_Transactions";

        try(Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                long max = rs.getLong("transaction_id");
                if(rs.wasNull()) {
                    return 0;
                }
            return max;
            }
        }
        catch(SQLException e){
            throw new RuntimeException("Unable to get the maximum id");
        }
        return 0;
    }
}
