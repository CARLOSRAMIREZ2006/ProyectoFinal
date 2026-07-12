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
        Pago p = new Pago(1L, 1L, 250.0, "Tarjeta");
        when(repository.findAll()).thenReturn(Arrays.asList(p));

        List<PagoDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
        assertEquals("Tarjeta", resultado.get(0).getMetodoPago());
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}