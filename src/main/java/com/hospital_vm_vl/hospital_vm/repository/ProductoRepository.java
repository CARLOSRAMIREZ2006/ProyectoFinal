package com.hospital_vm_vl.hospital_vm.repository;

import com.hospital_vm_vl.hospital_vm.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {}