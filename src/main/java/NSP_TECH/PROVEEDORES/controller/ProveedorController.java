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

import NSP_TECH.PROVEEDORES.model.proveedor;
import NSP_TECH.PROVEEDORES.services.ProveedorServices;

@RestController
@RequestMapping("api/v1/Proveedores")
public class ProveedorController {


    @Autowired

    private ProveedorServices pService;

    @GetMapping
    public ResponseEntity<?> ListarProductos(){
        List<proveedor> proveedores = pService.BuscarTodoProveedores();
        if (proveedores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
        } else {
            return ResponseEntity.ok(proveedores);
        }
    }
    @GetMapping("/{ID_PROVEEDOR}")
    public ResponseEntity<?> BuscarProducto(@PathVariable Long ID_PROVEEDOR){

        try {
            proveedor pBuscado = pService.BuscarUnProveedor(ID_PROVEEDOR);
            return ResponseEntity.ok(pBuscado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Proveedor");
        }
        
    }

    @PostMapping
    public ResponseEntity<?> GuardarProveedor(@RequestBody proveedor pGuardar){
    try {
            proveedor pRegistrar = pService.GuardarProveedor(pGuardar);
            return ResponseEntity.ok(pRegistrar);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Proveedor");
    }
    }
    
    @PutMapping("/{ID_PROVEEDOR}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS
        
    public ResponseEntity<?> ActualizarProveedor(@PathVariable Long ID_PROVEEDOR, @RequestBody proveedor pActualizar){
        try {
            proveedor pActualizado = pService.BuscarUnProveedor(ID_PROVEEDOR);
            pActualizado.setID_PROVEEDOR(pActualizar.getID_PROVEEDOR());
            pActualizado.setNOMBRE(pActualizar.getNOMBRE());
            pActualizado.setCONTACTO(pActualizar.getCONTACTO());
            pActualizado.setTELEFONO(pActualizar.getTELEFONO());
            pActualizado.setEMAIL(pActualizar.getEMAIL());
            pActualizado.setESTADO(pActualizar.getESTADO());
            pService.GuardarProveedor(pActualizado);
            return ResponseEntity.ok(pActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no esta registrado");
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
