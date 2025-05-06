package es.ies.puerto.modelo.db.entidades;


import java.util.Date;
import java.util.Objects;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class Libro {
    private String idLibro;
    private String titulo;
    private String autorDni;
    private Date fechaPublicacion;
    private String genero;

    /**
     * Constructor vacio
     */
    public Libro() {

    }

    /**
     * Cosntructor con el atributo principal de la clase
     * @param idLibro del autor
     */
    public Libro(String idLibro) {
        this.idLibro = idLibro;
    }

    /**
     * Constructor con los atributos de la clase
     * @param idLibro del libro
     * @param titulo del libro
     * @param autorDni del libro
     * @param fechaPublicacion del libro
     * @param genero del libro
     */
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Libro)) {
            return false;
        }
        Libro libro = (Libro) o;
        return Objects.equals(idLibro, libro.idLibro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro);
    }

    @Override
    public String toString() {
        return "{" +
            " idLibro='" + getIdLibro() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", autorDni='" + getAutorDni() + "'" +
            ", fechaPublicacion='" + getFechaPublicacion() + "'" +
            ", genero='" + getGenero() + "'" +
            "}";
    }

}
