package com.hospital_vm_vl.hospital_vm.repository;

import com.hospital_vm_vl.hospital_vm.model.DetalleAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleAtencionRepository extends JpaRepository<DetalleAtencion, Long> {}