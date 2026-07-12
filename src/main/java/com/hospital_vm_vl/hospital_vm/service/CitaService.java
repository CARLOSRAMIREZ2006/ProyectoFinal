package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.CitaDTO;
import com.hospital_vm_vl.hospital_vm.model.Cita;
import com.hospital_vm_vl.hospital_vm.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {
    @Autowired
    private CitaRepository repository;

    public List<CitaDTO> findAll() {
        return repository.findAll().stream().map(v -> {
            CitaDTO dto = new CitaDTO();
            dto.setId(v.getId());
            dto.setClienteId(v.getClienteId());
            dto.setFecha(v.getFecha());
            dto.setTotal(v.getTotal());
            return dto;
        }).collect(Collectors.toList());
    }

    public CitaDTO save(CitaDTO dto) {
        Cita v = new Cita(null, dto.getClienteId(), LocalDateTime.now(), dto.getTotal());
        Cita saved = repository.save(v);
        dto.setId(saved.getId());
        dto.setFecha(saved.getFecha());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}