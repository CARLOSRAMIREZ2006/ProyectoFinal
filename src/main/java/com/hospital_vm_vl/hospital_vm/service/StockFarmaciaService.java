package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.StockFarmaciaDTO;
import com.hospital_vm_vl.hospital_vm.model.StockFarmacia;
import com.hospital_vm_vl.hospital_vm.repository.StockFarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockFarmaciaService {
    @Autowired
    private StockFarmaciaRepository repository;

    public List<StockFarmaciaDTO> findAll() {
        return repository.findAll().stream().map(i -> {
            StockFarmaciaDTO dto = new StockFarmaciaDTO();
            dto.setId(i.getId());
            dto.setProductoId(i.getProductoId());
            dto.setCantidad(i.getCantidad());
            return dto;
        }).collect(Collectors.toList());
    }

    public StockFarmaciaDTO findById(Long id) {
        StockFarmacia i = repository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        StockFarmaciaDTO dto = new StockFarmaciaDTO();
        dto.setId(i.getId());
        dto.setProductoId(i.getProductoId());
        dto.setCantidad(i.getCantidad());
        return dto;
    }

    public StockFarmaciaDTO save(StockFarmaciaDTO dto) {
        StockFarmacia i = new StockFarmacia(null, dto.getProductoId(), dto.getCantidad());
        StockFarmacia saved = repository.save(i);
        dto.setId(saved.getId());
        return dto;
    }

    public StockFarmaciaDTO update(Long id, StockFarmaciaDTO dto) {
        StockFarmacia i = repository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        i.setCantidad(dto.getCantidad());
        StockFarmacia updated = repository.save(i);
        dto.setId(updated.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}