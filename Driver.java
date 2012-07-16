import java.math.BigDecimal;
import java.math.BigInteger;

public class Driver {

	static long p = 31541;
	static long q = 43019;
	static long e = 3;
	static long d = 904525147;
	static long nPrime = (p - 1) * (q - 1);
	static long n = p * q;
	static long encrypted = 57980160;

	public static void main(String[] args) {
		RSA r = new RSA(31541, 43019, 3);
		System.out.println("D: " + r.calculateD());
		int cipher = r.encryptMessage(35);
		System.out.println("Encrypting 35: " + cipher);
		System.out.println("Decrypting " + cipher + ": " + r.decryptMessage(cipher));		
	}

}
