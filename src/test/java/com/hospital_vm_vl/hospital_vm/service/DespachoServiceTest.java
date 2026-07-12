package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.DespachoDTO;
import com.hospital_vm_vl.hospital_vm.model.Despacho;
import com.hospital_vm_vl.hospital_vm.repository.DespachoRepository;
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
public class DespachoServiceTest {
    @Mock
    private DespachoRepository repository;
    @InjectMocks
    private DespachoService service;

    @Test
    void testFindAll() {
        Despacho d = new Despacho(1L, 1L, "Calle Falsa 123", "Enviado");
        when(repository.findAll()).thenReturn(Arrays.asList(d));
        List<DespachoDTO> resultado = service.findAll();
        assertEquals(1, resultado.size());
        assertEquals("Enviado", resultado.get(0).getEstado());
    }
}