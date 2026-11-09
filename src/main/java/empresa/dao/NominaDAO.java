package empresa.dao;

import empresa.modelo.Nomina;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface NominaDAO {

    List<Nomina> listarTodas() throws SQLException;

    Optional<Nomina> buscarPorDni(String dni) throws SQLException;

    void insertar(Nomina nomina) throws SQLException;

    boolean actualizar(Nomina nomina) throws SQLException;

    boolean eliminar(String dni) throws SQLException;
}
