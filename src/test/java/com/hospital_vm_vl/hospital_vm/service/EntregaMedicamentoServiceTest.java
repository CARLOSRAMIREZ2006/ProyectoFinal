package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.EntregaMedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.model.EntregaMedicamento;
import com.hospital_vm_vl.hospital_vm.repository.EntregaMedicamentoRepository;
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
public class EntregaMedicamentoServiceTest {
    @Mock
    private EntregaMedicamentoRepository repository;
    @InjectMocks
    private EntregaMedicamentoService service;

    @Test
    void testFindAll() {
        EntregaMedicamento d = new EntregaMedicamento(1L, 1L, "Calle Falsa 123", "Enviado");
        when(repository.findAll()).thenReturn(Arrays.asList(d));
        List<EntregaMedicamentoDTO> resultado = service.findAll();
        assertEquals(1, resultado.size());
        assertEquals("Enviado", resultado.get(0).getEstado());
    }
}