package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ProductoDTO;
import com.hospital_vm_vl.hospital_vm.model.Producto;
import com.hospital_vm_vl.hospital_vm.repository.ProductoRepository;
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
public class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    @Test
    void testFindAll() {
        // Given
        Producto p1 = new Producto(1L, "Producto A", 100.0, 10);
        when(repository.findAll()).thenReturn(Arrays.asList(p1));

        // When
        List<ProductoDTO> resultado = service.findAll();

        // Then
        assertEquals(1, resultado.size());
        assertEquals("Producto A", resultado.get(0).getNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        // Given
        Producto p1 = new Producto(1L, "Producto A", 100.0, 10);
        when(repository.findById(1L)).thenReturn(Optional.of(p1));

        // When
        ProductoDTO resultado = service.findById(1L);

        // Then
        assertNotNull(resultado);
        assertEquals("Producto A", resultado.getNombre());
    }

    @Test
    void testFindById_NotFound() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Then
        assertThrows(RuntimeException.class, () -> service.findById(1L));
    }

    @Test
    void testSave() {
        // Given
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre("Producto Nuevo");
        dto.setPrecio(50.0);
        Producto saved = new Producto(1L, "Producto Nuevo", 50.0, 5);
        when(repository.save(any(Producto.class))).thenReturn(saved);

        // When
        ProductoDTO resultado = service.save(dto);

        // Then
        assertEquals(1L, resultado.getId());
        verify(repository, times(1)).save(any(Producto.class));
    }
}