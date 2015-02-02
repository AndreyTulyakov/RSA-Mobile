package mhyhre.rsamobile;

import java.util.ArrayList;

import android.util.Log;

public final class RSACrypter {

	/**
	 * ���������� ������
	 * @param sourceData - �������� ������
	 * @param key - ����
	 * @return - ������������ ������������ ������������� � ���� �������.
	 */
	public static int[] EnCrypt(String sourceData, RSAComplexKey key) {
		int[] resultString = new int[sourceData.length()];

		for (int letterIndex = 0; letterIndex < sourceData.length(); letterIndex++) {
			int currentLetter = sourceData.codePointAt(letterIndex);	
			int resultLetterCode = divRest(currentLetter, key.getE(), key.getN());
			resultString[letterIndex] = resultLetterCode;	
		}

		return resultString;
	}

	/**
	 * ����������� � ������
	 * @param sourceData - ����������� ������������� ������
	 * @param key - ����
	 * @return -  �������������� ������
	 */
	public static String DeCrypt(int[] sourceData, RSAComplexKey key) {
		StringBuilder resultString = new StringBuilder(sourceData.length);

		for (int letterIndex = 0; letterIndex < sourceData.length; letterIndex++) {
			int currentCode = sourceData[letterIndex];
			int resultLetterCode = (int) divRest(currentCode, key.getD(),
					key.getN());
			Log.i("some", "letter:" + resultLetterCode);

			resultString.append((char)resultLetterCode);
		}

		return resultString.toString();
	}

	/**
	 * �������������� ������� ����� � ������. 
	 * ����� ������������ � ����������������� ������������� � ������������ - ��������.
	 */
	public static String convertLongArrayToString(int[] sourceData) {

		StringBuilder buf = new StringBuilder(200);
		for (int index = 0; index < sourceData.length; index++) {
			if (buf.length() > 0)
				buf.append(' ');
			buf.append(String.format("%04x", (int) sourceData[index]));
		}

		return buf.toString();

	}

	/**
	 * ������� ������ � ������� � ������ �����.
	 */
	public static int[] convertStringToLongArray(String sourceData) {

		String[] sourceArray = sourceData.split(" ");
		ArrayList<Integer> resultList = new ArrayList<>(sourceArray.length);

		try {
			for (int index = 0; index < sourceArray.length; index++) {
				String currentWord = sourceArray[index];
				if (currentWord.length() > 0 && currentWord.charAt(0) != '|') {
					resultList.add(Integer.parseInt(currentWord, 16));
				}
			}
		} catch (NumberFormatException e) {
			// do nothing
		}

		int[] correctResult = new int[resultList.size()];
		for (int index = 0; index < resultList.size(); index++) {
			correctResult[index] = resultList.get(index);
		}
		return correctResult;
	}

	/**
	 * ������� (a^k)mod(n) ����������� ����
	 */
	private static int divRest(long a, long k, long n)
	{
		long b = 1;

		while (k != 0) {
			if (k % 2 == 0) {
				k /= 2;
				a = (a * a) % n;
			} else {
				k--;
				b = (b * a) % n;
			}
		}
		return (int)b;
	}
}
