import java.sql.SQLException;

public class NoSuchRowException extends SQLException {
	private static final long serialVersionUID = 3071987092695251584L;

	public NoSuchRowException(String message) {
		super(message);
	}
}
