package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.LiquidacionAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.LiquidacionAtencion;
import com.hospital_vm_vl.hospital_vm.repository.LiquidacionAtencionRepository;
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
public class LiquidacionAtencionServiceTest {

    @Mock
    private LiquidacionAtencionRepository repository;

    @InjectMocks
    private LiquidacionAtencionService service;

    @Test
    void testFindAll() {
        LiquidacionAtencion f = new LiquidacionAtencion(1L, 1L, LocalDateTime.now(), 150.0);
        when(repository.findAll()).thenReturn(Arrays.asList(f));

        List<LiquidacionAtencionDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
        assertEquals(150.0, resultado.get(0).getMontoTotal());
    }

    @Test
    void testSave() {
        LiquidacionAtencionDTO dto = new LiquidacionAtencionDTO();
        dto.setVentaId(1L);
        dto.setMontoTotal(150.0);

        LiquidacionAtencion saved = new LiquidacionAtencion(1L, 1L, LocalDateTime.now(), 150.0);
        when(repository.save(any(LiquidacionAtencion.class))).thenReturn(saved);

        LiquidacionAtencionDTO resultado = service.save(dto);

        assertNotNull(resultado.getId());
        verify(repository, times(1)).save(any(LiquidacionAtencion.class));
    }
}