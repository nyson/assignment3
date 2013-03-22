import java.sql.SQLException;

public class NoSuchRowException extends SQLException {
	public NoSuchRowException(String message) {
		super(message);
	}
}
