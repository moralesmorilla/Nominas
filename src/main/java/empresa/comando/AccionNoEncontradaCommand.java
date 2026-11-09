package empresa.comando;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccionNoEncontradaCommand implements Command {

    @Override
    public String ejecutar(HttpServletRequest request) {
        request.setAttribute("tipo", "error");
        request.setAttribute("mensaje", "La acción solicitada no existe.");
        request.setAttribute("codigoEstado", HttpServletResponse.SC_NOT_FOUND);
        return "/WEB-INF/vistas/mensaje.jsp";
    }
}
