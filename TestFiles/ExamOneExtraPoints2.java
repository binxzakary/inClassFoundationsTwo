package TestFiles;

public class ExamOneExtraPoints2 {

	public static void main(String[] args) {
		String str = "hello";
		String str1 = reverse(str);

		System.out.println(str1);

	}

	public static String reverse(String str) {
		int index;
		String str1 = "";
		for (index = str.length() - 1; index >= 0; index--) {
			str1 += str.charAt(index) + "";
		}

		return str1;
	}

}
