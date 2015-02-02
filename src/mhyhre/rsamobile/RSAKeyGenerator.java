package mhyhre.rsamobile;

import java.util.Random;

public final class RSAKeyGenerator {

	private static final int COT = 20;
	private static final int CDO = 1000;
	private static boolean res;

	/**
	 * Функция генерации ассиметричного ключа
	 * @return
	 */
	public static RSAComplexKey generate() {

		Random rn = new Random();

		long p = 0;
		long q = 0;

		long n = 0;
		long e = 0;
		long d = 0;

		long temp = 1;
		long pr;
		int b;

		b = CDO;
		p = (long) getSmallSimpleUsingBrute();
		res = false;
		q = (long) getSmallSimpleUsingBrute();
		res = false;

		pr = (p - 1) * (q - 1);
		n = p * q;

		do {
			d = (long) (1 + rn.nextInt(b));
		} while (!isMutalSimple(pr, d));

		do {
			temp++;
		} while ((pr * temp + 1) % d != 0);

		e = (pr * temp + 1) / d;

		return new RSAComplexKey((int) e, (int) n, (int) d);
	}

	/**
	 * Определение взаимно простых чисел
	 */
	private static boolean isMutalSimple(long a, long b)
	{
		long r;
		r = a % b;
		if (r == 0) {
			return b == 1;
		} else {
			res = isMutalSimple(b, r);
		}
		return res;
	}

	/**
	 * Поиск случайного простого числа в некотором диапазоне
	 */
	private static int getSmallSimpleUsingBrute() // простое число
	{
		int PseudoSimple;
		int a, b;
		boolean check;
		int limit;
		a = COT;
		b = CDO;

		Random rn = new Random();

		while (true) {
			check = true;
			PseudoSimple = a + rn.nextInt(b);

			limit = (int) Math.sqrt(PseudoSimple);

			int[] numbers = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
					43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };

			for (int i : numbers) {
				if (PseudoSimple == i) {
					return PseudoSimple;
				}

				if (PseudoSimple % i == 0) {
					check = false;
					break;
				}
			}
			if (check) {
				for (int i = 103; i <= limit; i++) {
					if (PseudoSimple % i == 0) {
						check = false;
						break;
					}

				}
			}
			if (check) {
				return (int) PseudoSimple;
			}

		}
	}

}
