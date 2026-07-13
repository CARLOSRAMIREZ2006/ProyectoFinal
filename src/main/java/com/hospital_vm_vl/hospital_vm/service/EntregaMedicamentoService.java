package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.EntregaMedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.model.EntregaMedicamento;
import com.hospital_vm_vl.hospital_vm.repository.EntregaMedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EntregaMedicamentoService {
    private static final Logger log = LoggerFactory.getLogger(EntregaMedicamentoService.class);

    @Autowired
    private EntregaMedicamentoRepository repository;

    public List<EntregaMedicamentoDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EntregaMedicamentoDTO findById(Long id) {
        EntregaMedicamento d = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Entrega no encontrada con ID: {}", id);
                    return new RuntimeException("Entrega no encontrada con ID: " + id);
                });
        return toDTO(d);
    }

    @Transactional
    public EntregaMedicamentoDTO save(EntregaMedicamentoDTO dto) {
        EntregaMedicamento d = new EntregaMedicamento(null, dto.getVentaId(), dto.getDireccion(), dto.getEstado());
        EntregaMedicamento saved = repository.save(d);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public EntregaMedicamentoDTO update(Long id, EntregaMedicamentoDTO dto) {
        EntregaMedicamento d = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Entrega no encontrada con ID: {}", id);
                    return new RuntimeException("Entrega no encontrada con ID: " + id);
                });
        d.setVentaId(dto.getVentaId());
        d.setDireccion(dto.getDireccion());
        d.setEstado(dto.getEstado());
        EntregaMedicamento updated = repository.save(d);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Entrega no encontrada con ID: {}", id);
            throw new RuntimeException("Entrega no encontrada");
        }
        repository.deleteById(id);
    }

    private EntregaMedicamentoDTO toDTO(EntregaMedicamento d) {
        EntregaMedicamentoDTO dto = new EntregaMedicamentoDTO();
        dto.setId(d.getId());
        dto.setVentaId(d.getVentaId());
        dto.setDireccion(d.getDireccion());
        dto.setEstado(d.getEstado());
        return dto;
    }
}