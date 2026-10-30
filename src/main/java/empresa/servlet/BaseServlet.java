package empresa.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected void mostrarMensaje(HttpServletRequest req, HttpServletResponse resp,
                                  String tipo, String mensaje)
            throws ServletException, IOException {
        req.setAttribute("tipo", tipo);
        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("/WEB-INF/vistas/mensaje.jsp").forward(req, resp);
    }
}