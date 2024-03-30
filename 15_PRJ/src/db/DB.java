package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	private static Connection connection;
	
	public static Connection connection() {
		if(connection ==null) {
			try {
				Properties props = getProperties();
				String url = props.getProperty("dburl");
				connection = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DBException("Cannot instance a connection: " + e.getMessage());
			}
		}
		return connection;
	}
	
	public static void close() {
		if(connection !=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DBException("Cannot close instance of connection: " + e.getMessage());
			}
		}
	}
	
	private static Properties getProperties() {
		try (FileInputStream FS = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(FS);
			return props;
		} catch (FileNotFoundException e) {
			throw new DBException("File of properties not found!");
		} catch (IOException e) {
			throw new DBException(e.getMessage());
		}
	}
}
