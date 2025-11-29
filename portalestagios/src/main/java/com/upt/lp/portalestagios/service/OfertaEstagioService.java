package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.oferta.OfertaEstagioRequestDTO;
import com.upt.lp.portalestagios.dto.oferta.OfertaEstagioResponseDTO;
import com.upt.lp.portalestagios.entity.*;
import com.upt.lp.portalestagios.enums.StatusOferta;
import com.upt.lp.portalestagios.mapper.OfertaEstagioMapper;
import com.upt.lp.portalestagios.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OfertaEstagioService {

    private final OfertaEstagioRepository ofertaRepo;
    private final EmpresaRepository empresaRepo;
    private final CoordenadorRepository coordRepo;
    private final CursoRepository cursoRepo;
    private final AreaEstagioRepository areaRepo;

    public OfertaEstagioService(
            OfertaEstagioRepository ofertaRepo,
            EmpresaRepository empresaRepo,
            CoordenadorRepository coordRepo,
            CursoRepository cursoRepo,
            AreaEstagioRepository areaRepo
    ) {
        this.ofertaRepo = ofertaRepo;
        this.empresaRepo = empresaRepo;
        this.coordRepo = coordRepo;
        this.cursoRepo = cursoRepo;
        this.areaRepo = areaRepo;
    }

    public List<OfertaEstagioResponseDTO> listar() {
        return ofertaRepo.findAll()
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .toList();
    }

    public OfertaEstagioResponseDTO buscar(String id) {
        UUID uuid = UUID.fromString(id);
        OfertaEstagio o = ofertaRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));
        return OfertaEstagioMapper.toDTO(o);
    }

    public OfertaEstagioResponseDTO criar(OfertaEstagioRequestDTO dto) {

        Empresa emp = empresaRepo.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        Coordenador coord = null;
        if (dto.getCoordenadorId() != null)
            coord = coordRepo.findById(dto.getCoordenadorId())
                    .orElseThrow(() -> new RuntimeException("Coordenador não encontrado"));

        Curso curso = null;
        if (dto.getCursoId() != null)
            curso = cursoRepo.findById(dto.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        AreaEstagio area = null;
        if (dto.getAreaId() != null)
            area = areaRepo.findById(dto.getAreaId())
                    .orElseThrow(() -> new RuntimeException("Área não encontrada"));

        OfertaEstagio nova = OfertaEstagioMapper.toEntity(dto, emp, coord, curso, area);
        OfertaEstagio salva = ofertaRepo.save(nova);

        return OfertaEstagioMapper.toDTO(salva);
    }

    public OfertaEstagioResponseDTO atualizar(String id, OfertaEstagioRequestDTO dto) {
        UUID uuid = UUID.fromString(id);

        OfertaEstagio o = ofertaRepo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));

        o.setTitulo(dto.getTitulo());
        o.setDescricao(dto.getDescricao());
        o.setNumeroVagas(dto.getNumeroVagas());
        o.setDataInicio(dto.getDataInicio());
        o.setDataFim(dto.getDataFim());
        o.setLocalizacao(dto.getLocalizacao());
        o.setRequisitos(dto.getRequisitos());

        return OfertaEstagioMapper.toDTO(ofertaRepo.save(o));
    }

    public OfertaEstagioResponseDTO aprovar(String id) {
        UUID uuid = UUID.fromString(id);
        OfertaEstagio o = ofertaRepo.findById(uuid)
                .orElseThrow();

        o.setStatus(StatusOferta.APROVADO);
        return OfertaEstagioMapper.toDTO(ofertaRepo.save(o));
    }

    public OfertaEstagioResponseDTO rejeitar(String id) {
        UUID uuid = UUID.fromString(id);
        OfertaEstagio o = ofertaRepo.findById(uuid)
                .orElseThrow();

        o.setStatus(StatusOferta.REJEITADO);
        return OfertaEstagioMapper.toDTO(ofertaRepo.save(o));
    }

    public void apagar(String id) {
        ofertaRepo.deleteById(UUID.fromString(id));
    }
}
