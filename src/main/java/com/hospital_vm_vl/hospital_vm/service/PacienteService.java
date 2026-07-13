package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.PacienteDTO;
import com.hospital_vm_vl.hospital_vm.model.Paciente;
import com.hospital_vm_vl.hospital_vm.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<PacienteDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PacienteDTO findById(Long id) {
        Paciente c = repository.findById(id).orElseThrow(() -> {
                    log.warn("Paciente no encontrado con ID: {}", id);
                    return new RuntimeException("Paciente no encontrado con ID: " + id);
                });
        return toDTO(c);
    }

    @Transactional
    public PacienteDTO save(PacienteDTO dto) {
        Paciente c = new Paciente(null, dto.getNombre(), dto.getEmail(), dto.getTelefono());
        Paciente saved = repository.save(c);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public PacienteDTO update(Long id, PacienteDTO dto) {
        Paciente c = repository.findById(id).orElseThrow(() -> {
                    log.warn("Paciente no encontrado con ID: {}", id);
                    return new RuntimeException("Paciente no encontrado con ID: " + id);
                });
        c.setNombre(dto.getNombre());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
        Paciente updated = repository.save(c);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Paciente no encontrado con ID: {}", id);
            throw new RuntimeException("Paciente no encontrado");
        }
        repository.deleteById(id);
    }

    private PacienteDTO toDTO(Paciente c) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setEmail(c.getEmail());
        dto.setTelefono(c.getTelefono());
        return dto;
    }
}