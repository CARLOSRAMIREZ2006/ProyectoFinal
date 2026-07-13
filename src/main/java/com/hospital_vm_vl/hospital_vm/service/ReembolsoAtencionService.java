package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ReembolsoAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.ReembolsoAtencion;
import com.hospital_vm_vl.hospital_vm.repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReembolsoAtencionService {

    @Autowired
    private DevolucionRepository repository;

    public List<ReembolsoAtencionDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ReembolsoAtencionDTO findById(Long id) {
        ReembolsoAtencion d = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Reembolso no encontrado con ID: {}", id);
                    return new RuntimeException("Reembolso no encontrado con ID: " + id);
                });
        return toDTO(d);
    }

    @Transactional
    public ReembolsoAtencionDTO save(ReembolsoAtencionDTO dto) {
        ReembolsoAtencion d = new ReembolsoAtencion(null, dto.getVentaId(), dto.getMotivo(), LocalDateTime.now());
        ReembolsoAtencion saved = repository.save(d);
        return toDTO(saved);
    }

    @Transactional
    public ReembolsoAtencionDTO update(Long id, ReembolsoAtencionDTO dto) {
        ReembolsoAtencion d = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Reembolso no encontrado con ID: {}", id);
                    return new RuntimeException("Reembolso no encontrado con ID: " + id);
                });
        d.setVentaId(dto.getVentaId());
        d.setMotivo(dto.getMotivo());
        ReembolsoAtencion updated = repository.save(d);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Reembolso no encontrado con ID: {}", id);
            throw new RuntimeException("Reembolso no encontrado");
        }
        repository.deleteById(id);
    }

    private ReembolsoAtencionDTO toDTO(ReembolsoAtencion d) {
        ReembolsoAtencionDTO dto = new ReembolsoAtencionDTO();
        dto.setId(d.getId());
        dto.setVentaId(d.getVentaId());
        dto.setMotivo(d.getMotivo());
        dto.setFechaDevolucion(d.getFechaDevolucion());
        return dto;
    }
}