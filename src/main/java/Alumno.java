import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Alumno {
    private int id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String correo;
    private String escuela;
    private String facultad;
    private LocalDate fechaNacimiento;

    // Constructor vacío
    public Alumno() {}

    // Constructor con todos los atributos
    public Alumno(String codigo, String nombres, String apellidos, String correo, String escuela, String facultad, String fechaNacimiento) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.escuela = escuela;
        this.facultad = facultad;
        this.fechaNacimiento = convertirFecha(fechaNacimiento);
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = convertirFecha(fechaNacimiento);

    }

    public static LocalDate convertirFecha(String fechaStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (fechaStr != null && !fechaStr.isEmpty()) {
            try {
                return LocalDate.parse(fechaStr, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Error al convertir la fecha: " + fechaStr + ". Formato inválido.");
                return null;
            }
        } else {
            return null;
        }
    }

    // Método para validar si es mayor de edad
    public boolean esMayorDeEdad() {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(this.fechaNacimiento, fechaActual);
        return periodo.getYears() >= 18;
    }
}
