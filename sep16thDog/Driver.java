package sep16thDog;

public class Driver {

	public static void main(String[] args) {

		Cat cat1 = new Cat();
		Cat cat2 = new Cat();
		Cat cat3 = new Cat();

		cat1.setName("Toby");
		cat1.setBreed("meow");
		cat1.setAge(2);

		cat2.setName("Toby");
		cat2.setBreed("meow");
		cat2.setAge(2);

		cat3.setName("Toby");
		cat3.setBreed("meow");
		cat3.setAge(2);

		Cat[] cats = new Cat[3];
		cats[0] = cat1;
		cats[1] = cat2;
		cats[2] = cat3;

		cat1.show();
		Cat.showCount();

	}

}
