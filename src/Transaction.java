import java.sql.*;

public class Transaction {
	/**
	 * create table gjordatrans (
	 * kontonr char(13),
	 * typ char(3),
	 * belopp double,
	 * OCRmeddelande varchar(70)
	 */
	
	public class InvalidOCRException extends Exception {
		private static final long serialVersionUID = 7311045584954926981L;
		public InvalidOCRException(String m){
			super(m);
		}
		
	}
	
	
	
	private String account;
	private String OCRMessage = "";
	private double amount;
	private Type type;
	private boolean usingOCR;
	
	public enum Type {DEPOSIT, WITHDRAWAL};

	
	
	
	public Transaction(String acc, Transaction.Type t, double am, String ocr)
			throws InvalidOCRException {
		account = acc;
		type = t;
		amount = am;
		if(validateOCR(ocr)) {
			usingOCR = true;
			setOCRMessage(ocr);
		} else
			usingOCR = false;

		
	}	
	
	public Transaction(String acc, Transaction.Type t, double am){
		usingOCR = false;
		account = acc;
		type = t;
		amount = am;
		
	}	

	
	private String typeToString(Type t){
		switch(t) {
		case WITHDRAWAL:
			return "utt";
			
		default:	
		case DEPOSIT: 
			return "ins";
		}
	}
	
	/**
	 * Validate an OCR number according to the Luhn algorithm
	 * http://en.wikipedia.org/wiki/Luhn_algorithm
	 *
	 * @param ocr OCR number to validate
	 * @return true on valid OCR number
	 */
	private boolean validateOCR(String ocr) {

		try {
			Double.parseDouble(ocr);
		} catch (NumberFormatException e) {
			return false;
		}

		if (2 > ocr.length() || ocr.length() > 15)
			return false;

		/*
		 * Walks through the number backwards, one number at a time
		 * 	* if number is even, add it to the checksum
		 *  * if odd, multiply number by two and add each resulting 
		 *  	number to the checksum  
		 */
		int checksum = 0;
		boolean alt = false;

		for (int i = ocr.length() - 1; i >= 0; i--){
			int n = Integer.parseInt(ocr.substring(i, i + 1));

			if(alt) {
				n *= 2;	
				if(n > 10)
					n++;
			}

			checksum += n % 10;
			alt = !alt;
		}

		/*
		 * If result is a multiple of ten, we have a valid checksum! 
		 */
		return (checksum % 10 == 0);
	}
	
	public void register() throws SQLException{
		String query = "INSERT INTO gjordatrans "
				+ "(kontonr, typ, belopp, OCRmeddelande) "
				+ "VALUES (?, ?, ?, ?)";
		
		PreparedStatement ps = DB.prepareStatement(query);
		ps.setString(1, account);
		ps.setString(2, typeToString(type));
		ps.setDouble(3, amount);
		if(usingOCR)
			ps.setString(4, OCRMessage);
		else
			ps.setString(4, "");
		
		ps.executeUpdate();
		
	}
	
	
	// getters
	public String getOCRMessage() {return OCRMessage; }
	public String getAccount() {return account; }
	public double getAmount() {return amount;}
	public Type getType() {return type; }
	
	// setters
	/**
	 * Sets the OCR Message to param, and evaluates it according to the luhn
	 * 	algorithm
	 * @param ocr OCR number to validate
	 * @throws InvalidOCRException On invalid OCR
	 */
	public void setOCRMessage(String ocr) throws InvalidOCRException{
		if(!validateOCR(ocr))
			throw new InvalidOCRException("'"+ ocr +"' is not a valid OCR!");
		
	}

	/**
	 * Sets account and checks that it exists in the database
	 * @param acc Account number
	 * @throws SQLException 
	 * @throws NoSuchRowException
	 */
	public void setAccount(String acc) 
			throws SQLException, NoSuchRowException{
		Accounts as = new Accounts();
		Account a = as.getAccountByAccountNo(acc);
		
		account = a.getAccountNo();
	}
	
	
	public static String toStringHeader(){
		String format = "%-15s | %35s | %20s | %5s ";
		return String.format(format, "Konto", "Typ", "Belopp", "OCR");
	}
	
	public String toString(){
		String format = "%-15s | %35s | %20s | %5s ";
		
		return String.format(format, account, typeToString(type), 
				amount, OCRMessage);

		
	}
	
	public void setAmount(double a){amount = a;}
	public void setType(Type t) {type = t; }
	
}
	
	

	
