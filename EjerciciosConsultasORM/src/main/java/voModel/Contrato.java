package voModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "Contratos")
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "coCodContrato")
    private String codContrato;

    /*como un contrato solo puede ser de un inquilino creamos un atributo de tipo Inquilino*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "coDNIInquilino")
    private Inquilino inquilino;

    /*como un contrato solo puede ser de un inmueble creamos un atributo de tipo Inmueble*/
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn (name = "coCodInmueble")
    private Inmueble inmueble;

    @Column (name = "coFechaContrato", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaContrato;

    @Column (name = "coFechaVencimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    @Column (name = "coPrecio", nullable = false)
    private double precio;


    public Contrato() {
        super();
    }


    public Contrato(String codContrato, Inquilino inquilino, Inmueble inmueble, Date fechaContrato,
                    Date fechaVencimiento, double precio) {
        super();
        this.codContrato = codContrato;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
        this.fechaContrato = fechaContrato;
        this.fechaVencimiento = fechaVencimiento;
        this.precio = precio;
    }


    public String getCodContrato() {
        return codContrato;
    }


    public void setCodContrato(String codContrato) {
        this.codContrato = codContrato;
    }


    public Inquilino getInquilino() {
        return inquilino;
    }


    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }


    public Inmueble getInmueble() {
        return inmueble;
    }


    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }


    public Date getFechaContrato() {
        return fechaContrato;
    }


    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }


    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }


    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


    public double getPrecio() {
        return precio;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codContrato == null) ? 0 : codContrato.hashCode());
        result = prime * result + ((fechaContrato == null) ? 0 : fechaContrato.hashCode());
        result = prime * result + ((fechaVencimiento == null) ? 0 : fechaVencimiento.hashCode());
        long temp;
        temp = Double.doubleToLongBits(precio);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Contrato other = (Contrato) obj;
        if (codContrato == null) {
            if (other.codContrato != null)
                return false;
        } else if (!codContrato.equals(other.codContrato))
            return false;
        if (fechaContrato == null) {
            if (other.fechaContrato != null)
                return false;
        } else if (!fechaContrato.equals(other.fechaContrato))
            return false;
        if (fechaVencimiento == null) {
            if (other.fechaVencimiento != null)
                return false;
        } else if (!fechaVencimiento.equals(other.fechaVencimiento))
            return false;
        if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
            return false;
        return true;
    }

    public String toString() {
        return "Contrato [codContrato=" + codContrato +  ", inmueble=" + inmueble
                + ", fechaContrato=" + fechaContrato + ", fechaVencimiento=" + fechaVencimiento + ", precio=" + precio
                + "]";
    }
}
