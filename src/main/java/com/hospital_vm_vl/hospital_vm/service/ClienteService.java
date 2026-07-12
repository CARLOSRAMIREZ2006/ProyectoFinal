package com.hospital_vm_vl.hospital_vm.service;

import com.hospital_vm_vl.hospital_vm.dto.ClienteDTO;
import com.hospital_vm_vl.hospital_vm.model.Cliente;
import com.hospital_vm_vl.hospital_vm.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<ClienteDTO> findAll() {
        return repository.findAll().stream().map(c -> {
            ClienteDTO dto = new ClienteDTO();
            dto.setId(c.getId());
            dto.setNombre(c.getNombre());
            dto.setEmail(c.getEmail());
            dto.setTelefono(c.getTelefono());
            return dto;
        }).collect(Collectors.toList());
    }

    public ClienteDTO findById(Long id) {
        Cliente c = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setEmail(c.getEmail());
        dto.setTelefono(c.getTelefono());
        return dto;
    }

    public ClienteDTO save(ClienteDTO dto) {
        Cliente c = new Cliente(null, dto.getNombre(), dto.getEmail(), dto.getTelefono());
        Cliente saved = repository.save(c);
        dto.setId(saved.getId());
        return dto;
    }

    public ClienteDTO update(Long id, ClienteDTO dto) {
        Cliente c = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        c.setNombre(dto.getNombre());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
        Cliente updated = repository.save(c);
        dto.setId(updated.getId());
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}