package empresa.servlet;

import empresa.dao.EmpleadoDAO;
import empresa.modelo.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/buscar")
public class BuscarEmpleadosServlet extends BaseServlet {

    private final EmpleadoDAO dao = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String criterio = req.getParameter("criterio");
        String valor = req.getParameter("valor");

        if (criterio == null || valor == null || valor.isBlank()) {
            mostrarMensaje(req, resp, "error", "Debes elegir un campo e introducir un valor para buscar.");
            return;
        }

        valor = valor.trim();

        try {
            List<Empleado> resultados = dao.buscar(criterio, valor);
            req.setAttribute("empleados", resultados);
            req.setAttribute("criterio", criterio);
            req.setAttribute("valor", valor);
            req.getRequestDispatcher("/WEB-INF/vistas/resultados-busqueda.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            mostrarMensaje(req, resp, "error", "El campo de búsqueda seleccionado no es válido.");
        } catch (SQLException e) {
            log("Error en la búsqueda de empleados", e);
            mostrarMensaje(req, resp, "error", "No se pudo realizar la búsqueda. Inténtalo más tarde.");
        }
    }
}