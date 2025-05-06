package es.ies.puerto.modelo.db.entidades.comun;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.ies.puerto.config.ConfigManager;

/**
 * @author diego-febles-seoane
 * @version 1.0.0
 */
public abstract class Conexion {
    private Connection connection;
    
    String pathDB;
    /**
     * Constructor con el path de la bbdd
     */
    public Conexion(){
        ConfigManager.ConfigProperties.setPath("src/main/resources/app.properties");
        pathDB = ConfigManager.ConfigProperties.getProperties("ruta");
        try {
            File file = new File(pathDB);
            if (!file.exists()) {
                throw new SQLException("No se encuentra la bbdd");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion que abre la conexion a la bbdd
     * @return Connection
     */
    public Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:" + pathDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return this.connection;
    }

    /**
     * Funcion que cierra la conexion de bbdd
     */
    public void cerrar() {
        try {
            if (connection != null || !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
