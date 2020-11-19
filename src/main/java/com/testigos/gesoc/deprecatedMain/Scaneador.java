// package MainPackage;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.Scanner;

// public class Scaneador {
// private static final Scanner scanner = new Scanner(System.in);

// public static int scanearInt() {
// do {
// if (scanner.hasNextInt())
// return scanner.nextInt();
// else {
// scanearString();
// System.out.println("No es un tipo de dato correcto, vuelva a ingresar el
// dato");
// }
// } while (true);
// }

// public static double scanearDouble() {
// do {
// if (scanner.hasNextInt())
// return scanner.nextDouble();
// else {
// scanearString();
// System.out.println("No es un tipo de dato correcto, vuelva a ingresar el
// dato");
// }
// } while (true);
// }

// public static boolean scanearBoolean() {
// do {
// if (scanner.hasNextBoolean())
// return scanner.nextBoolean();
// else {
// scanearString();
// System.out.println("No es un tipo de dato correcto, ingrese true o false");
// }
// } while (true);
// }

// public static String scanearString() {
// do {
// if (scanner.hasNext())
// return scanner.next();
// else {
// System.out.println("No escribió nada");
// }
// } while (true);
// }

// public static LocalDate scanearFecha() {
// do {
// try {
// String dia = Scaneador.scanearString();
// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
// return LocalDate.parse(dia, formatter);
// } catch (Exception e) {
// System.out.println("Trata de poner bien la fecha esta vez...");
// }
// } while (true);
// }
// }