package voModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Table(name = "Inquilinos")
public class Inquilino implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "iqDNI", length = 9, nullable = false)
    private String dni;

    @Column(name = "iqNombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "iqDireccion", length = 50, nullable = false)
    private String direccion;

    @Column(name = "iqTelefono", length = 9, nullable = false)
    private String telefono;

    @Column(name = "iqSalario", nullable = false)
    private double salario;

    /*Como un inquilino  puede tener muchos contratos creamos un
     objeto de tipo List que contendrá todos los contratos
     de un inquilino tenemos que mapearlo*/
    @OneToMany(mappedBy = "inquilino", fetch = FetchType.EAGER)
    private List<Contrato> contratos = new ArrayList<>();

    public Inquilino() {
        super();
    }


    public Inquilino(String dni) {
        super();
        this.dni = dni;
    }


    public Inquilino(String dni, String nombre, String direccion, String telefono, double salario) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.salario = salario;
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
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
        result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
        Inquilino other = (Inquilino) obj;
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
        return true;
    }


    @Override
    public String toString() {
        return "Inquilino [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
                + ", salario=" + salario + "]";
    }


}

