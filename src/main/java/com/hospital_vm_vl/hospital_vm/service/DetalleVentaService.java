package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.DetalleVentaDTO;
import com.hospital_vm_vl.hospital_vm.model.DetalleVenta;
import com.hospital_vm_vl.hospital_vm.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaService {
    @Autowired
    private DetalleVentaRepository repository;

    public List<DetalleVentaDTO> findAll() {
        return repository.findAll().stream().map(d -> {
            DetalleVentaDTO dto = new DetalleVentaDTO();
            dto.setId(d.getId());
            dto.setVentaId(d.getVentaId());
            dto.setProductoId(d.getProductoId());
            dto.setCantidad(d.getCantidad());
            dto.setPrecioUnitario(d.getPrecioUnitario());
            return dto;
        }).collect(Collectors.toList());
    }

    public DetalleVentaDTO save(DetalleVentaDTO dto) {
        DetalleVenta d = new DetalleVenta(null, dto.getVentaId(), dto.getProductoId(), dto.getCantidad(), dto.getPrecioUnitario());
        DetalleVenta saved = repository.save(d);
        dto.setId(saved.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}