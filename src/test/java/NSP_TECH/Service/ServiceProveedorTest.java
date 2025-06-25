package NSP_TECH.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import NSP_TECH.PROVEEDORES.model.proveedor;
import NSP_TECH.PROVEEDORES.repository.proveedorRepository;
import NSP_TECH.PROVEEDORES.services.ProveedorServices;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;

public class ServiceProveedorTest {
    
    @Mock
    private proveedorRepository proveedorepository;
    
    @InjectMocks
    private ProveedorServices proveedorservices;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    
    @Test
    public void testBuscarTodo(){
    java.util.List<proveedor> lista = new  ArrayList<>();

    proveedor prov1 = new proveedor();
    proveedor prov2 = new proveedor();

    prov1.setId_proveedor(11L);
    prov1.setNombre("Bastian");
    prov1.setContacto("aaabbbccc");
    prov1.setTelefono(998756325);
    prov1.setEmail("xxx@xx.xx");
    prov1.setEstado('A');

    prov2.setId_proveedor(12L);
    prov2.setNombre("Maryanne");
    prov2.setContacto("aaabbbccc");
    prov2.setTelefono(998756325);
    prov2.setEmail("xxx@xx.xx");
    prov2.setEstado('D');
    

    lista.add(prov1);
    lista.add(prov2);

    when(proveedorepository.findAll()).thenReturn(lista);

    java.util.List<proveedor> resultadoBusqueda = proveedorservices.BuscarTodoProveedores();

    assertEquals(2,resultadoBusqueda.size());
    verify(proveedorepository, times(1)).findAll();

}


    @Test
    public void testBuscarUnProveedor(){
    proveedor prov = new proveedor();

    prov.setId_proveedor(11L);
    prov.setNombre("Bastian");
    prov.setContacto("aaabbbccc");
    prov.setTelefono(998756325);
    prov.setEmail("xxx@xx.xx");
    prov.setEstado('A');

    when(proveedorepository.findById(11L)).thenReturn(Optional.of(prov));

    proveedor provBuscado = proveedorservices.BuscarUnProveedor(11L);
    assertEquals(11L,provBuscado.getId_proveedor());
    verify(proveedorepository, times(1)).findById(11L);

    }



    @Test
    public void testGuardarProveedor(){
        proveedor prov = new proveedor();

        prov.setId_proveedor(11L);
        prov.setNombre("Bastian");
        prov.setContacto("aaabbbccc");
        prov.setTelefono(998756325);
        prov.setEmail("xxx@xx.xx");
        prov.setEstado('A');

        when(proveedorepository.save(any())).thenReturn(prov);

        proveedor provGuardados = proveedorservices.GuardarProveedor(prov);
        assertEquals('A', provGuardados.getEstado());
        verify(proveedorepository, times(1)).save(prov);

    }


/*

    @Test
    public void testEliminarEnvio(){
        Long id = 11L;
        doNothing().when(enviorepository).deleteById(id);

        enviosservices.Eliminar***(id);

        verify(enviosrepository.times(1)).deleteById(id);

    }
*/
}

