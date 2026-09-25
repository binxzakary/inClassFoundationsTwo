package sep16thDog;

public class Cat {

	static int noCats = 0;
	private String name;
	private String breed;
	private int age;

	public void setBreed(String breed) {
		this.breed = breed;
		if (breed.equals("")) {
			this.breed = "Mixed";
		}
	}

	public void setAge(int age) {
		this.age = 1;
		if (age >= 1 && age <= 20) {
			this.age = age;
		}
	}

	public void setName(String name) {
		this.name = name;
		if (name.equals("")) {
			this.name = "Cat";
		}
	}

	public String getBreed(String breed) {
		return this.breed;
	}

	public int getAge(int age) {
		return this.age;
	}

	public String getName() {
		return this.name;
	}

	public Cat() {
		Cat.noCats++;
	}

	public Cat(String name, String breed, int age) {
		this.name = name;
		this.breed = breed;
		this.age = age;
	}

	public void show() {
		System.out.printf("Name:  %s\nBreed: %s\nAge:   %d\n\n", this.name, this.breed, this.age);
	}

	public static void showCount() {
		System.out.printf("Cats: %d", noCats);
	}

}
