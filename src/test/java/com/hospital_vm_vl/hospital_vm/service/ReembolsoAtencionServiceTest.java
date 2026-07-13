package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ReembolsoAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.ReembolsoAtencion;
import com.hospital_vm_vl.hospital_vm.repository.DevolucionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReembolsoAtencionServiceTest {
    @Mock
    private DevolucionRepository repository;

    @InjectMocks
    private ReembolsoAtencionService service;

    @Test
    void testFindAll() {
        // GIVEN
        ReembolsoAtencion d = new ReembolsoAtencion(1L, 1L, "Defecto", LocalDateTime.now());
        when(repository.findAll()).thenReturn(Arrays.asList(d));

        // WHEN
        List<ReembolsoAtencionDTO> resultado = service.findAll();

        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(d.getMotivo(), resultado.get(0).getMotivo());
        // CORREGIDO: Usamos getVentaId() en ambos lados para asegurar compatibilidad
        assertEquals(d.getVentaId(), resultado.get(0).getVentaId());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // GIVEN
        ReembolsoAtencionDTO dto = new ReembolsoAtencionDTO();
        dto.setMotivo("Defecto");
        dto.setVentaId(1L);

        ReembolsoAtencion guardado = new ReembolsoAtencion(1L, 1L, "Defecto", LocalDateTime.now());
        when(repository.save(any(ReembolsoAtencion.class))).thenReturn(guardado);

        // WHEN
        ReembolsoAtencionDTO resultado = service.save(dto);

        // THEN
        assertNotNull(resultado.getId());
        assertEquals(dto.getMotivo(), resultado.getMotivo());
        assertEquals(dto.getVentaId(), resultado.getVentaId());
        verify(repository, times(1)).save(any(ReembolsoAtencion.class));
    }

    @Test
    void testFindById_NotFound() {
        // GIVEN
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> service.findById(999L));
    }
}