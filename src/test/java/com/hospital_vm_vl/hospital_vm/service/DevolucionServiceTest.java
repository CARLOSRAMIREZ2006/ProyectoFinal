package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.DevolucionDTO;
import com.hospital_vm_vl.hospital_vm.model.Devolucion;
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
public class DevolucionServiceTest {
    @Mock
    private DevolucionRepository repository;
    @InjectMocks
    private DevolucionService service;

    @Test
    void testFindAll() {
        Devolucion d = new Devolucion(1L, 1L, "Defecto", LocalDateTime.now());
        when(repository.findAll()).thenReturn(Arrays.asList(d));
        List<DevolucionDTO> resultado = service.findAll();
        assertEquals("Defecto", resultado.get(0).getMotivo());
    }
}