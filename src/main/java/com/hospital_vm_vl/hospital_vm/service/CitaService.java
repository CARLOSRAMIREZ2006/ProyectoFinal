package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.CitaDTO;
import com.hospital_vm_vl.hospital_vm.model.Cita;
import org.springframework.transaction.annotation.Transactional;
import com.hospital_vm_vl.hospital_vm.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CitaService {

    @Autowired
    private CitaRepository repository;

    public List<CitaDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CitaDTO findById(Long id) {
        Cita c = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cita no encontrada con ID: {}", id);
                    return new RuntimeException("Cita no encontrada con ID: " + id);
                });
        return toDTO(c);
    }

    @Transactional
    public CitaDTO save(CitaDTO dto) {
        // Mapeo manual: Si quieres puedes usar MapStruct luego, pero esto funciona perfecto
        Cita c = new Cita(null, dto.getClienteId(), dto.getFecha(), dto.getTotal());
        Cita saved = repository.save(c);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public CitaDTO update(Long id, CitaDTO dto) {
        Cita c = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cita no encontrada con ID: {}", id);
                    return new RuntimeException("Cita no encontrada con ID: " + id);
                });
        c.setClienteId(dto.getClienteId());
        c.setFecha(dto.getFecha());
        c.setTotal(dto.getTotal());
        Cita updated = repository.save(c);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Cita no encontrada con ID: {}", id);
            throw new RuntimeException("Cita no encontrada");
        }
        repository.deleteById(id);
    }

    // Método de utilidad para evitar código repetido (Buenas prácticas)
    private CitaDTO toDTO(Cita c) {
        CitaDTO dto = new CitaDTO();
        dto.setId(c.getId());
        dto.setClienteId(c.getClienteId());
        dto.setFecha(c.getFecha());
        dto.setTotal(c.getTotal());
        return dto;
    }
}