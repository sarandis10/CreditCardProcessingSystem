
public class CreditCardDetails {
	private long _cardNumber;
	private double _balance;

	public CreditCardDetails() {
		// empty
	}

	public CreditCardDetails(long cardNumber, double balance) { // Instantiate the variables
		_cardNumber = cardNumber;
		_balance = balance;
	}

	public long getCreditCardNumber() {
		return _cardNumber;
	}

	public void setCreditCardNumber(long cardNumber) {
		_cardNumber = cardNumber;
	}

	public double getCreditCardBalance() {
		return _balance;
	}

	public void setCreditCardBalance(double balance) {
		_balance = balance;
	}

	public String toString() {
		return String.format("the Credit card number is: %d \t with a balance: $%.2f", getCreditCardNumber(),
				getCreditCardBalance());

	}
}
