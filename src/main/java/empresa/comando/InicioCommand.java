package empresa.comando;

import javax.servlet.http.HttpServletRequest;

public class InicioCommand implements Command {

    @Override
    public String ejecutar(HttpServletRequest request) {
        return "/index.html";
    }
}
