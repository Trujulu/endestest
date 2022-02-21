public class Gestion {

	static Person person1;
	static Person person2;
	static Person person3;
	static final int numPersons = 3;

	public static void main(String[] args) {
		mainProgram();
	}

	public static void mainProgram() {
	int option = 0;
		do {
			Menus.menuMain();
			option = InputManager.readNumber("Elige una opcion:");
			switch (option) {
				case 1: printAllPersons(1);
				break;
				case 2: popupPerson("modificar");
				break;
				case 3: popupAddPerson();
				break;
				case 4: popupPerson("borrar");
				break;
			}
		} while (option != 0);
	}

	/* METODO GETPERSON, DEVUELVE CADA OBJETO SEGUN LA POSICION DADA */
	public static Person getPerson(int index) {
		if (index<1 || index>numPersons) {
			System.out.println("ERROR: Indice no valido " + index);
		}
		switch(index) {
			case 1: 
				if (person1 != null) return person1;
				break;
			case 2: 
				if (person2 != null) return person2;
				break;
			case 3: 
				if (person3 != null) return person3;
				break;
		}

		return null;
	}

	/* METODO SETPERSONA, ESTABLECE CADA OBJETO SEGUN LA POSICION DADA */
	public static void setPerson(int index, Person person) {
		if (index<1 || index>numPersons) {
			System.out.println("ERROR: Indice no valido " + index);
		}
		switch(index) {
			case 1: 
				person1 = person;
				break;
			case 2: 
				person2 = person;
				break;
			case 3: 
				person3 = person;
				break;
		}
	}

	/* DESTRUIR PERSONA */
	public static void deletePerson(int index) {
		if (index<1 || index>numPersons) {
			System.out.println("ERROR: Indice no valido " + index);
		}
		switch (index) {
			case 1: 
				person1 = null;
				System.out.println("Persona eliminada correctamente.");
			break;
			case 2: 
				person2 = null;
				System.out.println("Persona eliminada correctamente.");
			break;
			case 3: 
				person3 = null;
				System.out.println("Persona eliminada correctamente.");
			break;
		}
	}

	/* MUESTRA LAS PERSONAS Y DEVUELVE EL Nº PERSONAS DADAS DE ALTA */
	public static int printAllPersons(int tipo) {
		/* tipo 1 -> devolver texto */
		int number = 0;
		for (int i=1; i<=numPersons; i++) {
			if (getPerson(i) != null) {
				if (tipo == 1 && number == 0) System.out.println("Personas dadas de alta actualmente:");
				if (tipo == 1) getPerson(i).printPerson(i);
				number++;
			}
		}
		if (tipo == 1 && number == 0) System.out.println("ERROR: No existen personas dadas de alta actualmente");
		return number;
	}

	/* SELECCION DE PERSONA POR SU ID */
	public static int selectPerson(String msg) { // seleccionamos persona por su ID y devolvemos su posicion 1-3, -1 si no hay persona creadas
		int num, pos = -1;

		System.out.println("Seleccione la persona " + msg);
		num = printAllPersons(1);
		if (num == 0) return -1; /* si no hay personas, abortamos */

		do {
			pos = InputManager.readNumber("Introduce ID de persona, (0) para salir");
			if (pos != 0) {
				if (pos < 1 || pos > numPersons) System.out.println("ID de persona no valido.");
			}
		} while (pos < 1 || pos > numPersons && pos != 0);

		return pos;
	}

	/* MODIFICAR/BORRAR PERSONA */
	public static void popupPerson(String funcion) {
		int num = selectPerson("con la que operar");
		if (num != -1) {
			if (getPerson(num) != null) {
				if (funcion == "modificar") getPerson(num).personOperations();
				if (funcion == "borrar") deletePerson(num);
			}
			else System.out.println("La persona seleccionada no existe.");
		}
	}

	/* ENCONTRAR POSICION DE PERSONA LIBRE */
	public static int freePerson() {
		if (printAllPersons(0) >= numPersons) System.out.println("ERROR: Alcanzado limite de personas");
		for (int i=1; i<=numPersons; i++) {
			if (getPerson(i) == null) return i;
		}
		return -1;
	}

	/* ELEGIR TIPO DE PERSONA */
	public static int typePerson() { // añadir persona (tipo 1) y sin saldo (tipo 0)
		int option = 0;
		do {
			Menus.menuAdd();
			option = InputManager.readNumber("Elige una opcion:");
			switch (option) {
				case 1: 
					return 0;
				case 2: 
					return 1;
				case 3: 
					return 2;
			}
		} while (option != 0);
		return -1;
	}

	/* AÑADIR PERSONA SI 'HAY HUECOS LIBRES' */
	public static void popupAddPerson() {
		if (freePerson() != -1) {
			int type = typePerson();
			if (type != -1) { // tipo 0 -> ningun dato, tipo 1 -> nombre + edad + sexo, tipo 2 -> todos los datos
				if (type == 0) setPerson(freePerson(), new Person());
				if (type == 1) setPerson(freePerson(), new Person(InputManager.readString("Escribe el nombre de la persona"), InputManager.readNumber("Escribe la edad de la persona"), Person.chooseSex()));
				if (type == 2) setPerson(freePerson(), new Person(InputManager.readString("Escribe el nombre de la persona"), InputManager.readString("Escribe el apellido de la persona"), InputManager.readString("Escribe el DNI de la persona"), Person.chooseSex(), InputManager.readNumber("Escribe la edad de la persona"), InputManager.readNumberDec("Escribe el peso de la persona"), Person.putHeight()));
			}
		}
	}
}