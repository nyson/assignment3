	/**
	 * Exception for lack of funds in the account
	 * @author Jonathan Skårstedt
	 * @author Oskar Linder Pålsgård
	 * @author Magnus Duberg
	 */
	public class NotEnoughMineralsException extends Exception {
		static final long serialVersionUID = 13945013;
		public NotEnoughMineralsException(String message) {
			super(message);
		}
	}