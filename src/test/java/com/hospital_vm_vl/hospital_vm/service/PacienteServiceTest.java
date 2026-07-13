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
        // GIVEN
        Paciente c1 = new Paciente(1L, "Juan Perez", "juan@test.com", "123456");
        when(repository.findAll()).thenReturn(Arrays.asList(c1));

        // WHEN
        List<PacienteDTO> resultado = service.findAll();

        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(c1.getNombre(), resultado.get(0).getNombre());
        assertEquals(c1.getEmail(), resultado.get(0).getEmail());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        // GIVEN
        Paciente c1 = new Paciente(1L, "Juan Perez", "juan@test.com", "123456");
        when(repository.findById(1L)).thenReturn(Optional.of(c1));

        // WHEN
        PacienteDTO resultado = service.findById(1L);

        // THEN
        assertNotNull(resultado);
        assertEquals(c1.getNombre(), resultado.getNombre());
        assertEquals(c1.getEmail(), resultado.getEmail());
    }

    @Test
    void testFindById_NotFound() {
        // GIVEN
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> service.findById(1L));
    }

    @Test
    void testSave() {
        // GIVEN
        PacienteDTO dto = new PacienteDTO();
        dto.setNombre("Ana Lopez");
        dto.setEmail("ana@test.com");

        Paciente saved = new Paciente(2L, "Ana Lopez", "ana@test.com", "654321");
        when(repository.save(any(Paciente.class))).thenReturn(saved);

        // WHEN
        PacienteDTO resultado = service.save(dto);

        // THEN
        assertNotNull(resultado);
        assertEquals(2L, resultado.getId());
        assertEquals("Ana Lopez", resultado.getNombre());
        verify(repository, times(1)).save(any(Paciente.class));
    }
}