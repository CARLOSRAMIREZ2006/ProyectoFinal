package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.PacienteDTO;
import com.hospital_vm_vl.hospital_vm.model.Paciente;
import com.hospital_vm_vl.hospital_vm.repository.PacienteRepository;
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
public class PacienteServiceTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteService service;

    @Test
    void testFindAll() {
        Paciente c1 = new Paciente(1L, "Juan Perez", "juan@test.com", "123456");
        when(repository.findAll()).thenReturn(Arrays.asList(c1));

        List<PacienteDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombre());
    }

    @Test
    void testFindById_Success() {
        Paciente c1 = new Paciente(1L, "Juan Perez", "juan@test.com", "123456");
        when(repository.findById(1L)).thenReturn(Optional.of(c1));

        PacienteDTO resultado = service.findById(1L);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
    }

    @Test
    void testSave() {
        PacienteDTO dto = new PacienteDTO();
        dto.setNombre("Ana Lopez");
        Paciente saved = new Paciente(2L, "Ana Lopez", "ana@test.com", "654321");

        when(repository.save(any(Paciente.class))).thenReturn(saved);

        PacienteDTO resultado = service.save(dto);

        assertEquals(2L, resultado.getId());
        verify(repository, times(1)).save(any(Paciente.class));
    }
}