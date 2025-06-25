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
    @Schema(description = "Identificador del proveedor", example="1")
    @Column(name="ID_PROVEEDOR")
    private Long id_proveedor;

    @Column(name="NOMBRE", nullable=false, length=300)
    @Schema(description = "nombre del proveedor",example="JPerez S.A" )
    private String nombre;

    @Column(name="CONTACTO", nullable=false, length=50)
    @Schema(description = "Contacto con el proveedor", example="juan perez jefe produccion ")
    private String contacto;

    @Column(name="TELEFONO", nullable=true, precision=9)
    @Schema(description = "telefono de contacto con el proveedor", example="999999999")
    private int telefono;

    @Column(name="EMAIL", nullable=true, length=50)
    @Schema(description = "direccion de correo de contacto", example="xxxx@xxx.xx")
    private String email;

    @Column(name="ESTADO", nullable=false, length=1)
    @Schema(description = "estado del proveedor", example="A=Activo / I=Inactivo")
    private char estado;
}
