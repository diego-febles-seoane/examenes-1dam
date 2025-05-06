package es.ies.puerto.modelo.db.entidades;
import java.util.Date;
import java.util.Objects;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class Autor {
    private String dni;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;
    
    /**
     * Constructor vacio
     */
    public Autor() {}

    /**
     * Cosntructor con el atributo principal de la clase
     * @param dni del autor
     */
    public Autor(String dni) {
        this.dni = dni;
    }

    /**
     * Constructor con todos los atributos
     * @param dni del autor
     * @param nombre del autor
     * @param nacionalidad del autor
     * @param fechaNacimiento del autor
     */
    public Autor(String dni, String nombre, String nacionalidad, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() 
    { return dni; }

    public void setDni(String dni) 
    { this.dni = dni; }

    public String getNombre() 
    { return nombre; }

    public void setNombre(String nombre) 
    { this.nombre = nombre; }

    public String getNacionalidad() 
    { return nacionalidad; }

    public void setNacionalidad(String nacionalidad) 
    { this.nacionalidad = nacionalidad; }

    public Date getFechaNacimiento() 
    { return fechaNacimiento; }

    public void setFechaNacimiento(Date fechaNacimiento) 
    { this.fechaNacimiento = fechaNacimiento; }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Autor)) {
            return false;
        }
        Autor autor = (Autor) o;
        return Objects.equals(dni, autor.dni) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
    

    @Override
    public String toString() {
        return "{" +
            " dni='" + getDni() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", nacionalidad='" + getNacionalidad() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            "}";
    }

}