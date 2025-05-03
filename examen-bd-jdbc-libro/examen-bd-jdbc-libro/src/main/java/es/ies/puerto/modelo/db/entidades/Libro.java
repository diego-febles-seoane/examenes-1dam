package es.ies.puerto.modelo.db.entidades;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Date;

public class Libro {
    private String idLibro;
    private String titulo;
    private String autorDni;
    private Date fechaPublicacion;
    private String genero;

    public Libro() {
    }

    public Libro(String idLibro, String titulo, String autorDni, Date fechaPublicacion, String genero) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autorDni = autorDni;
        this.fechaPublicacion = fechaPublicacion;
        this.genero = genero;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutorDni() {
        return autorDni;
    }

    public void setAutorDni(String autorDni) {
        this.autorDni = autorDni;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
