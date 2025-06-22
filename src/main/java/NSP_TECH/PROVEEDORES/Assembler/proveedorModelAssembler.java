package NSP_TECH.PROVEEDORES.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import NSP_TECH.PROVEEDORES.controller.ProveedorController;
import NSP_TECH.PROVEEDORES.model.proveedor;

@Component
public class proveedorModelAssembler implements RepresentationModelAssembler<proveedor, EntityModel<proveedor>>{

    @Override
    public EntityModel<proveedor> toModel(proveedor p){
        return EntityModel.of(
            p,
            linkTo(methodOn(ProveedorController.class).BuscarProveedor(p.getId_proveedor())).withRel("LINKS"),
            linkTo(methodOn(ProveedorController.class).ListarProveedores()).withRel("todas-los-proveedores"),
            linkTo(methodOn(ProveedorController.class).ActualizarProveedor(p.getId_proveedor(), p)).withRel("actualiza-un-proveedor")
        );
    }
}
/*    linkTo(methodOn(ProveedorController.class).EliminarProveedor(p.get())).withRel("elimina-un-proveedor")*/