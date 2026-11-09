package empresa.comando;

import empresa.dao.NominaDAO;
import empresa.modelo.Nomina;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class ConsultarSalarioCommand implements Command {

    private static final String PATRON_DNI = "\\d{8}[A-Za-z]";
    private final NominaDAO nominaDAO;

    public ConsultarSalarioCommand(NominaDAO nominaDAO) {
        this.nominaDAO = nominaDAO;
    }

    @Override
    public String ejecutar(HttpServletRequest request) throws SQLException {
        String dni = request.getParameter("dni");
        if (dni == null || !dni.trim().matches(PATRON_DNI)) {
            request.setAttribute("tipo", "error");
            request.setAttribute("mensaje",
                    "El DNI introducido no es válido. Debe tener 8 dígitos y una letra.");
            return "/WEB-INF/vistas/mensaje.jsp";
        }

        dni = dni.trim().toUpperCase();
        Optional<Nomina> nomina = nominaDAO.buscarPorDni(dni);
        if (nomina.isEmpty()) {
            request.setAttribute("tipo", "info");
            request.setAttribute("mensaje", "No existe ninguna nómina con DNI " + dni + ".");
            return "/WEB-INF/vistas/mensaje.jsp";
        }
        request.setAttribute("dni", dni);
        request.setAttribute("sueldo", nomina.get().getSueldo());
        return "/WEB-INF/vistas/resultado-salario.jsp";
    }
}
