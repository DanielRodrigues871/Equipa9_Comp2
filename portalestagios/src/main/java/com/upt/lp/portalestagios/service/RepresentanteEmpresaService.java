package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.representante.RepresentanteEmpresaRequestDTO;
import com.upt.lp.portalestagios.entity.Empresa;
import com.upt.lp.portalestagios.entity.RepresentanteEmpresa;
import com.upt.lp.portalestagios.mapper.RepresentanteEmpresaMapper;
import com.upt.lp.portalestagios.repository.EmpresaRepository;
import com.upt.lp.portalestagios.repository.RepresentanteEmpresaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RepresentanteEmpresaService {

    private final RepresentanteEmpresaRepository representanteRepository;
    private final EmpresaRepository empresaRepository;

    public RepresentanteEmpresaService(RepresentanteEmpresaRepository representanteRepository,
                                       EmpresaRepository empresaRepository) {
        this.representanteRepository = representanteRepository;
        this.empresaRepository = empresaRepository;
    }

    public List<RepresentanteEmpresa> findAll() {
        return representanteRepository.findAll();
    }

    public Optional<RepresentanteEmpresa> findById(UUID id) {
        return representanteRepository.findById(id);
    }

    public RepresentanteEmpresa create(RepresentanteEmpresaRequestDTO dto) {

        Empresa emp = empresaRepository.findById(UUID.fromString(dto.getEmpresaId()))
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        RepresentanteEmpresa novo = RepresentanteEmpresaMapper.toEntity(dto, emp);

        return representanteRepository.save(novo);
    }

    public void delete(UUID id) {
        representanteRepository.deleteById(id);
    }
}
