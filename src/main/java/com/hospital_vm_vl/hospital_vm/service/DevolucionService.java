package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.DevolucionDTO;
import com.hospital_vm_vl.hospital_vm.model.Devolucion;
import com.hospital_vm_vl.hospital_vm.repository.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevolucionService {
    @Autowired
    private DevolucionRepository repository;

    public List<DevolucionDTO> findAll() {
        return repository.findAll().stream().map(d -> {
            DevolucionDTO dto = new DevolucionDTO();
            dto.setId(d.getId());
            dto.setVentaId(d.getVentaId());
            dto.setMotivo(d.getMotivo());
            dto.setFechaDevolucion(d.getFechaDevolucion());
            return dto;
        }).collect(Collectors.toList());
    }

    public DevolucionDTO save(DevolucionDTO dto) {
        Devolucion d = new Devolucion(null, dto.getVentaId(), dto.getMotivo(), LocalDateTime.now());
        Devolucion saved = repository.save(d);
        dto.setId(saved.getId());
        dto.setFechaDevolucion(saved.getFechaDevolucion());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}