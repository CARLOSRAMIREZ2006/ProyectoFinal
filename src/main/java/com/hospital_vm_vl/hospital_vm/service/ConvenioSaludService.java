package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ConvenioSaludDTO;
import com.hospital_vm_vl.hospital_vm.model.ConvenioSalud;
import com.hospital_vm_vl.hospital_vm.repository.ConvenioSaludRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ConvenioSaludService {
    private static final Logger log = LoggerFactory.getLogger(ConvenioSaludService.class);

    @Autowired
    private ConvenioSaludRepository repository;

    public List<ConvenioSaludDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ConvenioSaludDTO findById(Long id) {
        ConvenioSalud c = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Convenio no encontrado con ID: {}", id);
                    return new RuntimeException("Convenio no encontrado con ID: " + id);
                });
        return toDTO(c);
    }

    @Transactional
    public ConvenioSaludDTO save(ConvenioSaludDTO dto) {
        ConvenioSalud p = new ConvenioSalud(null, dto.getNombre(), dto.getDescuento(), dto.getProductoId());
        ConvenioSalud saved = repository.save(p);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public ConvenioSaludDTO update(Long id, ConvenioSaludDTO dto) {
        ConvenioSalud c = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Convenio no encontrado con ID: {}", id);
                    return new RuntimeException("Convenio no encontrado con ID: " + id);
                });
        c.setNombre(dto.getNombre());
        c.setDescuento(dto.getDescuento());
        c.setProductoId(dto.getProductoId());
        ConvenioSalud updated = repository.save(c);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Convenio no encontrado con ID: {}", id);
            throw new RuntimeException("Convenio no encontrado");
        }
        repository.deleteById(id);
    }

    private ConvenioSaludDTO toDTO(ConvenioSalud c) {
        ConvenioSaludDTO dto = new ConvenioSaludDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setDescuento(c.getDescuento());
        dto.setProductoId(c.getProductoId());
        return dto;
    }
}