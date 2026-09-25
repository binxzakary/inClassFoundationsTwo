import java.util.Random;

public class Sep11thMethods {

	public static void main(String[] args) {
		int[] theArray = new int[10];
		int numbers = 10;
		int startIndex = 1;
//		int searchValue = 8;
		loadArray(theArray, numbers, startIndex);
//		showArray(theArray);
//		int location = linearSearch(theArray, searchValue);
		bubbleSort(theArray);
		showArray(theArray);
	}

	public static void showArray(int[] theArray) {
		int index;
		for (index = 0; index < theArray.length; index++) {
			System.out.printf("[%d]: %d\n", index, theArray[index]);
		}
	}

	public static void loadArray(int[] theArray, int num, int start) {
		Random rand = new Random();
		int index;
		for (index = 0; index < theArray.length; index++) {
			theArray[index] = rand.nextInt(num) + start;

		}
	}

	public static int linearSearch(int[] theArray, int value) {
		int location = -1;
		int index;
		for (index = 0; index < theArray.length; index++) {
			if (theArray[index] == value) {
				location = index;
				break;
			}
		}

		return location;

	}

	public static void bubbleSort(int[] array) {
		boolean sort = false;
		int i;
		int store;

		while (sort != true) {
			for (i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					store = array[i + 1];
					array[i + 1] = array[i];
					array[i] = store;
				}
			}
			sort = true;
			for (i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					sort = false;
				}
			}
		}
	}

	public static int binarySearch(int[] array, int search) {
		int low = 0;
		int high = array.length - 1;

		while (low <= high) {
			int middlePosition = (low + high) / 2;
			int middleNumber = array[middlePosition];

			if (search == middleNumber) {
				return middlePosition;
			}
			if (search < middleNumber) {
				high = middlePosition - 1;
			} else {
				low = middlePosition + 1;

			}
		}

		return -1;

	}

}
