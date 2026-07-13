package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.EntregaMedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.model.EntregaMedicamento;
import com.hospital_vm_vl.hospital_vm.repository.EntregaMedicamentoRepository;
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
public class EntregaMedicamentoServiceTest {
    @Mock
    private EntregaMedicamentoRepository repository;

    @InjectMocks
    private EntregaMedicamentoService service;

    @Test
    void testFindAll() {
        // GIVEN
        EntregaMedicamento d = new EntregaMedicamento(1L, 1L, "Calle Falsa 123", "Enviado");
        when(repository.findAll()).thenReturn(Arrays.asList(d));

        // WHEN
        List<EntregaMedicamentoDTO> resultado = service.findAll();

        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Enviado", resultado.get(0).getEstado());
        assertEquals("Calle Falsa 123", resultado.get(0).getDireccion());
        assertEquals(1L, resultado.get(0).getVentaId());
    }

    @Test
    void testSave() {
        // GIVEN
        EntregaMedicamentoDTO dto = new EntregaMedicamentoDTO();
        dto.setVentaId(1L);
        dto.setDireccion("Calle Falsa 123");
        dto.setEstado("Enviado");

        EntregaMedicamento guardado = new EntregaMedicamento(1L, 1L, "Calle Falsa 123", "Enviado");
        when(repository.save(any(EntregaMedicamento.class))).thenReturn(guardado);

        // WHEN
        EntregaMedicamentoDTO resultado = service.save(dto);

        // THEN
        assertNotNull(resultado.getId());
        assertEquals("Enviado", resultado.getEstado());
        verify(repository, times(1)).save(any(EntregaMedicamento.class));
    }

    @Test
    void testFindById_NotFound() {
        // GIVEN
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> service.findById(999L));
    }
}