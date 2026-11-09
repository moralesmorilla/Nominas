package empresa.comando;

import empresa.dao.EmpleadoDAO;
import empresa.dao.MySQLEmpleadoDAO;
import empresa.dao.MySQLNominaDAO;
import empresa.dao.NominaDAO;

import java.util.Map;

public final class CommandFactory {

    private final Map<String, Command> comandos;
    private final Command accionNoEncontrada = new AccionNoEncontradaCommand();

    private CommandFactory() {
        EmpleadoDAO empleadoDAO = new MySQLEmpleadoDAO();
        NominaDAO nominaDAO = new MySQLNominaDAO();
        comandos = Map.of(
                "inicio", new InicioCommand(),
                "listar", new ListarEmpleadosCommand(empleadoDAO),
                "buscar", new BuscarEmpleadosCommand(empleadoDAO),
                "salario", new ConsultarSalarioCommand(nominaDAO),
                "formularioBuscar", new MostrarFormularioCommand("/formularios/buscar.jsp"),
                "formularioSalario", new MostrarFormularioCommand("/formularios/salario.jsp"));
    }

    private static class Holder {
        private static final CommandFactory INSTANCIA = new CommandFactory();
    }

    public static CommandFactory getInstancia() {
        return Holder.INSTANCIA;
    }

    public Command obtener(String accion) {
        return comandos.getOrDefault(accion, accionNoEncontrada);
    }
}
