package mhyhre.rsamobile;

import java.util.ArrayList;

import android.util.Log;

public final class RSACrypter {

	private static final int UNICODE_MULTIPLIER = 255;
	
	/**
	 * Шифрование строки
	 * @param sourceData - исходная строка
	 * @param key - ключ
	 * @return - посимвольное кодированное представление в виде массива.
	 */
	public static long[] EnCrypt(String sourceData, RSAComplexKey key) {
		long[] resultString = new long[sourceData.length()];

		for (int letterIndex = 0; letterIndex < sourceData.length(); letterIndex++) {
			long currentLetter = (sourceData.codePointAt(letterIndex)*UNICODE_MULTIPLIER + letterIndex);	
			long resultLetterCode = divRest(currentLetter, key.getE(), key.getN());
			resultString[letterIndex] = resultLetterCode;	
		}

		return resultString;
	}

	/**
	 * Расшифровка в строку
	 * @param sourceData - посимвольно зашифрованная строка
	 * @param key - ключ
	 * @return -  расшифрованная строка
	 */
	public static String DeCrypt(long[] sourceData, RSAComplexKey key) {
		StringBuilder resultString = new StringBuilder(sourceData.length);

		for (int letterIndex = 0; letterIndex < sourceData.length; letterIndex++) {
			long currentCode = sourceData[letterIndex];
			long resultLetterCode = divRest(currentCode, key.getD(),key.getN());
			char resultCharCode = (char) ((resultLetterCode - letterIndex)/UNICODE_MULTIPLIER);
			resultString.append(resultCharCode);
		}

		return resultString.toString();
	}

	/**
	 * Преобразование массива чисел в строку. 
	 */
	public static String convertLongArrayToString(long[] sourceData) {

		StringBuilder buf = new StringBuilder(200);
		for (int index = 0; index < sourceData.length; index++) {
			if (buf.length() > 0)
				buf.append(' ');
			buf.append(Long.toString(sourceData[index]));
		}

		return buf.toString();
	}

	/**
	 * Парсинг строки с числами в массив чисел.
	 */
	public static long[] convertStringToLongArray(String sourceData) {

		String[] sourceArray = sourceData.split(" ");
		ArrayList<Long> resultList = new ArrayList<>(sourceArray.length);

		try {
			for (int index = 0; index < sourceArray.length; index++) {
				String currentWord = sourceArray[index];
				if (currentWord.length() > 0 && currentWord.charAt(0) != ' ') {
					resultList.add(Long.parseLong(currentWord));
				}
			}
		} catch (NumberFormatException e) {
			// do nothing
		}

		long[] correctResult = new long[resultList.size()];
		for (int index = 0; index < resultList.size(); index++) {
			correctResult[index] = resultList.get(index);
		}
		return correctResult;
	}

	/**
	 * Функция (a^k)mod(n) ускоренного типа
	 */
	private static long divRest(long a, long k, long n)
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
		return b;
	}
}
