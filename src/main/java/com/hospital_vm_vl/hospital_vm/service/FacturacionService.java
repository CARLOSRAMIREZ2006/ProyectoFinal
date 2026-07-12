package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.FacturacionDTO;
import com.hospital_vm_vl.hospital_vm.model.Facturacion;
import com.hospital_vm_vl.hospital_vm.repository.FacturacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturacionService {
    @Autowired
    private FacturacionRepository repository;

    public List<FacturacionDTO> findAll() {
        return repository.findAll().stream().map(f -> {
            FacturacionDTO dto = new FacturacionDTO();
            dto.setId(f.getId());
            dto.setVentaId(f.getVentaId());
            dto.setFechaEmision(f.getFechaEmision());
            dto.setMontoTotal(f.getMontoTotal());
            return dto;
        }).collect(Collectors.toList());
    }

    public FacturacionDTO save(FacturacionDTO dto) {
        Facturacion f = new Facturacion(null, dto.getVentaId(), LocalDateTime.now(), dto.getMontoTotal());
        Facturacion saved = repository.save(f);
        dto.setId(saved.getId());
        dto.setFechaEmision(saved.getFechaEmision());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}