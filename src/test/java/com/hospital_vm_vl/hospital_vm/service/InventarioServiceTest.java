package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.InventarioDTO;
import com.hospital_vm_vl.hospital_vm.model.Inventario;
import com.hospital_vm_vl.hospital_vm.repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {

    @Mock
    private InventarioRepository repository;

    @InjectMocks
    private InventarioService service;

    @Test
    void testFindById_Success() {
        Inventario i = new Inventario(1L, 101L, 50);
        when(repository.findById(1L)).thenReturn(Optional.of(i));

        InventarioDTO resultado = service.findById(1L);

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