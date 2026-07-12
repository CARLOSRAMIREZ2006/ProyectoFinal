package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.PromocionDTO;
import com.hospital_vm_vl.hospital_vm.model.Promocion;
import com.hospital_vm_vl.hospital_vm.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromocionService {
    @Autowired
    private PromocionRepository repository;

    public List<PromocionDTO> findAll() {
        return repository.findAll().stream().map(p -> {
            PromocionDTO dto = new PromocionDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setDescuento(p.getDescuento());
            dto.setProductoId(p.getProductoId());
            return dto;
        }).collect(Collectors.toList());
    }

    public PromocionDTO save(PromocionDTO dto) {
        Promocion p = new Promocion(null, dto.getNombre(), dto.getDescuento(), dto.getProductoId());
        Promocion saved = repository.save(p);
        dto.setId(saved.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}