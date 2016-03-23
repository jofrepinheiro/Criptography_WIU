//This is program written by Binto George for CS395 Students

import java.util.Scanner;
import java.security.SecureRandom;
import java.math.BigInteger;

class XOR{

	public static void main (String args[]) 
	{
		try {

			Scanner in = new Scanner(System.in); //define scanner for input

			System.out.println("What's your name?");
			String name = in.nextLine();

			while(true){
				System.out.println(name+", what do you wish to do?. \n Type 1 to encrypt a message. \n Type 2 to decrypt a message. \n Type 0 to exit.");
				String select = in.nextLine();

				while( (!select.equals("1")) && (!select.equals("2")) &&  (!select.equals("0"))) {
					System.out.println("Wrong character typed. Please, try again.");
					System.out.println("What do you wish to do? \n Type 1 to encrypt a message. \n Type 2 to decrypt a message. \n Type 0 to exit.");
					select = in.nextLine();
				}

				switch(select){
				case "0":
					System.out.println("Finishing program...");
					System.exit(0);
				case "1":
					encrypt();
					break;
				case "2":
					decrypt();
					break;
				}
				System.out.println("");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void encrypt() {
		try{
			//Read a line from keyboard
			String messageString;  
			String password;
			Scanner in = new Scanner(System.in); //define scanner for input

			System.out.println("Please enter the message:");
			messageString = in.nextLine(); //read a line
			System.out.println("Original Message: "+ messageString);

			System.out.println("Please, type the password. Passwords must contain at least 5 characters, letters, numbers and special characters.");
			password = in.nextLine();
			while((!(password.matches(".*\\W.*") && password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*")))||(password.length()<5)){
				System.out.println("Password is not safe enough, please type a new one.");//Regular expression to test Password safety
				password = in.nextLine();
			}

			BigInteger message = convertStringToBigInteger(messageString);		

			//Set up a secure random number generator
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); 
			byte[] seed = password.getBytes();  
			random.setSeed(seed);  //initialize the random number generator

			//Generate Key (Warning! botched)
			byte[] keyStream = new byte[messageString.length()]; // usability++
			random.nextBytes(keyStream); //generate random bytes in put in keyStream
			BigInteger key = new BigInteger(keyStream);


			//XOR encryption -- Sender's (Alice's) end
			BigInteger cipherText = message.xor(key);

			//Convert ciphertext to a string so that we can print it
			String cipher = convertBigIntegerToString(cipherText);
			
			System.out.println("Plaintext (in binary):" + message.toString(2));
			System.out.println("Key (in binary):" + key.toString(2));
			System.out.println("Ciphertext (in binary):" + cipherText.toString(2));
			System.out.println("Ciphertext (in characters): "+ cipher);
			
			
			password = null;
			System.gc();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//This is a method to convert a String to BigInteger by
	//packing each character into a BigInteger
	//Input: String
	//Output: BigInteger
	public static BigInteger convertStringToBigInteger(String s)
	{
		BigInteger b = new BigInteger("0");
		for (int i = 0; i < s.length(); i++)
		{
			Integer code = new Integer((int)s.charAt(i));
			BigInteger c = new BigInteger(code.toString());
			b = b.shiftLeft(8);
			b = b.or(c);
		}
		return b;
	}


	private static void decrypt() {
		try{


			Scanner in = new Scanner(System.in);

			System.out.println("Please type the ciphertext:");
			String cipherText = in.nextLine();

			System.out.println("Please type the password: ");
			String password = in.nextLine();

			SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); 
			byte[] seed = password.getBytes();  
			random.setSeed(seed);  //initilize the random number generator

			//Generate Key (Warning! botched)
			byte [] keyStream = new byte[cipherText.length()]; // usability++
			random.nextBytes(keyStream); //generate random bytes in put in keyStream
			BigInteger key = new BigInteger(keyStream);

			BigInteger cipher = convertStringToBigInteger(cipherText);	

			BigInteger receivedMessage = cipher.xor(key);
			String receivedMessageString = convertBigIntegerToString(receivedMessage);		
			System.out.println("Received Message:"+ receivedMessageString);


		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	//This is a method to convert a BigInteger to String
	//by converting each byte into a character and forming 
	//a string of characters.
	//Input: BigInteger
	//Output: String

	public static String  convertBigIntegerToString(BigInteger b)
	{ 
		String s = new String();
		while (b.compareTo(BigInteger.ZERO) == 1)
		{
			BigInteger c = new BigInteger("11111111", 2);
			int cb = (b.and(c)).intValue();
			Character cv= new Character((char)cb);
			s = (cv.toString()).concat(s);
			b = b.shiftRight(8);
		}
		return s;
	}
}

