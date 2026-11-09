package empresa.comando;

import empresa.dao.EmpleadoDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class BuscarEmpleadosCommand implements Command {

    private final EmpleadoDAO empleadoDAO;

    public BuscarEmpleadosCommand(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    @Override
    public String ejecutar(HttpServletRequest request) throws SQLException {
        String criterio = request.getParameter("criterio");
        String valor = request.getParameter("valor");
        if (criterio == null || valor == null || valor.isBlank()) {
            request.setAttribute("tipo", "error");
            request.setAttribute("mensaje", "Debes elegir un campo e introducir un valor para buscar.");
            return "/WEB-INF/vistas/mensaje.jsp";
        }

        valor = valor.trim();
        try {
            request.setAttribute("empleados", empleadoDAO.buscar(criterio, valor));
            request.setAttribute("criterio", criterio);
            request.setAttribute("valor", valor);
            return "/WEB-INF/vistas/resultados-busqueda.jsp";
        } catch (IllegalArgumentException e) {
            request.setAttribute("tipo", "error");
            request.setAttribute("mensaje", "El campo de búsqueda seleccionado no es válido.");
            return "/WEB-INF/vistas/mensaje.jsp";
        }
    }
}
