package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.StockFarmaciaDTO;
import com.hospital_vm_vl.hospital_vm.model.StockFarmacia;
import com.hospital_vm_vl.hospital_vm.repository.StockFarmaciaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockFarmaciaServiceTest {

    @Mock
    private StockFarmaciaRepository repository;

    @InjectMocks
    private StockFarmaciaService service;

    @Test
    void testFindById_Success() {
        // GIVEN
        StockFarmacia i = new StockFarmacia(1L, 101L, 50);
        when(repository.findById(1L)).thenReturn(Optional.of(i));

        // WHEN
        StockFarmaciaDTO resultado = service.findById(1L);

        // THEN
        assertNotNull(resultado);
        assertEquals(i.getCantidad(), resultado.getCantidad());
        assertEquals(i.getProductoId(), resultado.getProductoId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // GIVEN
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> service.findById(999L));
    }

    @Test
    void testDelete_Success() {
        // GIVEN: Le decimos a Mockito que el objeto SÍ existe en existsById
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        // WHEN
        service.delete(1L);

        // THEN: Verificamos que pasó por ambos métodos del repositorio
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        // GIVEN: Simulamos que NO existe
        when(repository.existsById(999L)).thenReturn(false);

        // WHEN / THEN: Se debe lanzar la excepción y NUNCA se debe llamar a deleteById
        assertThrows(RuntimeException.class, () -> service.delete(999L));
        verify(repository, never()).deleteById(anyLong());
    }
}