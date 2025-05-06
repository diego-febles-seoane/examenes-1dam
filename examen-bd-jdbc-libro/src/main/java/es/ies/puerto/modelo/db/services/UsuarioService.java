package es.ies.puerto.modelo.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.ies.puerto.modelo.db.entidades.Usuario;
import es.ies.puerto.modelo.db.entidades.comun.Conexion;
/**
 * @author diego-febles-seoane
 * @version 1.0.0
 */
public class UsuarioService extends Conexion{

    /**
     * Constructor vacio
     */
    public UsuarioService (){
        super();
    }

    /**
     * Funcion que crea un usuario en la bbd
     * @param usuario a crear
     * @return true/false
     */
    public boolean crearUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        String sql = "INSERT INTO usuarios (id_usuario, nombre, email, telefono, fecha_registro) VALUES (?, ?, ?, ?,?)";
        return ejecutarUpdate(sql, usuario);  
    }

    /**
     * Funcion que obtiene todos los usuarios
     * @return lista de usuarios o vacia
     */
    public List<Usuario> obtenerTodosUsuarios() {
        String sql = "SELECT * FROM usuarios";        
        return leerSql(sql);
    }

    /**
     * Funcion que buscan usuarios por el id
     * @param idUsuario a buscar
     * @return usuario buscado/ null
     */
    public Usuario obtenerUsuarioPorId(String idUsuario) {
        try {
            String sql = "SELECT * FROM usuarios " + "where id_usuario='" + idUsuario + "'";
            List<Usuario> usuarios = leerSql(sql);
            if (usuarios.isEmpty()) {
                return null;
            }
            return usuarios.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion que actualiza los datos de un usuario en la bbdd
     * @param usuario a actualizar
     * @return true/false
     */
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "Update usuarios Set nombre=?, email=?, telefono=?, fecha_registro=? where id_usuario=?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(5, usuario.getIdUsuario());
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefono());
            if (usuario.getFechaRegistro() != null) {
                stmt.setString(4,new SimpleDateFormat("yyyy-MM-dd").format(usuario.getFechaRegistro()));
            }else{
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
     * Funcion que elimina un usuario de la bbdd por el id
     * @param idUsuario del usuario a eliminar
     * @return true/false
     */
    public boolean eliminarUsuario(String idUsuario) {
        List<Usuario> usuarios = obtenerTodosUsuarios();
        Usuario usuario = new Usuario(idUsuario);
        if (!usuarios.contains(usuario)) {
            return false;
        }
        String sql = "delete FROM usuarios " + 
        "where id_usuario='" + idUsuario + "'";
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
     * @param usuario a agregar
     * @return true/false
     */
    public boolean ejecutarUpdate(String sql, Usuario usuario) {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefono());
            if (usuario.getFechaRegistro() != null) {
                stmt.setString(5,new SimpleDateFormat("yyyy-MM-dd").format(usuario.getFechaRegistro()));
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
     * Ejecuta la sencuencia sql introducida
     *
     * @param sql a ejecutar
     * @return ArrayList<Usuario>
     */
    public List<Usuario> leerSql(String sql) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String idUsuario = resultado.getString("id_usuario");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                String telefono = resultado.getString("telefono");
                String fechaRegistro = resultado.getString("fecha_registro");
                Date fecha = null;
                if (fechaRegistro != null) {
                    fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaRegistro);
                }
                Usuario usuario = new Usuario(idUsuario, nombre, email, telefono,fecha);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return usuarios;
    }

}
