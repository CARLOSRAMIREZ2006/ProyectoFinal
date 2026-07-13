package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.DetalleAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.DetalleAtencion;
import com.hospital_vm_vl.hospital_vm.repository.DetalleAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleAtencionService {
    @Autowired
    private DetalleAtencionRepository repository;

    public List<DetalleAtencionDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public DetalleAtencionDTO findById(Long id) {
        DetalleAtencion d = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con ID: " + id));
        return toDTO(d);
    }

    @Transactional
    public DetalleAtencionDTO save(DetalleAtencionDTO dto) {
        DetalleAtencion d = new DetalleAtencion(null, dto.getVentaId(), dto.getProductoId(), dto.getCantidad(), dto.getPrecioUnitario());
        DetalleAtencion saved = repository.save(d);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public DetalleAtencionDTO update(Long id, DetalleAtencionDTO dto) {
        DetalleAtencion d = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con ID: " + id));
        d.setVentaId(dto.getVentaId());
        d.setProductoId(dto.getProductoId());
        d.setCantidad(dto.getCantidad());
        d.setPrecioUnitario(dto.getPrecioUnitario());
        DetalleAtencion updated = repository.save(d);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Detalle no encontrado");
        repository.deleteById(id);
    }

    private DetalleAtencionDTO toDTO(DetalleAtencion d) {
        DetalleAtencionDTO dto = new DetalleAtencionDTO();
        dto.setId(d.getId());
        dto.setVentaId(d.getVentaId());
        dto.setProductoId(d.getProductoId());
        dto.setCantidad(d.getCantidad());
        dto.setPrecioUnitario(d.getPrecioUnitario());
        return dto;
    }
}