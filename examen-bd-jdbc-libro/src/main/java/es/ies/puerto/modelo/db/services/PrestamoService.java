package es.ies.puerto.modelo.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.ies.puerto.modelo.db.entidades.Prestamo;
import es.ies.puerto.modelo.db.entidades.comun.Conexion;

/**
 * @author diego-febles-seoane
 * @version 1.0.0
 */
public class PrestamoService extends Conexion {

    /**
     * Consturctor vacio
     */
    public PrestamoService() {
        super();
    }

    /**
     * Metodo para crear un prestamo en la bbdd
     * 
     * @param prestamo a agregar
     * @return true/false
     */
    public boolean crearPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            return false;
        }
        String sql = "INSERT INTO prestamos (id_prestamo, libro_id, usuario_id, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?,?)";
        return ejecutarUpdate(sql, prestamo);
    }

    /**
     * Funcion para obtener todos los prestamos de la bbdd
     * 
     * @return lista de prestamos
     */
    public List<Prestamo> obtenerTodosPrestamos() {
        String sql = "SELECT * FROM prestamos";
        return leerSql(sql);
    }

    /**
     * Funcion que buscan prestamo por el dni
     * 
     * @param dni a buscar
     * @return prestamo buscado/ null
     */
    public Prestamo obtenerPrestamoPorId(String id) {
        try {
            String sql = "SELECT * FROM prestamos " + "where id_prestamo='" + id + "'";
            List<Prestamo> prestamos = leerSql(sql);
            if (prestamos.isEmpty()) {
                return null;
            }
            return prestamos.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion que actualiza los prestamos de un libro en la bbdd
     * 
     * @param prestamo a actualizar
     * @return true/false
     */
    public boolean actualizarPrestamo(Prestamo prestamo) {
        String sql = "update prestamos set libro_id=?, usuario_id=?, fecha_prestamo=?,fecha_devolucion=? where id_prestamo=?";
                    
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(5, prestamo.getIdPrestamo());
            stmt.setString(1, prestamo.getLibroId());
            stmt.setString(2, prestamo.getUsuarioId());
            if (prestamo.getFechaPrestamo() != null) {
                stmt.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(prestamo.getFechaPrestamo()));
            } else {
                stmt.setString(3, null);
            }
            if (prestamo.getFechaDevolucion() != null) {
                stmt.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(prestamo.getFechaDevolucion()));
            } else {
                stmt.setString(4, null);
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
     * Funcion que elimina un prestamo de la bbdd por el id
     * 
     * @param id del libro a eliminar
     * @return true/false
     */
    public boolean eliminarPrestamo(String id) {
        List<Prestamo> prestamos = obtenerTodosPrestamos();
        Prestamo prestamo = new Prestamo(id);
        if (!prestamos.contains(prestamo)) {
            return false;
        }
        String sql = "delete FROM prestamos " +
                "where id_prestamo='" + id + "'";
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
     * Metodo que obtiene una lista de prestamos vencidos
     * @param fechaLimite de los prestamios
     * @return lista de prestamos
     */
    public List<Prestamo> obtenerPrestamosVencidos(Date fechaLimite) {
        List<Prestamo> resultado = new ArrayList<>();
        List<Prestamo> prestamos = obtenerTodosPrestamos();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getFechaDevolucion() != null && prestamo.getFechaDevolucion().before(fechaLimite)) {
                resultado.add(prestamo);    
            }
        }
        return resultado;
    }

    /**
     * Funcion que obtiene todos los prestamos por el usuario buscado
     * 
     * @param idUsuario a buscar
     * @returnlista de prestamos
     */
    public List<Prestamo> obtenerPrestamosPorUsuario(String idUsuario) {
        String stm = "Select * from prestamos where usuario_id = '"+idUsuario+ "'";
        return leerSql(stm);
    }

    /**
     * Funcion que devuelve una lista de los prestamos que estan activos
     * @return lista de prestamos activos
     */
    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> prestamos = obtenerTodosPrestamos();
        List<Prestamo> prestamosVencidos = obtenerPrestamosVencidos(new Date());
        prestamos.removeAll(prestamosVencidos);
        return prestamos;
    }

    /**
     * Ejecuta la sentencia sql de update
     * 
     * @param sql      a ejecutar
     * @param prestamo a agregar
     * @return true/false
     */
    public boolean ejecutarUpdate(String sql, Prestamo prestamo) {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, prestamo.getIdPrestamo());
            stmt.setString(2, prestamo.getLibroId());
            stmt.setString(3, prestamo.getUsuarioId());
            if (prestamo.getFechaPrestamo() != null) {
                stmt.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(prestamo.getFechaPrestamo()));
            } else {
                stmt.setString(4, null);
            }
            if (prestamo.getFechaDevolucion() != null) {
                stmt.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(prestamo.getFechaDevolucion()));
            } else {
                stmt.setString(5, null);
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
     * @return ArrayList<Prestamo>
     */
    public List<Prestamo> leerSql(String sql) {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String idPrestamo = resultado.getString("id_prestamo");
                String libroId = resultado.getString("libro_id");
                String usuarioId = resultado.getString("usuario_id");
                String fechaPrestamo = resultado.getString("fecha_prestamo");
                Date prestamoFecha = null;
                if (fechaPrestamo != null) {
                    prestamoFecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaPrestamo);
                }
                String fechaDevolucion = resultado.getString("fecha_devolucion");
                Date devolcionFecha = null;
                if (fechaDevolucion != null) {
                    devolcionFecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaDevolucion);
                }
                Prestamo prestamo = new Prestamo(idPrestamo, libroId, usuarioId, prestamoFecha, devolcionFecha);
                prestamos.add(prestamo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return prestamos;
    }

}
