package empresa.dao;

import empresa.modelo.Empleado;
import empresa.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLEmpleadoDAO implements EmpleadoDAO {

    private static final String SELECT_EMPLEADOS =
            "SELECT nombre, dni, sexo, categoria, anyos FROM Empleados";

    @Override
    public List<Empleado> listarTodos() throws SQLException {
        String sql = SELECT_EMPLEADOS + " ORDER BY nombre";
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapear(rs);
        }
    }

    @Override
    public List<Empleado> buscar(String criterio, String valor) throws SQLException {
        String columna = switch (criterio) {
            case "dni", "nombre", "sexo", "categoria", "anyos" -> criterio;
            default -> throw new IllegalArgumentException("Criterio no válido: " + criterio);
        };

        boolean textual = "nombre".equals(columna);
        String sql = SELECT_EMPLEADOS + " WHERE " + columna + (textual ? " LIKE ?" : " = ?")
                + " ORDER BY nombre";
        String parametro = textual ? "%" + valor + "%" : valor;

        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, parametro);
            try (ResultSet rs = ps.executeQuery()) {
                return mapear(rs);
            }
        }
    }

    @Override
    public Optional<Empleado> buscarPorDni(String dni) throws SQLException {
        String sql = SELECT_EMPLEADOS + " WHERE dni = ?";
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapearEmpleado(rs)) : Optional.empty();
            }
        }
    }

    @Override
    public void insertar(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO Empleados (nombre, dni, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            establecerParametros(ps, empleado);
            ps.executeUpdate();
        }
    }

    @Override
    public boolean actualizar(Empleado empleado) throws SQLException {
        String sql = "UPDATE Empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getSexo());
            ps.setInt(3, empleado.getCategoria());
            ps.setInt(4, empleado.getAnyos());
            ps.setString(5, empleado.getDni());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(String dni) throws SQLException {
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Empleados WHERE dni = ?")) {
            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        }
    }

    private void establecerParametros(PreparedStatement ps, Empleado empleado) throws SQLException {
        ps.setString(1, empleado.getNombre());
        ps.setString(2, empleado.getDni());
        ps.setString(3, empleado.getSexo());
        ps.setInt(4, empleado.getCategoria());
        ps.setInt(5, empleado.getAnyos());
    }

    private List<Empleado> mapear(ResultSet rs) throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(mapearEmpleado(rs));
        }
        return lista;
    }

    private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getString("nombre"),
                rs.getString("dni"),
                rs.getString("sexo"),
                rs.getInt("categoria"),
                rs.getInt("anyos"));
    }
}
