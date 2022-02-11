package voModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Table(name = "Zonas")
public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "zoCodZona", length = 5)
    private String codZona;

    @Column(name = "zoNombre", length = 30, nullable = false)
    private String nombre;

    /*Como una zona puede tener muchos inmuebles creamos un
    objeto de tipo List que contendrá todos los inmuebles de una zona*/

    @OneToMany(mappedBy = "zona", fetch = FetchType.EAGER)
    private List<Inmueble> inmuebles = new ArrayList<>();

    public Zona() {
        super();
    }

    public Zona(String codZona, String nombre) {
        super();
        this.codZona = codZona;
        this.nombre = nombre;
    }

    public String getCodZona() {
        return codZona;
    }

    public void setCodZona(String codZona) {
        this.codZona = codZona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codZona == null) ? 0 : codZona.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        Zona other = (Zona) obj;
        if (codZona == null) {
            if (other.codZona != null)
                return false;
        } else if (!codZona.equals(other.codZona))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Zona [codZona=" + codZona + ", nombre=" + nombre + "]";
    }
}

