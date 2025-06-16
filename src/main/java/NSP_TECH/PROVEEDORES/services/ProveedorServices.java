package NSP_TECH.PROVEEDORES.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NSP_TECH.PROVEEDORES.model.proveedor;
import NSP_TECH.PROVEEDORES.repository.proveedorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProveedorServices {
    @Autowired
    private proveedorRepository pRespository;

    public List<proveedor> BuscarTodoProveedores(){
        return pRespository.findAll();
    }

    public proveedor BuscarUnProveedor(Long ID_PROVEEDOR){
        return pRespository.findById(ID_PROVEEDOR).get();

    }

    public proveedor GuardarProveedor(proveedor proveedor){
        return pRespository.save(proveedor);

    }

    public void EliminarProveedor(Long ID_PROVEEDOR){
        pRespository.deleteById(ID_PROVEEDOR);
    }

}
