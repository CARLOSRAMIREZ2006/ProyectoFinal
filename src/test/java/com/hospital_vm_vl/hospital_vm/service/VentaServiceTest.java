package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.VentaDTO;
import com.hospital_vm_vl.hospital_vm.model.Venta;
import com.hospital_vm_vl.hospital_vm.repository.VentaRepository;
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
public class VentaServiceTest {

    @Mock
    private VentaRepository repository;

    @InjectMocks
    private VentaService service;

    @Test
    void testFindAll() {
        Venta v = new Venta(1L, 10L, LocalDateTime.now(), 500.0);
        when(repository.findAll()).thenReturn(Arrays.asList(v));

        List<VentaDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
        assertEquals(500.0, resultado.get(0).getTotal());
    }

    @Test
    void testSave() {
        VentaDTO dto = new VentaDTO();
        dto.setClienteId(10L);
        dto.setTotal(500.0);
        Venta saved = new Venta(1L, 10L, LocalDateTime.now(), 500.0);
        when(repository.save(any(Venta.class))).thenReturn(saved);

        VentaDTO resultado = service.save(dto);

        assertNotNull(resultado.getId());
        verify(repository, times(1)).save(any(Venta.class));
    }
}