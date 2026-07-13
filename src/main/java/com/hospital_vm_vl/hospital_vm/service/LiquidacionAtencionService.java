package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.LiquidacionAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.LiquidacionAtencion;
import com.hospital_vm_vl.hospital_vm.repository.LiquidacionAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiquidacionAtencionService {
    @Autowired
    private LiquidacionAtencionRepository repository;

    public List<LiquidacionAtencionDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public LiquidacionAtencionDTO findById(Long id) {
        LiquidacionAtencion f = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liquidación no encontrada con ID: " + id));
        return toDTO(f);
    }

    @Transactional
    public LiquidacionAtencionDTO save(LiquidacionAtencionDTO dto) {
        LiquidacionAtencion f = new LiquidacionAtencion(null, dto.getVentaId(), LocalDateTime.now(), dto.getMontoTotal());
        LiquidacionAtencion saved = repository.save(f);
        return toDTO(saved);
    }

    @Transactional
    public LiquidacionAtencionDTO update(Long id, LiquidacionAtencionDTO dto) {
        LiquidacionAtencion f = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liquidación no encontrada con ID: " + id));
        f.setVentaId(dto.getVentaId());
        f.setMontoTotal(dto.getMontoTotal());
        LiquidacionAtencion updated = repository.save(f);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Liquidación no encontrada");
        repository.deleteById(id);
    }

    private LiquidacionAtencionDTO toDTO(LiquidacionAtencion f) {
        LiquidacionAtencionDTO dto = new LiquidacionAtencionDTO();
        dto.setId(f.getId());
        dto.setVentaId(f.getVentaId());
        dto.setFechaEmision(f.getFechaEmision());
        dto.setMontoTotal(f.getMontoTotal());
        return dto;
    }
}