/*
 * Michael Nipper
 */
import java.math.BigInteger;


public class RSA {
	private int p;
	private int q;
	private int e;
	private int N;

	public RSA(int inputP, int inputQ, int inputE) {
		p = inputP;
		q = inputQ;
		e = inputE;
		N = p * q;
	}
	
	public int calculateD() {
		int temp[][] = new int[100][4];
		temp[0][0] = 1;
		temp[0][1] = 0;
		temp[0][2] = (p - 1) * (q - 1);
		temp[0][3] = 0;
		temp[1][0] = 0;
		temp[1][1] = 1;
		temp[1][2] = e;
		temp[1][3] = ((p - 1) * (q - 1)) / e;
		for (int i = 2; i < 100; i++) {
			temp[i][0] = temp[i-2][0] - temp[i-1][0] * temp[i - 1][3];
			temp[i][1] = temp[i-2][1] - temp[i-1][1] * temp[i - 1][3];
			temp[i][2] = temp[i-2][2] - temp[i-1][2] * temp[i - 1][3];
			temp[i][3] = temp[i-1][2] / temp[i][2];
			if (temp[i][2] <= 1) {
				int d = temp[i][1];
				int np = (p - 1) * (q - 1);
				if (d <= 0) { return np + d; }
				if (d > np) { return d % np; }
				return d;
			}
		}
		return -1;
	}
	
	public int encryptMessage(int m) {
		return (int) (Math.pow(m,e) % N);
	}
	
	public int decryptMessage(int c) {

		int d = calculateD();
		int iterations = (int) (Math.log( d ) / Math.log( 2 ));
		
		BigInteger runningCount = BigInteger.valueOf((long) Math.pow(c, 1) % N);
		long lastCalc = runningCount.longValue();
		
		String bitString = Integer.toBinaryString(d);

		for (int i = 0; i < iterations; i++) {
			lastCalc = (lastCalc * lastCalc) % N;
			if (bitString.charAt(bitString.length() - i - 2) == '1') {
				runningCount = runningCount.multiply(BigInteger.valueOf(lastCalc));

			}
		}

		
		BigInteger temp = BigInteger.valueOf(N);
		
		BigInteger answer = (runningCount.mod(temp));
		return answer.intValue();
	}
}
