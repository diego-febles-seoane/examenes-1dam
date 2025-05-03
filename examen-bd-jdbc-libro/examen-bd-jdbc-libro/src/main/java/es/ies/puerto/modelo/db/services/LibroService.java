package es.ies.puerto.modelo.db.services;

import es.ies.puerto.modelo.db.entidades.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibroService {

    public boolean crearLibro(Libro libro) {
        return false;
    }

    public List<Libro> obtenerTodosLibros() {
        List<Libro> libros = new ArrayList<>();
        
        return libros;
    }

    public Libro obtenerLibroPorId(String idLibro) {
        
        return null;
    }

    public boolean actualizarLibro(Libro libro) {
        return false;
    }

    public boolean eliminarLibro(String idLibro) {
        return false;
    }
}
