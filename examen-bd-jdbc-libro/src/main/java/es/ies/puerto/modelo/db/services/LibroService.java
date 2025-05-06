package es.ies.puerto.modelo.db.services;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.ies.puerto.modelo.db.entidades.Libro;
import es.ies.puerto.modelo.db.entidades.comun.Conexion;

/**
 * @author diego-febles-seoane
 * @version 1.0.0
 */
public class LibroService extends Conexion{

    /**
     * Constructor vacio
     */
    public LibroService(){
        super();
    }

    /**
     * Funcion que crea un libro en la bbd
     * @param libro a crear
     * @return true/false
     */
    public boolean crearLibro(Libro libro) {
        if (libro == null) {
            return false;
        }
        String sql = "INSERT INTO libros (id_libro, titulo, autor_dni, fecha_publicacion, genero) VALUES (?, ?, ?, ?,?)";
        return ejecutarUpdate(sql, libro);  
    }

    /**
     * Funcion que obtiene todos los libros
     * @return lista de libros o vacia
     */
    public List<Libro> obtenerTodosLibros() {
        String sql = "SELECT * FROM libros";        
        return leerSql(sql);
    }

    /**
     * Funcion que buscan libros por el dni
     * @param dni a buscar
     * @return libro buscado/ null
     */
    public Libro obtenerLibroPorId(String idLibro) {
        try {
            String sql = "SELECT * FROM libros " + "where id_libro='" + idLibro + "'";
            List<Libro> libros = leerSql(sql);
            if (libros.isEmpty()) {
                return null;
            }
            return libros.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion que actualiza los datos de un libro en la bbdd
     * @param libro a actualizar
     * @return true/false
     */
    public boolean actualizarLibro(Libro libro) {
        String sql = "Update libros Set titulo=?, autor_dni=?, fecha_publicacion=?, genero=? where id_libro=?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(5, libro.getIdLibro());
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutorDni());
            if (libro.getFechaPublicacion() != null) {
                stmt.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(libro.getFechaPublicacion()));
            } else {
                stmt.setString(3, null);
            }
            stmt.setString(4, libro.getGenero());
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
     * Funcion que elimina un libro de la bbdd por el dni
     * @param idLibro del libro a eliminar
     * @return true/false
     */
    public boolean eliminarLibro(String idLibro) {
        List<Libro> libros = obtenerTodosLibros();
        Libro libro = new Libro(idLibro);
        if (!libros.contains(libro)) {
            return false;
        }
        String sql = "delete FROM libros " + 
        "where id_libro='" + idLibro + "'";
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
     * @param genero a agregar
     * @return true/false
     */
    public boolean ejecutarUpdate(String sql, Libro libro) {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, libro.getIdLibro());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutorDni());
            if (libro.getFechaPublicacion() == null) {
                stmt.setString(4, null);
            }else{
                stmt.setString(4,new SimpleDateFormat("yyyy-MM-dd").format(libro.getFechaPublicacion()));
            }
            stmt.setString(5, libro.getGenero());
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
     * @return ArrayList<Libro>
     */
    public List<Libro> leerSql(String sql) {
        List<Libro> libros = new ArrayList<>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String idLibro = resultado.getString("id_libro");
                String titulo = resultado.getString("titulo");
                String autorDni = resultado.getString("autor_dni");
                String dateStr = resultado.getString("fecha_publicacion");
                Date fecha = null;
                if (dateStr != null) {
                    fecha = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                }
                String genero = resultado.getString("genero");
                Libro libro = new Libro(idLibro, titulo, autorDni, fecha,genero);
                libros.add(libro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return libros;
    }
}
