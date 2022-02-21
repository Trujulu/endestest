import java.util.*;

class InputManager {

	static Scanner sc = new Scanner(System.in);

	public static int readNumber(String message) { // numero entero
		String str;
		int num = 0;
		boolean isValid = false;

		do {
			System.out.println(message);
			str = sc.next();

			try {
				num = Integer.parseInt(str);
				isValid = true; 
			} catch (NumberFormatException ex) {
				System.out.println("No has introducido un numero entero. Intentalo de nuevo");
			}
		} while(!isValid);

		return num;
	}

	public static double readNumberDec(String message) { // numero decimal (double)
		String str;
		double num = 0;
		boolean isValid = false;

		do {
			System.out.println(message);
			str = sc.next();

			try {
				num = Double.parseDouble(str);
				isValid = true;
			} catch (NumberFormatException ex) {
				System.out.println("No has introducido un numero. Intentalo de nuevo");
			}
		} while(!isValid);

		return num;
	}

	public static float readNumberDecF(String message) { // numero decimal (float)
		String str;
		float num = 0f;
		boolean isValid = false;

		do {
			System.out.println(message);
			str = sc.next();

			try {
				num = Float.parseFloat(str);
				isValid = true;
			} catch (NumberFormatException ex) {
				System.out.println("No has introducido un numero. Intentalo de nuevo");
			}
		} while(!isValid);

		return num;
	}
	
	public static String readString(String message) { // cadena de texto
		String str = "";
		boolean isValid = false;

		do {
			System.out.println(message);
			str = sc.nextLine();

			if (str != "") {
				isValid = true; 
			} else {
				System.out.println("No has introducido ningun dato. Intentalo de nuevo");
			}
		} while(!isValid);

		return str;
	}
}