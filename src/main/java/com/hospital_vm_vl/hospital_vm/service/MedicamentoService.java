package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.MedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.model.Medicamento;
import com.hospital_vm_vl.hospital_vm.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository repository;

    public List<MedicamentoDTO> findAll() {
        return repository.findAll().stream().map(p -> {
            MedicamentoDTO dto = new MedicamentoDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setPrecio(p.getPrecio());
            dto.setStock(p.getStock());
            return dto;
        }).collect(Collectors.toList());
    }

    public MedicamentoDTO findById(Long id) {
        Medicamento p = repository.findById(id).orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        return dto;
    }

    public MedicamentoDTO save(MedicamentoDTO dto) {
        Medicamento p = new Medicamento(null, dto.getNombre(), dto.getPrecio(), dto.getStock());
        Medicamento saved = repository.save(p);
        dto.setId(saved.getId());
        return dto;
    }

    public MedicamentoDTO update(Long id, MedicamentoDTO dto) {
        Medicamento p = repository.findById(id).orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        Medicamento updated = repository.save(p);
        dto.setId(updated.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}