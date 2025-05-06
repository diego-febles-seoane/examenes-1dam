package es.ies.puerto.modelo.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import es.ies.puerto.modelo.db.entidades.Autor;
import es.ies.puerto.modelo.db.entidades.comun.Conexion;

/**
 * @author Diego-Febles-Seoane
 * @version 1.0.0
 */
public class AutorService extends Conexion{

    /**
     * Constructor vacio
     */
    public AutorService(){
        super();
    }

    /**
     * Funcion que crea un autor en la bbd
     * @param autor a crear
     * @return true/false
     */
    public boolean crearAutor(Autor autor) {
        if (autor == null) {
            return false;
        }
        String sql = "INSERT INTO autores (dni, nombre, nacionalidad, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        return ejecutarUpdate(sql, autor);     
            
    }

    /**
     * Funcion que obtiene todos los autores
     * @return lista de autores o vacia
     */
    public List<Autor> obtenerTodosAutores() {
        String sql = "SELECT * FROM autores";        
        return leerSql(sql);
    }

    /**
     * Funcion que buscan autores por el dni
     * @param dni a buscar
     * @return autor buscado/ null
     */
    public Autor obtenerAutorPorDni(String dni) {
        try {
            String sql = "SELECT * FROM autores " + "where dni='" + dni + "'";
            List<Autor> autores = leerSql(sql);
            if (autores.isEmpty()) {
                return null;
            }
            return autores.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion que actualiza los datos de un autor en la bbdd
     * @param autor a actualizar
     * @return true/false
     */
    public boolean actualizarAutor(Autor autor) {
        String sql = "update autores set nombre=?, nacionalidad=?, fecha_nacimiento=? where dni=?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(4, autor.getDni());
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getNacionalidad());
            if (autor.getFechaNacimiento()!= null) {
                stmt.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(autor.getFechaNacimiento()));
            } else{
                stmt.setString(3, null);
            }
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    /**
     * Funcion que elimina un autor de la bbdd por el dni
     * @param dni del autor a eliminar
     * @return true/false
     */
    public boolean eliminarAutor(String dni) {
        List<Autor>lista = obtenerTodosAutores();
        Autor autor = new Autor(dni);
        if (!lista.contains(autor)) {
            return false;
        }
        String sql = "delete FROM autores " + 
        "where dni='" + dni + "'";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
        return true;
    }

    /**
     * Ejecuta la sentencia sql de update
     * 
     * @param sql a ejecutar
     * @param autopr a agregar
     * @return true/false
     */
    public boolean ejecutarUpdate(String sql, Autor autor) {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, autor.getDni());
            stmt.setString(2, autor.getNombre());
            stmt.setString(3, autor.getNacionalidad());
            if (autor.getFechaNacimiento() == null) {
                stmt.setString(4, null);
            } else {
                stmt.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(autor.getFechaNacimiento()));
            }
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    /**
     * Ejecuta la sencuencia sql introducida
     *
     * @param sql a ejecutar
     * @return ArrayList<Autor>
     */
    public List<Autor> leerSql(String sql) {
        List<Autor> autores = new ArrayList<>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String dniStr = resultado.getString("dni");
                String nombreStr = resultado.getString("nombre");
                String nacionalidadStr = resultado.getString("nacionalidad");
                String dateStr = resultado.getString("fecha_nacimiento");
                Date fecha = null;
                if (dateStr != null) {
                    fecha = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                }
                Autor autor = new Autor(dniStr, nombreStr, nacionalidadStr, fecha);
                autores.add(autor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return autores;
    }
}
