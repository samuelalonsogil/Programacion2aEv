package voModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Table(name = "Propietarios")
public class Propietario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "prDNI", length = 9)
    private String dni;
    @Column(name = "prNombre", length = 15, nullable = false)
    private String nombre;
    @Column(name = "prApellidos", length = 40, nullable = false)
    private String apellidos;
    @Column(name = "prDireccion", length = 50, nullable = false)
    private String direccion;
    @Column(name = "prTelefono", length = 9, nullable = false)
    private String telefono;

    /*Como un propietario  puede tener muchos inmuebles creamos un objeto
    de tipo List que contendrá todos los inmuebles de un propietario tenemos que mapearlo*/
    @OneToMany(mappedBy = "propietario", fetch = FetchType.EAGER)
    List<Inmueble> inmuebles = new ArrayList<>();

    public Propietario() {
        super();
    }

    public Propietario(String dni, String nombre, String apellidos, String direccion, String telefono) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
        result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
        result = prime * result + ((dni == null) ? 0 : dni.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Propietario other = (Propietario) obj;
        if (apellidos == null) {
            if (other.apellidos != null)
                return false;
        } else if (!apellidos.equals(other.apellidos))
            return false;
        if (direccion == null) {
            if (other.direccion != null)
                return false;
        } else if (!direccion.equals(other.direccion))
            return false;
        if (dni == null) {
            if (other.dni != null)
                return false;
        } else if (!dni.equals(other.dni))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (telefono == null) {
            if (other.telefono != null)
                return false;
        } else if (!telefono.equals(other.telefono))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Propietario [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion="
                + direccion + ", telefono=" + telefono + "]";
    }
}
