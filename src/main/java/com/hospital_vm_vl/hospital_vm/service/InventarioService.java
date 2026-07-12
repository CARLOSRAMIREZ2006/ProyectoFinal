package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.InventarioDTO;
import com.hospital_vm_vl.hospital_vm.model.Inventario;
import com.hospital_vm_vl.hospital_vm.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository repository;

    public List<InventarioDTO> findAll() {
        return repository.findAll().stream().map(i -> {
            InventarioDTO dto = new InventarioDTO();
            dto.setId(i.getId());
            dto.setProductoId(i.getProductoId());
            dto.setCantidad(i.getCantidad());
            return dto;
        }).collect(Collectors.toList());
    }

    public InventarioDTO findById(Long id) {
        Inventario i = repository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        InventarioDTO dto = new InventarioDTO();
        dto.setId(i.getId());
        dto.setProductoId(i.getProductoId());
        dto.setCantidad(i.getCantidad());
        return dto;
    }

    public InventarioDTO save(InventarioDTO dto) {
        Inventario i = new Inventario(null, dto.getProductoId(), dto.getCantidad());
        Inventario saved = repository.save(i);
        dto.setId(saved.getId());
        return dto;
    }

    public InventarioDTO update(Long id, InventarioDTO dto) {
        Inventario i = repository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        i.setCantidad(dto.getCantidad());
        Inventario updated = repository.save(i);
        dto.setId(updated.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}