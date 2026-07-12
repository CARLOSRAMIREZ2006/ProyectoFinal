package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.FacturacionDTO;
import com.hospital_vm_vl.hospital_vm.model.Facturacion;
import com.hospital_vm_vl.hospital_vm.repository.FacturacionRepository;
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
public class FacturacionServiceTest {

    @Mock
    private FacturacionRepository repository;

    @InjectMocks
    private FacturacionService service;

    @Test
    void testFindAll() {
        Facturacion f = new Facturacion(1L, 1L, LocalDateTime.now(), 150.0);
        when(repository.findAll()).thenReturn(Arrays.asList(f));

        List<FacturacionDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
        assertEquals(150.0, resultado.get(0).getMontoTotal());
    }

    @Test
    void testSave() {
        FacturacionDTO dto = new FacturacionDTO();
        dto.setVentaId(1L);
        dto.setMontoTotal(150.0);

        Facturacion saved = new Facturacion(1L, 1L, LocalDateTime.now(), 150.0);
        when(repository.save(any(Facturacion.class))).thenReturn(saved);

        FacturacionDTO resultado = service.save(dto);

        assertNotNull(resultado.getId());
        verify(repository, times(1)).save(any(Facturacion.class));
    }
}