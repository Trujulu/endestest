public class Person {

	public Person() {} // CONSTRUCTOR: vacio

	public Person(String n, int a, int x) { // CONSTRUCTOR: nombre + edad + sexo
		setName(n);
		setAge(a);
		setSex(x);
		setIsDefined(true);
	}

	public Person(String n, String s, String d, int x, int a, double w, double h) { // CONSTRUCTOR: todos los datos
		setName(n);
		setSurname(s);
		setDNI(d);
		setSex(x);
		setAge(a);
		setWeight(w);
		setHeight(h);
		setIsDefined(true);
	}

	private String name, surname, dni;
	private int age, sex; // sexo -> 1: mujer, 2: hombre
	private double weight, height;
	private boolean isdefined; // "tiene datos dados de alta"
	private static final int adult = 18;

	public String getName() { return this.name; }
	public String getSurname() { return this.surname; }
	public String getDNI() { return this.dni; }
	public int getSex() { return this.sex; }
	public int getAge() { return this.age; }
	public double getWeight() { return this.weight; }
	public double getHeight() { return this.height; }
	public boolean getIsDefined() { return this.isdefined; }

	public void setName(String n) { this.name = n; }
	public void setSurname(String s) { this.surname = s; }
	public void setDNI(String d) { this.dni = d; }
	public void setSex(int x) { this.sex = x; }
	public void setAge(int a) { this.age = a; }
	public void setWeight(double w) { this.weight = w; }
	public void setHeight(double h) { this.height = h; }
	public void setIsDefined(boolean d) { this.isdefined = d; }

	private double calculateIMC() {
		return getWeight()/Math.pow(getHeight(), 2);
	}

	private int getIdealWeight() {
		double math = calculateIMC();
		if (math < 20) return -1;
		else if (math >= 20 && math <= 25) return 0;
		else return 1;
	}

	private void menuGetIdealWeight() {
		System.out.println("================");
		if (this.height != 0 && this.weight != 0) {
			switch (getIdealWeight()) {
				case -1: System.out.println("Esta por debajo de su peso ideal (infrapeso)");
				break;
				case 0: System.out.println("Esta en su peso ideal");
				break;
				case 1: System.out.println("Su peso esta por encima del ideal (sobrepeso)");
			}
		} else System.out.println("No hay datos de peso y/o altura, luego no se puede calcular su peso ideal");
	}

	private String menuSex() {
		switch (this.sex) {
			case 1: return "Mujer";
			case 2: return "Hombre";
			default: return "desconocido";
		}
	}

	private boolean isAdult() {
		if (getAge() >= adult) return true;
		else return false;
	}

	private void printFull() {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			if (isdefined) {
				System.out.println("+ Nombre: " + this.getName() + ", Apellidos: " + this.getSurname());
				System.out.println("+ DNI: " + this.getDNI() + ", Sexo: " + this.menuSex());
				System.out.println("+ Peso: " + this.getWeight() + ", Altura: " + this.getHeight() + ", IMC: " + this.calculateIMC());
			} else System.out.println("La persona no tiene ningun dato asignado");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

	public void printPerson(int n) {
		System.out.println((n != 0 ? "ID " + n + ": " : "") + "Nombre: " + this.getName() + (this.surname != null ? " " + this.getSurname() + " | DNI: " + this.getDNI() : ""));
	}

	public void personOperations() {
		int option = 0;
		do {
			Menus.menuOperations();
			option = InputManager.readNumber("Elige una opcion:");
			switch (option) {
				case 1: this.menuGetIdealWeight();
				break;
				case 2: System.out.println("================\n" + (this.isAdult() ? "Si" : "No") + " es mayor de edad");
				break;
				case 3: System.out.println("================\nSu sexo es " + this.menuSex());
				break;
				case 4: this.printFull();
				break;
				case 5: this.modifyData();
				break;
			}
		} while (option != 0);
	}

	public static boolean checkSex(String val) {
		switch (val.toLowerCase()) {
			case "masculino": 
			case "hombre":
			case "mujer":
			case "femenino":
				return true;
			default:
				return false;
		}
	}

    public static int chooseSex() {
        String sex;
		int result = 0;
		do {
			sex = InputManager.readString("Elige el sexo de la persona:");
			switch (sex.toLowerCase()) {
				case "masculino": 
				case "hombre":
					result = 2;
				break;
				case "mujer":
				case "femenino":
					result = 1;
				break;
				default:
					System.out.println("No has elegido un sexo correcto");
			}
		} while (checkSex(sex) != true);
		return result;
    }

	public static double putHeight() {
		double height = InputManager.readNumberDec("Introduzca la altura:");
		do {
			if (height < 2.5) return height;
			else if (height > 20 && height < 250) return height/100; // pasar a m
			else System.out.println("ERROR: La altura introducida no es valida");
		} while (true);
	}

	private void modifyData() {
		int option = 0;
		do {
			Menus.menuData();
			option = InputManager.readNumber("Elige una opcion:");
			switch (option) {
				case 1: this.name = InputManager.readString("Escribe el nombre de la persona"); 
						this.isdefined = true;
				break;
				case 2: this.surname = InputManager.readString("Escribe el apellido de la persona"); 
						this.isdefined = true;
				break;
				case 3: this.dni = InputManager.readString("Escribe el DNI de la persona"); 
						this.isdefined = true;
				break;
				case 4: this.sex = chooseSex(); 
						this.isdefined = true;
				break;
				case 5: this.age = InputManager.readNumber("Escribe la edad de la persona"); 
						this.isdefined = true;
				break;
				case 6: this.weight = InputManager.readNumberDec("Escribe el peso de la persona"); 
						this.isdefined = true;
				break;
				case 7: this.height = Person.putHeight(); 
						this.isdefined = true;
				break;
			}
		} while (option != 0);
	}
}