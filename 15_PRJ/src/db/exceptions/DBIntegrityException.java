package db.exceptions;

public class DBIntegrityException extends DBException {
	private static final long serialVersionUID = 1L;

	public DBIntegrityException(String msg) {
		super(msg);
	}

}
