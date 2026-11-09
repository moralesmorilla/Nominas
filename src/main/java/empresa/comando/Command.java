package empresa.comando;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface Command {

    String ejecutar(HttpServletRequest request) throws SQLException;
}
