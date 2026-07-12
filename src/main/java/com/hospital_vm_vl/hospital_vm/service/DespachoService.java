package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.DespachoDTO;
import com.hospital_vm_vl.hospital_vm.model.Despacho;
import com.hospital_vm_vl.hospital_vm.repository.DespachoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespachoService {
    @Autowired
    private DespachoRepository repository;

    public List<DespachoDTO> findAll() {
        return repository.findAll().stream().map(d -> {
            DespachoDTO dto = new DespachoDTO();
            dto.setId(d.getId());
            dto.setVentaId(d.getVentaId());
            dto.setDireccion(d.getDireccion());
            dto.setEstado(d.getEstado());
            return dto;
        }).collect(Collectors.toList());
    }

    public DespachoDTO save(DespachoDTO dto) {
        Despacho d = new Despacho(null, dto.getVentaId(), dto.getDireccion(), dto.getEstado());
        Despacho saved = repository.save(d);
        dto.setId(saved.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}