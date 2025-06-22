package NSP_TECH.PROVEEDORES.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import NSP_TECH.PROVEEDORES.Assembler.proveedorModelAssembler;
import NSP_TECH.PROVEEDORES.model.proveedor;
import NSP_TECH.PROVEEDORES.services.ProveedorServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/Proveedores")
public class ProveedorController {


    @Autowired

    private ProveedorServices pService;
    
    @Autowired
    private proveedorModelAssembler assembler;
// ENDPOINT PARA BUSCAR TODOS LOS PROVEEDORES
    @GetMapping
        @Operation(summary = "PROVEEDORES", description = "Operacion que lista todos los proveedores")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los proveedores", content = @Content(mediaType = "application/json", schema = @Schema(implementation = proveedor.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun proveedor", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))

    })

    
    public ResponseEntity<?> ListarProveedores(){
        List<proveedor> proveedores = pService.BuscarTodoProveedores();
        if (proveedores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
        } else {
            return ResponseEntity.ok(assembler.toCollectionModel(proveedores));
        }
    }


    // ENDPOINT PARA BUSCAR UN PROVEEDOR
    @GetMapping("/{ID_PROVEEDOR}")
    @Operation(summary = "PROVEEDOR", description = "Operacion que lista un Proveedor")
    @Parameters (value = {
        @Parameter (name="ID_PROVEEDOR", description= "ID del proveedor que se buscara", in = ParameterIn.PATH, required= true)

    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el proveedor ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = proveedor.class))), 
        @ApiResponse(responseCode = "404", description = "No se encontro ningun proveedor", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })
    public ResponseEntity<?> BuscarProveedor(@PathVariable Long ID_PROVEEDOR){

        try {
            proveedor pBuscado = pService.BuscarUnProveedor(ID_PROVEEDOR);
            return ResponseEntity.ok(assembler.toModel(pBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Proveedor");
        }
        
    }


   // ENDPOINT PARA REGISTRAR UN PROVEEDOR
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UN PROVEEDOR", description = "ENDPOINT QUE REGISTRA UN PROVEEDOR",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="PROVEEDOR QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = proveedor.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente el proveedor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = proveedor.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el proveedor", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el proveedor")))
    })
    public ResponseEntity<?> GuardarProveedor(@RequestBody proveedor pGuardar){
    try {
            proveedor pRegistrar = pService.GuardarProveedor(pGuardar);
            return ResponseEntity.ok(assembler.toModel(pRegistrar));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Proveedor");
    }
    }




  // ENDPOINT PARA EDITAR UN PROVEEDOR
    
    @PutMapping("/{ID_PROVEEDOR}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS

    
    @Operation(summary = "ENDPOINT QUE EDITA UN PROVEEDOR", description = "ENDPOINT QUE EDITA UN PROVEEDOR", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="PROVEEDOR QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = proveedor.class))))
    @Parameters (value = {
        @Parameter (name="ID_PROVEEDOR", description= "ID del proveedor que se editara", in = ParameterIn.PATH, required= true)})

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se edito correctamente el proveedor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = proveedor.class))),
        @ApiResponse(responseCode = "500", description = "Proveedor no registrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el proveedor")))
    })
        
    public ResponseEntity<?> ActualizarProveedor(@PathVariable Long ID_PROVEEDOR, @RequestBody proveedor pActualizar){
        try {
            proveedor pActualizado = pService.BuscarUnProveedor(ID_PROVEEDOR);
            pActualizado.setNombre(pActualizar.getNombre());
            pActualizado.setContacto(pActualizar.getContacto());
            pActualizado.setTelefono(pActualizar.getTelefono());
            pActualizado.setEmail(pActualizar.getEmail());
            pActualizado.setEstado(pActualizar.getEstado());
            pService.GuardarProveedor(pActualizado);
            return ResponseEntity.ok(assembler.toModel(pActualizado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no registrado");
        }
    }

    /*
        @DeleteMapping("/{ID_PROVEEDOR}")
        public ResponseEntity<String> EliminarProveedor(@PathVariable Long ID_PROVEEDOR){
            try {
                proveedor pBuscado = pService.BuscarUnProveedor(ID_PROVEEDOR);
                pService.EliminarProveedor(ID_PROVEEDOR);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimina proveedor");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proveedor no esta registrado");
            }
        }
     */
    



}
