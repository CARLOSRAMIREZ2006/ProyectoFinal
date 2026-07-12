package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.PagoDTO;
import com.hospital_vm_vl.hospital_vm.model.Pago;
import com.hospital_vm_vl.hospital_vm.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService {
    @Autowired
    private PagoRepository repository;

    public List<PagoDTO> findAll() {
        return repository.findAll().stream().map(p -> {
            PagoDTO dto = new PagoDTO();
            dto.setId(p.getId());
            dto.setVentaId(p.getVentaId());
            dto.setMonto(p.getMonto());
            dto.setMetodoPago(p.getMetodoPago());
            return dto;
        }).collect(Collectors.toList());
    }

    public PagoDTO save(PagoDTO dto) {
        Pago p = new Pago(null, dto.getVentaId(), dto.getMonto(), dto.getMetodoPago());
        Pago saved = repository.save(p);
        dto.setId(saved.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}