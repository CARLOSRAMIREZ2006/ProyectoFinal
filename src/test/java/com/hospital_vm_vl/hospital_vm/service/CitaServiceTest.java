package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.CitaDTO;
import com.hospital_vm_vl.hospital_vm.model.Cita;
import com.hospital_vm_vl.hospital_vm.repository.CitaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CitaServiceTest {

    @Mock
    private CitaRepository repository;

    @InjectMocks
    private CitaService service;

    @Test
    void testFindAll() {
        Cita v = new Cita(1L, 10L, LocalDateTime.now(), 500.0);
        when(repository.findAll()).thenReturn(Arrays.asList(v));

        List<CitaDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
        assertEquals(500.0, resultado.get(0).getTotal());
    }

    @Test
    void testSave() {
        CitaDTO dto = new CitaDTO();
        dto.setClienteId(10L);
        dto.setTotal(500.0);
        Cita saved = new Cita(1L, 10L, LocalDateTime.now(), 500.0);
        when(repository.save(any(Cita.class))).thenReturn(saved);

        CitaDTO resultado = service.save(dto);

        assertNotNull(resultado.getId());
        verify(repository, times(1)).save(any(Cita.class));
    }
}