package com.iispl.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.iispl.connectionpool.ConnectionPool;
import com.iispl.model.TransactionRequest;
import com.iispl.model.TransactionResult;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public boolean saveTransaction(TransactionRequest request, TransactionResult result) {
		String insertSql = """
				INSERT INTO transactions
				(transaction_id, batch_id, from_account, to_account,
				 transaction_amount, transaction_type, source_file,
				 remarks, status, processing_date)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";
		try (Connection connection = ConnectionPool.getDataSource().getConnection();
				PreparedStatement prepStmt = connection.prepareStatement(insertSql)) {
			prepStmt.setString(1, request.getTransactionId());
			prepStmt.setString(2, request.getBatchId());
			prepStmt.setString(3, request.getFromAccount());
			prepStmt.setString(4, request.getToAccount());
			prepStmt.setBigDecimal(5, request.getTransactionAmount());
			prepStmt.setString(6, request.getTransactionType().toString().toUpperCase());
			prepStmt.setString(7, result.getSourceFile().toString());
			prepStmt.setString(8, result.getRemarks());
			prepStmt.setString(9, result.getStatus().toString());
			prepStmt.setDate(10,Date.valueOf(result.getProcessingDate()));

			int rowsAffected = prepStmt.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}

		} catch (Exception ex) {
			ex.getMessage();
		}
		return false;
	}

}
