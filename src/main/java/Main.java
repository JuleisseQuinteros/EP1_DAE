import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Registrar un alumno");
            System.out.println("2. Ver alumnos registrados");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    // Registrar un nuevo alumno
                    System.out.print("Ingrese el código: ");
                    String codigo = scanner.nextLine();


                    // Validar si el código es único
                    try {
                        if (!alumnoDAO.esCodigoUnico(codigo)) {
                            System.out.println();
                            System.out.println("El código ingresado ya está en uso. Intente con otro código.");
                            System.out.println();
                            break;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.print("Ingrese los nombres: ");
                    String nombres = scanner.nextLine();

                    System.out.print("Ingrese los apellidos: ");
                    String apellidos = scanner.nextLine();

                    System.out.print("Ingrese el correo: ");
                    String correo = scanner.nextLine();

                    // Validar si el correo es único
                    try {
                        if (!alumnoDAO.esCorreoUnico(correo)) {
                            System.out.println();
                            System.out.println("El correo ingresado ya está en uso. Intente con otro correo.");
                            System.out.println();
                            break;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.print("Ingrese la escuela: ");
                    String escuela = scanner.nextLine();

                    System.out.print("Ingrese la facultad: ");
                    String facultad = scanner.nextLine();

                    Alumno alumno = new Alumno(codigo, nombres, apellidos, correo, escuela, facultad);
                    alumnoDAO.registrarAlumno(alumno);
                    System.out.println("Alumno registrado exitosamente.");
                    break;

                case 2:
                    // Ver todos los alumnos
                    List<Alumno> alumnos = alumnoDAO.obtenerAlumnos();

                    if (alumnos.isEmpty()) {
                        System.out.println();
                        System.out.println("No hay alumnos registrados aún.");
                        System.out.println();
                    } else {
                        System.out.println("Lista de alumnos registrados:");
                        for (Alumno a : alumnos) {
                            System.out.println(a.getCodigo() + " -  " + a.getNombres() + " " + a.getApellidos());
                        }
                        System.out.println();
                    }

                    break;

                case 3:
                    // Salir
                    System.exit(0);

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
