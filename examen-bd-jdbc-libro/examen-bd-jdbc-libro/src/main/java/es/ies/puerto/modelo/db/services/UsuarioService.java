package es.ies.puerto.modelo.db.services;

import es.ies.puerto.modelo.db.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    public boolean crearUsuario(Usuario usuario) {
        return false;
    }

    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        
        return usuarios;
    }

    public Usuario obtenerUsuarioPorId(String idUsuario) {
        Usuario usuario = null;
        
        return usuario;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return false;
    }

    public boolean eliminarUsuario(String idUsuario) {
        return false;
    }

}
