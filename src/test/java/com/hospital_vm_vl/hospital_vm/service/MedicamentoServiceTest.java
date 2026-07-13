package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.MedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.model.Medicamento;
import com.hospital_vm_vl.hospital_vm.repository.MedicamentoRepository;
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
public class MedicamentoServiceTest {

    @Mock
    private MedicamentoRepository repository;

    @InjectMocks
    private MedicamentoService service;

    @Test
    void testFindAll() {
        // GIVEN
        Medicamento p1 = new Medicamento(1L, "Producto A", 100.0, 10);
        when(repository.findAll()).thenReturn(Arrays.asList(p1));

        // WHEN
        List<MedicamentoDTO> resultado = service.findAll();

        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(p1.getNombre(), resultado.get(0).getNombre());
        assertEquals(p1.getPrecio(), resultado.get(0).getPrecio());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        // GIVEN
        Medicamento p1 = new Medicamento(1L, "Producto A", 100.0, 10);
        when(repository.findById(1L)).thenReturn(Optional.of(p1));

        // WHEN
        MedicamentoDTO resultado = service.findById(1L);

        // THEN
        assertNotNull(resultado);
        assertEquals(p1.getNombre(), resultado.getNombre());
        assertEquals(p1.getPrecio(), resultado.getPrecio());
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
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setNombre("Producto Nuevo");
        dto.setPrecio(50.0);
        Medicamento saved = new Medicamento(1L, "Producto Nuevo", 50.0, 5);
        when(repository.save(any(Medicamento.class))).thenReturn(saved);

        // WHEN
        MedicamentoDTO resultado = service.save(dto);

        // THEN
        assertNotNull(resultado.getId());
        assertEquals(dto.getNombre(), resultado.getNombre());
        assertEquals(dto.getPrecio(), resultado.getPrecio());
        verify(repository, times(1)).save(any(Medicamento.class));
    }
}