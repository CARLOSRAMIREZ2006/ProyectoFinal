package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.PacienteDTO;
import com.hospital_vm_vl.hospital_vm.model.Paciente;
import com.hospital_vm_vl.hospital_vm.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public List<PacienteDTO> findAll() {
        return repository.findAll().stream().map(c -> {
            PacienteDTO dto = new PacienteDTO();
            dto.setId(c.getId());
            dto.setNombre(c.getNombre());
            dto.setEmail(c.getEmail());
            dto.setTelefono(c.getTelefono());
            return dto;
        }).collect(Collectors.toList());
    }

    public PacienteDTO findById(Long id) {
        Paciente c = repository.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        PacienteDTO dto = new PacienteDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setEmail(c.getEmail());
        dto.setTelefono(c.getTelefono());
        return dto;
    }

    public PacienteDTO save(PacienteDTO dto) {
        Paciente c = new Paciente(null, dto.getNombre(), dto.getEmail(), dto.getTelefono());
        Paciente saved = repository.save(c);
        dto.setId(saved.getId());
        return dto;
    }

    public PacienteDTO update(Long id, PacienteDTO dto) {
        Paciente c = repository.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        c.setNombre(dto.getNombre());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
        Paciente updated = repository.save(c);
        dto.setId(updated.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}