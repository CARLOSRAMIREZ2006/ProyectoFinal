package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.EntregaMedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.model.EntregaMedicamento;
import com.hospital_vm_vl.hospital_vm.repository.EntregaMedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregaMedicamentoService {
    @Autowired
    private EntregaMedicamentoRepository repository;

    public List<EntregaMedicamentoDTO> findAll() {
        return repository.findAll().stream().map(d -> {
            EntregaMedicamentoDTO dto = new EntregaMedicamentoDTO();
            dto.setId(d.getId());
            dto.setVentaId(d.getVentaId());
            dto.setDireccion(d.getDireccion());
            dto.setEstado(d.getEstado());
            return dto;
        }).collect(Collectors.toList());
    }

    public EntregaMedicamentoDTO save(EntregaMedicamentoDTO dto) {
        EntregaMedicamento d = new EntregaMedicamento(null, dto.getVentaId(), dto.getDireccion(), dto.getEstado());
        EntregaMedicamento saved = repository.save(d);
        dto.setId(saved.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}