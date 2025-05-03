package es.ies.puerto.abstractas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:sqlite:src/main/resources/biblioteca.db";
    // Para MySQL sería algo como:
    // private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    // private static final String USER = "tu_usuario";
    // private static final String PASSWORD = "tu_contraseña";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
        // Para MySQL sería:
        // return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

