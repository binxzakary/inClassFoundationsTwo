package TestFiles;

public class ExamOneExtraPoints {

	public static void main(String[] args) {
		int[] array1 = { 10, 20, 30, 40 };
		int[] array2 = { 10, 9, 8, 7, 6, 4, 2 };
		int[] array3 = new int[array1.length + array2.length];

		int index;

		array3 = combine(array1, array2);

		for (index = 0; index < array3.length; index++) {
			System.out.printf("[%d] = %d\n", index, array3[index]);
		}

	}

	public static int[] combine(int[] array1, int[] array2) {
		int index;
		int index2 = 0;
		int[] array3 = new int[array1.length + array2.length];

		for (index = 0; index < array1.length; index++) {
			array3[index] = array1[index];
		}
		for (index = 0; index < array2.length; index++) {
			array3[array1.length + index] = array2[index];
		}

		System.out.println(array1.length);
		return array3;
	}

}
