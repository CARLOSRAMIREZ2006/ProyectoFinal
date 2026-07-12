package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ConvenioSaludDTO;
import com.hospital_vm_vl.hospital_vm.model.ConvenioSalud;
import com.hospital_vm_vl.hospital_vm.repository.ConvenioSaludRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvenioSaludService {
    @Autowired
    private ConvenioSaludRepository repository;

    public List<ConvenioSaludDTO> findAll() {
        return repository.findAll().stream().map(p -> {
            ConvenioSaludDTO dto = new ConvenioSaludDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setDescuento(p.getDescuento());
            dto.setProductoId(p.getProductoId());
            return dto;
        }).collect(Collectors.toList());
    }

    public ConvenioSaludDTO save(ConvenioSaludDTO dto) {
        ConvenioSalud p = new ConvenioSalud(null, dto.getNombre(), dto.getDescuento(), dto.getProductoId());
        ConvenioSalud saved = repository.save(p);
        dto.setId(saved.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}