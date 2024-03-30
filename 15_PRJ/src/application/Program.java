package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs =null;
		
		try {
			conn = DB.connection();
			st = conn.prepareStatement(
					"INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Miguel Nascimento");
			st.setString(2, "miguel.nasto@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("06/09/2005").getTime()));
			st.setDouble(4, 3500.00);
			st.setInt(5, 2);
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				System.out.println("Done! Rows affected: " + rowsAffected);
				while(rs.next()) {
					System.out.println("Done! Seller Id: " + rs.getInt(1));
				}
			}
			else {
				System.out.println("No rows affected!");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(st);
			DB.close();
		}
	}

}
