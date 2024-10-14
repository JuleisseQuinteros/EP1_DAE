import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    // Registrar un nuevo alumno
    public void registrarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno (codigo, nombres, apellidos, correo, escuela, facultad, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionBd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (esCodigoUnico(alumno.getCodigo()) && esCorreoUnico(alumno.getCorreo())) {
                pstmt.setString(1, alumno.getCodigo());
                pstmt.setString(2, alumno.getNombres());
                pstmt.setString(3, alumno.getApellidos());
                pstmt.setString(4, alumno.getCorreo());
                pstmt.setString(5, alumno.getEscuela());
                pstmt.setString(6, alumno.getFacultad());
                pstmt.setString(7, String.valueOf(alumno.getFechaNacimiento()));
                pstmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener todos los alumnos
    public List<Alumno> obtenerAlumnos() {
        List<Alumno> listaAlumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno";

        try (Connection conn = ConnectionBd.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setCodigo(rs.getString("codigo"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setCorreo(rs.getString("correo"));
                alumno.setEscuela(rs.getString("escuela"));
                alumno.setFacultad(rs.getString("facultad"));
                String fechaNacimiento = rs.getString("fecha_nacimiento");
                if (fechaNacimiento != null) {
                    alumno.setFechaNacimiento(fechaNacimiento);
                } else {
                    alumno.setFechaNacimiento("---");
                }
                listaAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAlumnos;
    }

    /**
     *
     *  VALIDACIONES
     *
     * **/

    public boolean esCodigoUnico(String codigo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM alumno WHERE codigo = ?";
        try (Connection conn = ConnectionBd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        }
        return false;
    }

    public boolean esCorreoUnico(String correo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM alumno WHERE correo = ?";
        try (Connection conn = ConnectionBd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        }
        return false;
    }
}
