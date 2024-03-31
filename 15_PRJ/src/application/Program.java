package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;
import db.exceptions.DBException;
import db.exceptions.DBIntegrityException;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.connection();
			conn.setAutoCommit(false);
			st = conn.createStatement();

			/*int rows = st.executeUpdate("UPDATE seller SET BaseSalary = 2000 WHERE DepartmentId = 1");
			if (rows > 0) {
				System.out.println("Done! Rows affected: " + rows);
			}*/
			
			rows = 1;
			if (rows < 2) {
				throw new SQLException("[FAKE] ERROR");
			}

			rows = st.executeUpdate("UPDATE seller SET BaseSalary = 3000 WHERE DepartmentId = 2");
			if (rows > 0) {
				System.out.println("Done! Rows affected: " + rows);
			}

			conn.commit(); // envia para o BD

		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DBException("Transaction rolled back!", e.getMessage());
			} catch (Exception e2) {
				throw new DBIntegrityException("Error trying rollback!", e.getMessage());
			}
		} finally {
			DB.close(st);
			DB.close();
		}
	}

}
