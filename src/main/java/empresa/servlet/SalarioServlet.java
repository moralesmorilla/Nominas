package empresa.servlet;

import empresa.dao.EmpleadoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/salario")
public class SalarioServlet extends BaseServlet {

    private static final String PATRON_DNI = "\\d{8}[A-Za-z]";

    private final EmpleadoDAO dao = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String dni = req.getParameter("dni");

        if (dni == null || !dni.trim().matches(PATRON_DNI)) {
            mostrarMensaje(req, resp, "error",
                    "El DNI introducido no es válido. Debe tener 8 dígitos y una letra.");
            return;
        }

        dni = dni.trim().toUpperCase();

        try {
            Integer sueldo = dao.obtenerSueldo(dni);
            if (sueldo == null) {
                mostrarMensaje(req, resp, "info",
                        "No existe ningún empleado con DNI " + dni + " en la base de datos.");
                return;
            }
            req.setAttribute("dni", dni);
            req.setAttribute("sueldo", sueldo);
            req.getRequestDispatcher("/WEB-INF/vistas/resultado-salario.jsp").forward(req, resp);
        } catch (SQLException e) {
            log("Error al consultar el salario", e);
            mostrarMensaje(req, resp, "error", "No se pudo consultar el salario. Inténtalo más tarde.");
        }
    }
}