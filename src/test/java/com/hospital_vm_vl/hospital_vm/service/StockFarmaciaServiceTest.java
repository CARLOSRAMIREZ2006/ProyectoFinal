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
        StockFarmacia i = new StockFarmacia(1L, 101L, 50);
        when(repository.findById(1L)).thenReturn(Optional.of(i));

        StockFarmaciaDTO resultado = service.findById(1L);

        assertEquals(50, resultado.getCantidad());
        assertEquals(101L, resultado.getProductoId());
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}