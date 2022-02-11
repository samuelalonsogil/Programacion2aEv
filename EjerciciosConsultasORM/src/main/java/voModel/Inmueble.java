package voModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Table(name = "Inmuebles")
public class Inmueble implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "inCodInmueble", length = 5)
    private String codInmueble;

    @Column(name = "inDireccion", length = 50, nullable = false)
    private String direccion;

    /*como un inmueble solo puede estar en una zona creamos un atributo de tipo Zona
    en este caso la anotacion ManyToOne*/
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "inCodZona")
    private Zona zona;

    /*0 desocupado 1 alquilado*/
    @Column(name = "inEstado", nullable = false)
    private boolean estado;

    /*como un inmueble solo puede tener un propietario creamos un atributo de tipo Propietario*/
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "inDNIPropietario")
    private Propietario propietario;

    /*Como un inmueble  puede tener muchos contratos creamos un objeto de tipo List que contendrá
    todos los contratos de un inmueble tenemos que mapearlo */
    @OneToMany(mappedBy = "inmueble", fetch = FetchType.EAGER)
    private List<Contrato> contratos = new ArrayList<>();

    public Inmueble() {
        super();
    }

    public Inmueble(String codInmueble, String direccion, Zona zona, boolean estado, Propietario propietario) {
        super();
        this.codInmueble = codInmueble;
        this.direccion = direccion;
        this.zona = zona;
        this.estado = estado;
        this.propietario = propietario;
    }

    public String getCodInmueble() {
        return codInmueble;
    }

    public void setCodInmueble(String codInmueble) {
        this.codInmueble = codInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codInmueble == null) ? 0 : codInmueble.hashCode());
        result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
        result = prime * result + (estado ? 1231 : 1237);
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
        Inmueble other = (Inmueble) obj;
        if (codInmueble == null) {
            if (other.codInmueble != null)
                return false;
        } else if (!codInmueble.equals(other.codInmueble))
            return false;
        if (direccion == null) {
            if (other.direccion != null)
                return false;
        } else if (!direccion.equals(other.direccion))
            return false;
        if (estado != other.estado)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Inmueble [codInmueble=" + codInmueble + ", direccion=" + direccion + ", zona=" + zona + ", estado="
                + estado + ", propietario=" + propietario + "]";
    }


}

