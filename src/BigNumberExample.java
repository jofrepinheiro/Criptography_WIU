import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
class BigNumberExample{

	public static void main (String args[]) {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			// Reading A
			System.out.println("Enter number a");
			String aval = in.readLine();
			while (!aval.matches("[0-9]+")){
				System.out.println("Wrong set of characters, please try again.");
				aval = in.readLine();
			}

			// Reading B
			System.out.println("Enter number b");
			String bval = in.readLine();
			while (!bval.matches("[0-9]+")){
				System.out.println("Wrong set of characters, please try again.");
				bval = in.readLine();
			}

			BigInteger a = new BigInteger(aval);
			BigInteger b = new BigInteger(bval);

			// A XOR B
			System.out.println(a + " XOR " + b + " = " + a.xor(b));

			// A XOR A
			System.out.println(a + " XOR " + a + " = " + a.xor(a));

			// A XOR B XOR A
			System.out.println(a + " XOR " + b + " XOR " + b + " = " + a.xor(b.xor(b)));

			// A POWER B MOD N
			System.out.print("Type a number N: ");
			String nval = in.readLine();
			BigInteger n = new BigInteger(nval);
			System.out.println(a+ " power " + b + " mod " + n + " equals " + a.modPow(b, n));

			//A SHIFT RIGHT 4 TIMES
			System.out.println(a + " shifted 4 times to the right is: " + a.shiftRight(4));

			//B SHIFT LEFT 4 TIMES
			System.out.println(b + " shifted 4 times to the left is: " + b.shiftLeft(4));


		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}