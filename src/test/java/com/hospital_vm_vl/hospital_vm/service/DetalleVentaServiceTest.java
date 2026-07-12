package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.DetalleVentaDTO;
import com.hospital_vm_vl.hospital_vm.model.DetalleVenta;
import com.hospital_vm_vl.hospital_vm.repository.DetalleVentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetalleVentaServiceTest {

    @Mock
    private DetalleVentaRepository repository;

    @InjectMocks
    private DetalleVentaService service;

    @Test
    void testFindAll() {
        DetalleVenta dv = new DetalleVenta(1L, 1L, 101L, 2, 50.0);
        when(repository.findAll()).thenReturn(Arrays.asList(dv));

        List<DetalleVentaDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
        assertEquals(2, resultado.get(0).getCantidad());
    }

    @Test
    void testSave() {
        DetalleVentaDTO dto = new DetalleVentaDTO();
        dto.setVentaId(1L);
        dto.setProductoId(101L);
        dto.setCantidad(2);

        DetalleVenta saved = new DetalleVenta(1L, 1L, 101L, 2, 50.0);
        when(repository.save(any(DetalleVenta.class))).thenReturn(saved);

        DetalleVentaDTO resultado = service.save(dto);

        assertNotNull(resultado.getId());
        verify(repository, times(1)).save(any(DetalleVenta.class));
    }
}