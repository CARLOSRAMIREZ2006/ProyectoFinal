package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ReembolsoAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.ReembolsoAtencion;
import com.hospital_vm_vl.hospital_vm.repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReembolsoAtencionService {
    @Autowired
    private DevolucionRepository repository;

    public List<ReembolsoAtencionDTO> findAll() {
        return repository.findAll().stream().map(d -> {
            ReembolsoAtencionDTO dto = new ReembolsoAtencionDTO();
            dto.setId(d.getId());
            dto.setVentaId(d.getVentaId());
            dto.setMotivo(d.getMotivo());
            dto.setFechaDevolucion(d.getFechaDevolucion());
            return dto;
        }).collect(Collectors.toList());
    }

    public ReembolsoAtencionDTO save(ReembolsoAtencionDTO dto) {
        ReembolsoAtencion d = new ReembolsoAtencion(null, dto.getVentaId(), dto.getMotivo(), LocalDateTime.now());
        ReembolsoAtencion saved = repository.save(d);
        dto.setId(saved.getId());
        dto.setFechaDevolucion(saved.getFechaDevolucion());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}