package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ReembolsoAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.ReembolsoAtencion;
import com.hospital_vm_vl.hospital_vm.repository.DevolucionRepository;
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
public class ReembolsoAtencionServiceTest {
    @Mock
    private DevolucionRepository repository;
    @InjectMocks
    private ReembolsoAtencionService service;

    @Test
    void testFindAll() {
        ReembolsoAtencion d = new ReembolsoAtencion(1L, 1L, "Defecto", LocalDateTime.now());
        when(repository.findAll()).thenReturn(Arrays.asList(d));
        List<ReembolsoAtencionDTO> resultado = service.findAll();
        assertEquals("Defecto", resultado.get(0).getMotivo());
    }
}