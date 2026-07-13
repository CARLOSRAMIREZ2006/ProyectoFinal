package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.MedicamentoDTO;
import com.hospital_vm_vl.hospital_vm.model.Medicamento;
import com.hospital_vm_vl.hospital_vm.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository repository;

    public List<MedicamentoDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MedicamentoDTO findById(Long id) {
        Medicamento p = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Medicamento no encontrado con ID: {}", id);
                    return new RuntimeException("Medicamento no encontrado con ID: " + id);
                });
        return toDTO(p);
    }

    @Transactional
    public MedicamentoDTO save(MedicamentoDTO dto) {
        Medicamento p = new Medicamento(null, dto.getNombre(), dto.getPrecio(), dto.getStock());
        Medicamento saved = repository.save(p);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public MedicamentoDTO update(Long id, MedicamentoDTO dto) {
        Medicamento p = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Medicamento no encontrado con ID: {}", id);
                    return new RuntimeException("Medicamento no encontrado con ID: " + id);
                });
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        Medicamento updated = repository.save(p);
        return toDTO(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            log.warn("Medicamento no encontrado con ID: {}", id);
            throw new RuntimeException("Medicamento no encontrado");
        }
        repository.deleteById(id);
    }

    private MedicamentoDTO toDTO(Medicamento p) {
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        return dto;
    }
}