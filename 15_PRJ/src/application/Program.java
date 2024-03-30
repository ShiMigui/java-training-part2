package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.connection();
			st = conn.prepareStatement("UPDATE seller SET BaseSalary = BaseSalary + ? WHERE (DepartmentId = ?)");
			
			st.setDouble(1, 200.0);
			st.setInt(2, 2);
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Done! Rows affected: " + rowsAffected);
			}
			else {
				System.out.println("No rows affected!");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(st);
			DB.close();
		}
	}

}
