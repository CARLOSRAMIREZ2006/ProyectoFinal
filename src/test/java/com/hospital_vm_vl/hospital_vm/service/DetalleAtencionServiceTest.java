package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.DetalleAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.DetalleAtencion;
import com.hospital_vm_vl.hospital_vm.repository.DetalleAtencionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetalleAtencionServiceTest {

    @Mock
    private DetalleAtencionRepository repository;

    @InjectMocks
    private DetalleAtencionService service;

    @Test
    void testFindAll() {
        // GIVEN
        DetalleAtencion dv = new DetalleAtencion(1L, 1L, 101L, 2, 50.0);
        when(repository.findAll()).thenReturn(Arrays.asList(dv));

        // WHEN
        List<DetalleAtencionDTO> resultado = service.findAll();

        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(dv.getVentaId(), resultado.get(0).getVentaId());
        assertEquals(dv.getCantidad(), resultado.get(0).getCantidad());
        assertEquals(dv.getProductoId(), resultado.get(0).getProductoId());
    }

    @Test
    void testSave() {
        // GIVEN
        DetalleAtencionDTO dto = new DetalleAtencionDTO();
        dto.setVentaId(1L);
        dto.setProductoId(101L);
        dto.setCantidad(2);

        DetalleAtencion saved = new DetalleAtencion(1L, 1L, 101L, 2, 50.0);
        when(repository.save(any(DetalleAtencion.class))).thenReturn(saved);

        // WHEN
        DetalleAtencionDTO resultado = service.save(dto);

        // THEN
        assertNotNull(resultado.getId());
        assertEquals(dto.getCantidad(), resultado.getCantidad());
        verify(repository, times(1)).save(any(DetalleAtencion.class));
    }

    @Test
    void testFindById_NotFound() {
        // GIVEN
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> service.findById(999L));
    }
}