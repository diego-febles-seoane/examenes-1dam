package es.ies.puerto.modelo.db.services;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.ies.puerto.modelo.db.entidades.Prestamo;

public class PrestamoService {

    public boolean crearPrestamo(Prestamo prestamo) {
        return false;
    }

    public List<Prestamo> obtenerTodosPrestamos() {
        List<Prestamo> prestamos = new ArrayList<>();
        
        return prestamos;
    }

    public Prestamo obtenerPrestamoPorId(String id) {
        Prestamo prestamo = null;

       
        return prestamo;
    }

    public boolean actualizarPrestamo(Prestamo prestamo) {
        return false;
    }

    public boolean eliminarPrestamo(String id) {
        return false;
    }


public List<Prestamo> obtenerPrestamosVencidos(Date fechaLimite) {
    List<Prestamo> resultado = new ArrayList<>();
    
    return resultado;
}


public List<Prestamo> obtenerPrestamosPorUsuario(String idUsuario) {
    List<Prestamo> resultado = new ArrayList<>();
    

    return resultado;
}



public List<Prestamo> obtenerPrestamosActivos() {
    List<Prestamo> resultado = new ArrayList<>();
    

    return resultado;
}



}

