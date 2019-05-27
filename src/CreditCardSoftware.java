import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class CreditCardSoftware {

	public static String creditCardData = "\\C:\\Users\\saran\\eclipse-workspace\\"
			+ "Credit Card Process Application\\creditCardData.txt";
	public static final int NUMBER = 8;

	public static void main(String[] args) throws IOException {
		// menu();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(creditCardData, "rw");
			Scanner scanner = new Scanner(System.in);
			boolean complete = false;

			while (!complete) {

				int choise = menu();
				long cardNumber;
				int position;

				switch (choise) {
				case 1: // add a new card
					CreditCardDetails ccd = getNewCardDetails();
					raf.seek(raf.length());
					raf.writeLong(ccd.getCreditCardNumber());
					raf.writeDouble(ccd.getCreditCardBalance());
					System.out.println(ccd.toString() + "added to the system");
					break;
				case 2:
					System.out.println("Please enter the 8-digit number of the card to view:");
					cardNumber = Long.parseLong(scanner.nextLine());
					position = findCard(cardNumber, raf);
					if (position >= 0) {
						System.out.println("");
						showCardDetails(position, raf);
						System.out.println("");
					} else {
						System.out.println("No card with that number found.");
					}
				case 3:
					System.out.println("enter the 8 digit number of the card you want to view!");
					cardNumber=Long.parseLong(scanner.nextLine());
					position = findCard(cardNumber, raf);
					if(position>=0) {
						double newBalance=getCardsBalance();
						if(newBalance>=0.0) {
						raf.seek(position*NUMBER);
						raf.writeLong(cardNumber);
						raf.writeDouble(newBalance);
						System.out.println("the card with number: "+ cardNumber+ "and has balance:"+newBalance);
						}
					}else {
						System.out.println("card not found..try again!");
					}
					break;
					
				case 4:
					System.out.println("");
					long allCardsList=raf.length()/NUMBER;
					if(allCardsList==0) {
						System.out.println("there are no records....please add a card");
					}else {
						for (int i=0; i<allCardsList; i++) {
							showCardDetails(i,raf);
						}
					}
					System.out.println("");
					break;
				case 5:
					// default:
					complete = true;
					break;
				}// switch

			} // while

			System.out.println("Thank you for using Sarantis Technologies");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} // catch
	} // main

	private static int findCard(long cardNumber, RandomAccessFile raf) throws IOException {
		long totalInputs = raf.length() / NUMBER;
		for (int i = 0; i < totalInputs; i++) {
			raf.seek(i * NUMBER);

			long num = raf.readLong();
			if (num == cardNumber) {
				return i;
			}
		}
		return -1;
	}

	private static CreditCardDetails getNewCardDetails() {
		CreditCardDetails ccd = new CreditCardDetails();
		ccd.setCreditCardBalance(getCardsBalance());
		ccd.setCreditCardNumber(getCardsNumber());
		return ccd;
	}

	private static long getCardsNumber() {
		Scanner scanner = new Scanner(System.in);
		String pattern = "[0-9]{8}";
		boolean valid = false;
		while (!valid) {
			System.out.println("enter a valid number with 8 digits!!!");
			String userNumber = scanner.nextLine();
			valid = userNumber.matches(pattern); // validate the 8 digit number matches the format on the line above
			if (valid) {
				return Long.parseLong(userNumber);
			}
		}

		return 0;
	}

	private static double getCardsBalance() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter the balance of the card");
		return Double.parseDouble(scanner.nextLine());
	}

	private static int menu() {
		stars();
		System.out.println("Welcome to Sarandis Technologies!!");
		System.out.println("Please choose one of the following options");
		stars();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Press 1 for adding a new credit card");
		System.out.println("Press 2 for viewing details of the card");
		System.out.println("Press 3 to update card details");
		System.out.println("Press 4 to list all cards ");
		System.out.println("Press 5 to quit");
		stars();
		return Integer.parseInt(scanner.nextLine());
	}

	public static void stars() {
		System.out.println("******************************************");
	}

	private static void showCardDetails(int position, RandomAccessFile r) throws IOException {
		r.seek(position * NUMBER);
		CreditCardDetails ccd = new CreditCardDetails(r.readLong(), r.readDouble());
		System.out.println("Card Details: " + ccd.toString());
	}

}