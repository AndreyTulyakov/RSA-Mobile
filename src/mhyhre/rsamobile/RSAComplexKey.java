package mhyhre.rsamobile;

/**
 * Complex crypt key for RSA
 * @author andrew
 *
 */

public class RSAComplexKey {
	
	private final int e;
	private final int n;
	private final int d;
	
	public RSAComplexKey(int e, int n, int d) {
		this.e = e;
		this.n = n;
		this.d = d;
	}

	public int getE() {
		return e;
	}

	public int getN() {
		return n;
	}

	public int getD() {
		return d;
	}
	
}
