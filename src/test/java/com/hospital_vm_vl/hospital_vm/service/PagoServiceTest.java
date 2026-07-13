package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.PagoDTO;
import com.hospital_vm_vl.hospital_vm.model.Pago;
import com.hospital_vm_vl.hospital_vm.repository.PagoRepository;
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
public class PagoServiceTest {

    @Mock
    private PagoRepository repository;

    @InjectMocks
    private PagoService service;

    @Test
    void testFindAll() {
        // GIVEN
        Pago p = new Pago(1L, 1L, 250.0, "Tarjeta");
        when(repository.findAll()).thenReturn(Arrays.asList(p));

        // WHEN
        List<PagoDTO> resultado = service.findAll();

        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(p.getMetodoPago(), resultado.get(0).getMetodoPago());
        assertEquals(p.getMonto(), resultado.get(0).getMonto());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // GIVEN
        PagoDTO dto = new PagoDTO();
        dto.setMonto(250.0);
        dto.setMetodoPago("Tarjeta");

        Pago guardado = new Pago(1L, 1L, 250.0, "Tarjeta");
        when(repository.save(any(Pago.class))).thenReturn(guardado);

        // WHEN
        PagoDTO resultado = service.save(dto);

        // THEN
        assertNotNull(resultado.getId());
        assertEquals(dto.getMetodoPago(), resultado.getMetodoPago());
        verify(repository, times(1)).save(any(Pago.class));
    }

    @Test
    void testDelete() {
        // GIVEN
        doNothing().when(repository).deleteById(1L);

        // WHEN
        service.delete(1L);

        // THEN
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // GIVEN
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // WHEN / THEN
        assertThrows(RuntimeException.class, () -> service.findById(999L));
    }
}