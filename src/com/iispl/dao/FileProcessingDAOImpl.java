package com.iispl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.iispl.connectionpool.ConnectionPool;
import com.iispl.model.FileProcessingSummary;

public class FileProcessingDAOImpl implements FileProcessingDAO {

	@Override
	public boolean saveFileProcessingSummary(FileProcessingSummary fileProcessingSummary) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT INTO file_processing_summary "
	            + "(batch_id, file_name, total_records, failure_records, successfull_records) "
	            + "VALUES (?, ?, ?, ?, ?)";

	    try (Connection connection = ConnectionPool.getDataSource().getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	        preparedStatement.setString(1, fileProcessingSummary.getBatchId());
	        preparedStatement.setString(2, fileProcessingSummary.getFileName().toString());
	        preparedStatement.setLong(3, fileProcessingSummary.getTotalRecords());
	        preparedStatement.setLong(4, fileProcessingSummary.getFailureRecords());
	        preparedStatement.setLong(5, fileProcessingSummary.getSuccessfullRecords());

	        int rows = preparedStatement.executeUpdate();

	        if (rows > 0) {
	            return true;
	        } else {
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return false;
	}

}
