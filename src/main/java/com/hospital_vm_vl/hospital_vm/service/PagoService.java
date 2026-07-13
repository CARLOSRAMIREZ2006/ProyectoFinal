package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.PagoDTO;
import com.hospital_vm_vl.hospital_vm.model.Pago;
import com.hospital_vm_vl.hospital_vm.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PagoService {

    @Autowired
    private PagoRepository repository;

    public List<PagoDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PagoDTO findById(Long id) {
        Pago p = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Pago no encontrado con ID: {}", id);
                    return new RuntimeException("Pago no encontrado con ID: " + id);
                });
        return toDTO(p);
    }

    @Transactional
    public PagoDTO save(PagoDTO dto) {
        Pago p = new Pago(null, dto.getVentaId(), dto.getMonto(), dto.getMetodoPago());
        Pago saved = repository.save(p);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public PagoDTO update(Long id, PagoDTO dto) {
        Pago p = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Pago no encontrado con ID: {}", id);
                    return new RuntimeException("Pago no encontrado con ID: " + id);
                });
        p.setVentaId(dto.getVentaId());
        p.setMonto(dto.getMonto());
        p.setMetodoPago(dto.getMetodoPago());
        Pago updated = repository.save(p);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Pago no encontrado con ID: {}", id);
            throw new RuntimeException("Pago no encontrado");
        }
        repository.deleteById(id);
    }

    private PagoDTO toDTO(Pago p) {
        PagoDTO dto = new PagoDTO();
        dto.setId(p.getId());
        dto.setVentaId(p.getVentaId());
        dto.setMonto(p.getMonto());
        dto.setMetodoPago(p.getMetodoPago());
        return dto;
    }
}