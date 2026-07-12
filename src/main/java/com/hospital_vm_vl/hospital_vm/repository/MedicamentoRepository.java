package com.hospital_vm_vl.hospital_vm.repository;

import com.hospital_vm_vl.hospital_vm.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {}