package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.VentaDTO;
import com.hospital_vm_vl.hospital_vm.model.Venta;
import com.hospital_vm_vl.hospital_vm.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {
    @Autowired
    private VentaRepository repository;

    public List<VentaDTO> findAll() {
        return repository.findAll().stream().map(v -> {
            VentaDTO dto = new VentaDTO();
            dto.setId(v.getId());
            dto.setClienteId(v.getClienteId());
            dto.setFecha(v.getFecha());
            dto.setTotal(v.getTotal());
            return dto;
        }).collect(Collectors.toList());
    }

    public VentaDTO save(VentaDTO dto) {
        Venta v = new Venta(null, dto.getClienteId(), LocalDateTime.now(), dto.getTotal());
        Venta saved = repository.save(v);
        dto.setId(saved.getId());
        dto.setFecha(saved.getFecha());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}