package es.ies.puerto.modelo.db.entidades;

import java.util.Date;
import java.util.Objects;

/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class Usuario {
    private String idUsuario;
    private String nombre;
    private String email;
    private String telefono;
    private Date fechaRegistro;

    /**
     * Constructor vacio
     */
    public Usuario() {}

    /**
     * Constructor con el atributo principal
     * @param idUsuario del usuario
     */
    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Constructor con todos los atributos
     * @param idUsuario del usuario
     * @param nombre del usuario
     * @param email del usuario
     * @param telefono del usuario
     * @param fechaRegistro del usuario
     */
    public Usuario(String idUsuario, String nombre, String email, String telefono, Date fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    @Override
    public String toString() {
        return "{" +
            " idUsuario='" + getIdUsuario() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            "}";
    }

}
