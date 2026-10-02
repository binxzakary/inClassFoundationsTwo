package reverseAStringMethod;

public class ReverseWord {
	public static void main(String[] args) {

		String str = "and Data and more stuff";
		String str2 = reverseWords(str);

	}

	static String reverseWords(String str) {
		String str2 = "";
		String temp = "";
		int index;
		int indexStart = str.length();
		int indexCopy;

		// filling array with individual letters
		for (index = str.length() - 1; index >= 0; index--) {
			temp = str.charAt(index) + "";
		}

		if (temp.equals(" ")) {
			for (indexCopy = index + 1; indexCopy < indexStart; indexCopy++) {
				str2 += str.charAt(indexCopy);
			}
			str2 += " ";
			indexStart = index;
		}

		for (indexCopy = 0; indexCopy < indexStart; indexCopy++) {
			str2 += str.charAt(indexCopy);

		}

		return str2;

	}

}
