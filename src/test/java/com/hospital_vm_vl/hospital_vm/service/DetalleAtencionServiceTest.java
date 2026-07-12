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
        DetalleAtencion dv = new DetalleAtencion(1L, 1L, 101L, 2, 50.0);
        when(repository.findAll()).thenReturn(Arrays.asList(dv));

        List<DetalleAtencionDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
        assertEquals(2, resultado.get(0).getCantidad());
    }

    @Test
    void testSave() {
        DetalleAtencionDTO dto = new DetalleAtencionDTO();
        dto.setVentaId(1L);
        dto.setProductoId(101L);
        dto.setCantidad(2);

        DetalleAtencion saved = new DetalleAtencion(1L, 1L, 101L, 2, 50.0);
        when(repository.save(any(DetalleAtencion.class))).thenReturn(saved);

        DetalleAtencionDTO resultado = service.save(dto);

        assertNotNull(resultado.getId());
        verify(repository, times(1)).save(any(DetalleAtencion.class));
    }
}