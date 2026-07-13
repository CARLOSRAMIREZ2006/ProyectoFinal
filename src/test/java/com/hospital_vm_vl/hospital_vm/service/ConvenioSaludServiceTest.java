package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ConvenioSaludDTO;
import com.hospital_vm_vl.hospital_vm.model.ConvenioSalud;
import com.hospital_vm_vl.hospital_vm.repository.ConvenioSaludRepository;
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
public class ConvenioSaludServiceTest {

    @Mock
    private ConvenioSaludRepository repository;

    @InjectMocks
    private ConvenioSaludService service;

    @Test
    void testFindAll() {
        // GIVEN
        ConvenioSalud p = new ConvenioSalud(1L, "Verano", 20.0, 101L);
        when(repository.findAll()).thenReturn(Arrays.asList(p));

        // WHEN
        List<ConvenioSaludDTO> resultado = service.findAll();

        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Verano", resultado.get(0).getNombre());
        assertEquals(20.0, resultado.get(0).getDescuento());
    }

    @Test
    void testFindById_NotFound() {
        // GIVEN
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> service.findById(999L));
    }
}