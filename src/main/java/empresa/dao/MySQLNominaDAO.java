package empresa.dao;

import empresa.modelo.Nomina;
import empresa.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLNominaDAO implements NominaDAO {

    @Override
    public List<Nomina> listarTodas() throws SQLException {
        List<Nomina> nominas = new ArrayList<>();
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement("SELECT dni, sueldo FROM Nominas ORDER BY dni");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                nominas.add(mapear(rs));
            }
        }
        return nominas;
    }

    @Override
    public Optional<Nomina> buscarPorDni(String dni) throws SQLException {
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement("SELECT dni, sueldo FROM Nominas WHERE dni = ?")) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }
        }
    }

    @Override
    public void insertar(Nomina nomina) throws SQLException {
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Nominas (dni, sueldo) VALUES (?, ?)")) {
            establecerParametros(ps, nomina);
            ps.executeUpdate();
        }
    }

    @Override
    public boolean actualizar(Nomina nomina) throws SQLException {
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement("UPDATE Nominas SET sueldo = ? WHERE dni = ?")) {
            ps.setInt(1, nomina.getSueldo());
            ps.setString(2, nomina.getDni());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(String dni) throws SQLException {
        try (Connection con = ConexionBD.getInstancia().obtenerConexion();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Nominas WHERE dni = ?")) {
            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        }
    }

    private void establecerParametros(PreparedStatement ps, Nomina nomina) throws SQLException {
        ps.setString(1, nomina.getDni());
        ps.setInt(2, nomina.getSueldo());
    }

    private Nomina mapear(ResultSet rs) throws SQLException {
        return new Nomina(rs.getString("dni"), rs.getInt("sueldo"));
    }
}
