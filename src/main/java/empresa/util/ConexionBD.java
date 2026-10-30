package empresa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3307/nominas?serverTimezone=Europe/Madrid&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("No se encontró el driver de MySQL: " + e.getMessage());
        }
    }

    private ConexionBD() {
    }

    public static Connection obtener() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}