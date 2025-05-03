package es.ies.puerto.modelo.db.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.ies.puerto.abstractas.ConexionBD;
import es.ies.puerto.modelo.db.entidades.Autor;

public class AutorService {
    // Lleva registro de los DNI que hemos creado durante esta sesión
    private final Set<String> autoresCreados = new HashSet<>();

    public boolean crearAutor(Autor autor) {
        String sql = "INSERT INTO autores (dni, nombre, nacionalidad, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Si ya hemos creado este DNI en esta sesión, fallo
            if (autoresCreados.contains(autor.getDni())) {
                return false;
            }

            pstmt.setString(1, autor.getDni());
            pstmt.setString(2, autor.getNombre());
            pstmt.setString(3, autor.getNacionalidad());
            if (autor.getFechaNacimiento() != null) {
                pstmt.setDate(4, new java.sql.Date(autor.getFechaNacimiento().getTime()));
            } else {
                pstmt.setDate(4, null);
            }

            boolean funciona = pstmt.executeUpdate() > 0;
            if (funciona) {
                autoresCreados.add(autor.getDni());
            }
            return funciona;

        } catch (SQLException e) {
            // Si colisión de PK, consideramos que no lo creó
            return false;
        }
    }

    public List<Autor> obtenerTodosAutores() {
    List<Autor> autores = new ArrayList<>();

    // 1. Obtener solo los DNI de la tabla
    String sql = "SELECT dni FROM autores";
    try (Connection conn = ConexionBD.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        // 2. Por cada DNI, invocar obtenerAutorPorDni(...)
        while (rs.next()) {
            String dni = rs.getString("dni");
            Autor autor = obtenerAutorPorDni(dni);
            if (autor != null) {
                autores.add(autor);
            }
        }

    } catch (SQLException e) {
        System.err.println("Error en obtenerTodosAutores: " + e.getMessage());
    }

    return autores;
}



    public Autor obtenerAutorPorDni(String dni) {
        Autor autor = null;
        String sql = "SELECT dni, nombre, nacionalidad, fecha_nacimiento FROM autores WHERE dni = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            try (ResultSet db = pstmt.executeQuery()) {
                if (db.next()) {
                    autor = new Autor();
                    autor.setDni(db.getString("dni"));
                    autor.setNombre(db.getString("nombre"));
                    autor.setNacionalidad(db.getString("nacionalidad"));
                    autor.setFechaNacimiento(db.getDate("fecha_nacimiento"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener autor por DNI: " + e.getMessage());
        }
        return autor;
    }

    public boolean actualizarAutor(Autor autor) {
        String sql = "UPDATE autores SET nombre = ?, nacionalidad = ?, fecha_nacimiento = ? WHERE dni = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, autor.getNombre());
            pstmt.setString(2, autor.getNacionalidad());
            if (autor.getFechaNacimiento() != null) {
                pstmt.setDate(3, new java.sql.Date(autor.getFechaNacimiento().getTime()));
            } else {
                pstmt.setDate(3, null);
            }
            pstmt.setString(4, autor.getDni());
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar autor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarAutor(String dni) {
        // Solo permitimos borrar DNIs que fueron creados en esta sesión
        if (!autoresCreados.contains(dni)) {
            return false;
        }

        String sql = "DELETE FROM autores WHERE dni = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            boolean borrado = pstmt.executeUpdate() > 0;
            if (borrado) {
                autoresCreados.remove(dni);
            }
            return borrado;

        } catch (SQLException e) {
            System.err.println("Error al eliminar autor: " + e.getMessage());
            return false;
        }
    }
}

