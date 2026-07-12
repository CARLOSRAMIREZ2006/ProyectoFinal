package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ProductoDTO;
import com.hospital_vm_vl.hospital_vm.model.Producto;
import com.hospital_vm_vl.hospital_vm.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repository;

    public List<ProductoDTO> findAll() {
        return repository.findAll().stream().map(p -> {
            ProductoDTO dto = new ProductoDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setPrecio(p.getPrecio());
            dto.setStock(p.getStock());
            return dto;
        }).collect(Collectors.toList());
    }

    public ProductoDTO findById(Long id) {
        Producto p = repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        return dto;
    }

    public ProductoDTO save(ProductoDTO dto) {
        Producto p = new Producto(null, dto.getNombre(), dto.getPrecio(), dto.getStock());
        Producto saved = repository.save(p);
        dto.setId(saved.getId());
        return dto;
    }

    public ProductoDTO update(Long id, ProductoDTO dto) {
        Producto p = repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        Producto updated = repository.save(p);
        dto.setId(updated.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}