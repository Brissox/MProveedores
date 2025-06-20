package NSP_TECH.PROVEEDORES.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Todos los proveedores registrados en la empresa")

public class proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "")
    @Column(name="ID_PROVEEDOR")
    private Long ID_PROVEEDOR;

    @Column(name="NOMBRE", nullable=false, length=300)
    @Schema(description = "")
    private String NOMBRE;

    @Column(name="CONTACTO", nullable=false, length=50)
    @Schema(description = "")
    private String CONTACTO;

    @Column(name="TELEFONO", nullable=true, precision=9)
    @Schema(description = "")
    private int TELEFONO;

    @Column(name="EMAIL", nullable=true, length=50)
    @Schema(description = "")
    private String EMAIL;

    @Column(name="ESTADO", nullable=false, length=1)
    @Schema(description = "")
    private char ESTADO;
}
