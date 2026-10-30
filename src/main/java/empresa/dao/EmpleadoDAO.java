package empresa.dao;

import empresa.modelo.Empleado;
import empresa.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    private static final String SELECT_EMPLEADOS =
            "SELECT nombre, dni, sexo, categoria, anyos FROM Empleados";

    public List<Empleado> listarTodos() throws SQLException {
        String sql = SELECT_EMPLEADOS + " ORDER BY nombre";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapear(rs);
        }
    }

    public List<Empleado> buscar(String criterio, String valor) throws SQLException {
        String columna = switch (criterio) {
            case "dni", "nombre", "sexo", "categoria", "anyos" -> criterio;
            default -> throw new IllegalArgumentException("Criterio no válido: " + criterio);
        };

        boolean textual = "nombre".equals(columna);
        String sql = SELECT_EMPLEADOS + " WHERE " + columna + (textual ? " LIKE ?" : " = ?")
                + " ORDER BY nombre";
        String parametro = textual ? "%" + valor + "%" : valor;

        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, parametro);
            try (ResultSet rs = ps.executeQuery()) {
                return mapear(rs);
            }
        }
    }

    public Integer obtenerSueldo(String dni) throws SQLException {
        String sql = "SELECT sueldo FROM Nominas WHERE dni = ?";
        try (Connection con = ConexionBD.obtener();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("sueldo") : null;
            }
        }
    }

    private List<Empleado> mapear(ResultSet rs) throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Empleado(
                    rs.getString("nombre"),
                    rs.getString("dni"),
                    rs.getString("sexo"),
                    rs.getInt("categoria"),
                    rs.getInt("anyos")));
        }
        return lista;
    }
}