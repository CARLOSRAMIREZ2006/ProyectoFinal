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
    void testDelete() {
        // GIVEN
        doNothing().when(repository).deleteById(1L);

        // WHEN
        service.delete(1L);

        // THEN
        verify(repository, times(1)).deleteById(1L);
    }
}