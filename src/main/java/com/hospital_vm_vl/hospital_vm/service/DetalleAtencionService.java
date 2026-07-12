package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.DetalleAtencionDTO;
import com.hospital_vm_vl.hospital_vm.model.DetalleAtencion;
import com.hospital_vm_vl.hospital_vm.repository.DetalleAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleAtencionService {
    @Autowired
    private DetalleAtencionRepository repository;

    public List<DetalleAtencionDTO> findAll() {
        return repository.findAll().stream().map(d -> {
            DetalleAtencionDTO dto = new DetalleAtencionDTO();
            dto.setId(d.getId());
            dto.setVentaId(d.getVentaId());
            dto.setProductoId(d.getProductoId());
            dto.setCantidad(d.getCantidad());
            dto.setPrecioUnitario(d.getPrecioUnitario());
            return dto;
        }).collect(Collectors.toList());
    }

    public DetalleAtencionDTO save(DetalleAtencionDTO dto) {
        DetalleAtencion d = new DetalleAtencion(null, dto.getVentaId(), dto.getProductoId(), dto.getCantidad(), dto.getPrecioUnitario());
        DetalleAtencion saved = repository.save(d);
        dto.setId(saved.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}