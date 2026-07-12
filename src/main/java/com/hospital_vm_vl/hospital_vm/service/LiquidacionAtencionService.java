package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.LiquidacionAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.LiquidacionAtencion;
import com.hospital_vm_vl.hospital_vm.repository.LiquidacionAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiquidacionAtencionService {
    @Autowired
    private LiquidacionAtencionRepository repository;

    public List<LiquidacionAtencionDTO> findAll() {
        return repository.findAll().stream().map(f -> {
            LiquidacionAtencionDTO dto = new LiquidacionAtencionDTO();
            dto.setId(f.getId());
            dto.setVentaId(f.getVentaId());
            dto.setFechaEmision(f.getFechaEmision());
            dto.setMontoTotal(f.getMontoTotal());
            return dto;
        }).collect(Collectors.toList());
    }

    public LiquidacionAtencionDTO save(LiquidacionAtencionDTO dto) {
        LiquidacionAtencion f = new LiquidacionAtencion(null, dto.getVentaId(), LocalDateTime.now(), dto.getMontoTotal());
        LiquidacionAtencion saved = repository.save(f);
        dto.setId(saved.getId());
        dto.setFechaEmision(saved.getFechaEmision());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}