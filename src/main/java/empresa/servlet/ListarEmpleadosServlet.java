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

@WebServlet("/listar")
public class ListarEmpleadosServlet extends BaseServlet {

    private final EmpleadoDAO dao = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Empleado> empleados = dao.listarTodos();
            req.setAttribute("empleados", empleados);
            req.getRequestDispatcher("/WEB-INF/vistas/listado.jsp").forward(req, resp);
        } catch (SQLException e) {
            log("Error al listar empleados", e);
            mostrarMensaje(req, resp, "error", "No se pudo consultar la base de datos. Inténtalo más tarde.");
        }
    }
}