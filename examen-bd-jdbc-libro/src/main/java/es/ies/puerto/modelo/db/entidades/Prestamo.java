package es.ies.puerto.modelo.db.entidades;

import java.util.Date;
import java.util.Objects;

/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class Prestamo {
    private String idPrestamo;
    private String libroId;
    private String usuarioId;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    /**
     * Cosntructor vacio
     */
    public Prestamo() {
    }


    /**
     * Constructor con el atributo principal 
     * @param idPrestamo del prestamo
     */
    public Prestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    /**
     * Constructor de los atributos de la clase
     * @param idPrestamo del prestamo
     * @param libroId del prestamo
     * @param usuarioId del prestamo
     * @param fechaPrestamo del prestamo
     * @param fechaDevolucion del prestamo
     */
    public Prestamo(String idPrestamo, String libroId, String usuarioId, Date fechaPrestamo, Date fechaDevolucion) {
        this.idPrestamo = idPrestamo;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getLibroId() {
        return libroId;
    }

    public void setLibroId(String libroId) {
        this.libroId = libroId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Prestamo)) {
            return false;
        }
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(idPrestamo, prestamo.idPrestamo) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrestamo);
    }

    @Override
    public String toString() {
        return "{" +
            " idPrestamo='" + getIdPrestamo() + "'" +
            ", libroId='" + getLibroId() + "'" +
            ", usuarioId='" + getUsuarioId() + "'" +
            ", fechaPrestamo='" + getFechaPrestamo() + "'" +
            ", fechaDevolucion='" + getFechaDevolucion() + "'" +
            "}";
    }
    
}
