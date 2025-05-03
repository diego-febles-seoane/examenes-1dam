package es.ies.puerto.modelo.db.entidades;


import java.util.Date;
import java.util.Objects;

public class Autor {
    private String dni;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;
    

    public Autor() {}

    public Autor(String dni, String nombre, String nacionalidad, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
    }
 

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Autor)) {
            return false;
        }
        Autor autor = (Autor) o;
        return Objects.equals(dni, autor.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, nacionalidad, fechaNacimiento);
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