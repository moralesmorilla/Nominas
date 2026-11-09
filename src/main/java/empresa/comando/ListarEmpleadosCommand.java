package empresa.comando;

import empresa.dao.EmpleadoDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ListarEmpleadosCommand implements Command {

    private final EmpleadoDAO empleadoDAO;

    public ListarEmpleadosCommand(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    @Override
    public String ejecutar(HttpServletRequest request) throws SQLException {
        request.setAttribute("empleados", empleadoDAO.listarTodos());
        return "/WEB-INF/vistas/listado.jsp";
    }
}
