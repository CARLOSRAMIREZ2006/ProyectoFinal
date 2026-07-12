package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ClienteDTO;
import com.hospital_vm_vl.hospital_vm.model.Cliente;
import com.hospital_vm_vl.hospital_vm.repository.ClienteRepository;
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
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @Test
    void testFindAll() {
        Cliente c1 = new Cliente(1L, "Juan Perez", "juan@test.com", "123456");
        when(repository.findAll()).thenReturn(Arrays.asList(c1));

        List<ClienteDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombre());
    }

    @Test
    void testFindById_Success() {
        Cliente c1 = new Cliente(1L, "Juan Perez", "juan@test.com", "123456");
        when(repository.findById(1L)).thenReturn(Optional.of(c1));

        ClienteDTO resultado = service.findById(1L);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
    }

    @Test
    void testSave() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre("Ana Lopez");
        Cliente saved = new Cliente(2L, "Ana Lopez", "ana@test.com", "654321");

        when(repository.save(any(Cliente.class))).thenReturn(saved);

        ClienteDTO resultado = service.save(dto);

        assertEquals(2L, resultado.getId());
        verify(repository, times(1)).save(any(Cliente.class));
    }
}