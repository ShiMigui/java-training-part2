package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import db.DB;
import db.exceptions.DBIntegrityException;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.connection();
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?;");
			
			st.setInt(1, 4);
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Done! Rows affected: " + rowsAffected);
			}
			else {
				System.out.println("No rows affected!");
			}	
		} catch (SQLException e) {
			throw new DBIntegrityException(e.getMessage());
		} finally {
			DB.close(st);
			DB.close();
		}
	}

}
