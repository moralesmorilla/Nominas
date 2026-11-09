package empresa.comando;

import javax.servlet.http.HttpServletRequest;

public class MostrarFormularioCommand implements Command {

    private final String vista;

    public MostrarFormularioCommand(String vista) {
        this.vista = vista;
    }

    @Override
    public String ejecutar(HttpServletRequest request) {
        return vista;
    }
}
