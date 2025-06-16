package NSP_TECH.PROVEEDORES.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="PROVEEDORES")
@AllArgsConstructor
@NoArgsConstructor

public class proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PROVEEDOR")
    private Long ID_PROVEEDOR;

    @Column(name="NOMBRE", nullable=false, length=300)
    private String NOMBRE;

    @Column(name="CONTACTO", nullable=false, length=50)
    private String CONTACTO;

    @Column(name="TELEFONO", nullable=true, precision=9)
    private int TELEFONO;

    @Column(name="EMAIL", nullable=true, length=50)
    private String EMAIL;

    @Column(name="ESTADO", nullable=false, length=1)
    private char ESTADO;
}
