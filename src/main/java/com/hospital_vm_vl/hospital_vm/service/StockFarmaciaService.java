package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.StockFarmaciaDTO;
import com.hospital_vm_vl.hospital_vm.model.StockFarmacia;
import com.hospital_vm_vl.hospital_vm.repository.StockFarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StockFarmaciaService {
    private static final Logger log = LoggerFactory.getLogger(StockFarmaciaService.class);

    @Autowired
    private StockFarmaciaRepository repository;

    public List<StockFarmaciaDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public StockFarmaciaDTO findById(Long id) {
        StockFarmacia i = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Inventario no encontrado con ID: {}", id);
                    return new RuntimeException("Inventario no encontrado con ID: " + id);
                });
        return toDTO(i);
    }

    @Transactional
    public StockFarmaciaDTO save(StockFarmaciaDTO dto) {
        StockFarmacia i = new StockFarmacia(null, dto.getProductoId(), dto.getCantidad());
        StockFarmacia saved = repository.save(i);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public StockFarmaciaDTO update(Long id, StockFarmaciaDTO dto) {
        StockFarmacia i = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Inventario no encontrado con ID: {}", id);
                    return new RuntimeException("Inventario no encontrado con ID: " + id);
                });
        i.setCantidad(dto.getCantidad());
        i.setProductoId(dto.getProductoId());
        StockFarmacia updated = repository.save(i);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Inventario no encontrado con ID: {}", id);
            throw new RuntimeException("Inventario no encontrado");
        }
        repository.deleteById(id);
    }

    private StockFarmaciaDTO toDTO(StockFarmacia i) {
        StockFarmaciaDTO dto = new StockFarmaciaDTO();
        dto.setId(i.getId());
        dto.setProductoId(i.getProductoId());
        dto.setCantidad(i.getCantidad());
        return dto;
    }
}