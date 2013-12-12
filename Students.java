import java.util.Random;
import java.util.Scanner;
import java.io.*;
public class Students {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. Choose random group?");
		System.out.println("2. Random Student?");
		int number = scanner.nextInt();
		File file = new File("class.csv");
		Scanner scan = new Scanner(file);
		String[] names = scanner.nextLine().split(",");

		for (int i = 0; i<names.length; i++) {
			System.out.println(names[i]);
		}
	}
}