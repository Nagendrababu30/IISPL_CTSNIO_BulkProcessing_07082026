package com.iispl.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iispl.connectionpool.ConnectionPool;
import com.iispl.enums.AccountStatus;
import com.iispl.model.Account;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public Account getAccountByNumber(String accountNumber) {
		String query = "SELECT * FROM account WHERE account_number = ?";

		try (Connection connection = ConnectionPool.getDataSource().getConnection();
				PreparedStatement psmt = connection.prepareStatement(query);) {

			psmt.setString(1, accountNumber);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {

				Account account = null;

				account.setAccountNumber(rs.getString("account_number"));

				account.setStatus(AccountStatus.valueOf(rs.getString("status")));

				account.setAvailableBalance(rs.getBigDecimal("available_balance"));

				account.setAccountHolderName(rs.getString("account_holder_name"));

				return account;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateBalance(String accountNumber, BigDecimal amount) {
		String query = "UPDATE account SET available_balance = ? WHERE account_number = ?";

		try (Connection connection = ConnectionPool.getDataSource().getConnection();
				PreparedStatement psmt = connection.prepareStatement(query);) {

			psmt.setBigDecimal(1, amount);
			psmt.setString(2, accountNumber);

			int rows = psmt.executeUpdate();

			if (rows > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
