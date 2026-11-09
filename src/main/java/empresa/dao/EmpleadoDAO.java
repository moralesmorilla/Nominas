package empresa.dao;

import empresa.modelo.Empleado;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmpleadoDAO {

    List<Empleado> listarTodos() throws SQLException;

    List<Empleado> buscar(String criterio, String valor) throws SQLException;

    Optional<Empleado> buscarPorDni(String dni) throws SQLException;

    void insertar(Empleado empleado) throws SQLException;

    boolean actualizar(Empleado empleado) throws SQLException;

    boolean eliminar(String dni) throws SQLException;
}
