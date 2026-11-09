package empresa.servlet;

import empresa.comando.Command;
import empresa.comando.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controlador")
public class FrontControllerServlet extends HttpServlet {

    private final CommandFactory commandFactory = CommandFactory.getInstancia();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesar(request, response);
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null || accion.isBlank()) {
            accion = "inicio";
        }

        Command command = commandFactory.obtener(accion);
        try {
            String vista = command.ejecutar(request);
            Object codigoEstado = request.getAttribute("codigoEstado");
            if (codigoEstado instanceof Integer) {
                response.setStatus((Integer) codigoEstado);
            }
            request.getRequestDispatcher(vista).forward(request, response);
        } catch (SQLException e) {
            log("Error al procesar la acción '" + accion + "'", e);
            request.setAttribute("tipo", "error");
            request.setAttribute("mensaje", "No se pudo completar la operación. Inténtalo más tarde.");
            request.getRequestDispatcher("/WEB-INF/vistas/mensaje.jsp").forward(request, response);
        }
    }
}
