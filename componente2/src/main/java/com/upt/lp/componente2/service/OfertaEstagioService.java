package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.OfertaEstagio;
import com.upt.lp.componente2.entity.Empresa;
import com.upt.lp.componente2.entity.AreaEstagio;
import com.upt.lp.componente2.entity.Curso;
import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.enums.StatusOferta;
import com.upt.lp.componente2.enums.TipoEstagio;
import com.upt.lp.componente2.repository.OfertaEstagioRepository;
import com.upt.lp.componente2.repository.EmpresaRepository;
import com.upt.lp.componente2.repository.AreaEstagioRepository;
import com.upt.lp.componente2.repository.CursoRepository;
import com.upt.lp.componente2.repository.CoordenadorRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class OfertaEstagioService {
    
    private final OfertaEstagioRepository ofertaRepository;
    private final EmpresaRepository empresaRepository;
    private final AreaEstagioRepository areaRepository;
    private final CursoRepository cursoRepository;
    private final CoordenadorRepository coordenadorRepository;
    
    public OfertaEstagioService(OfertaEstagioRepository ofertaRepository,
                               EmpresaRepository empresaRepository,
                               AreaEstagioRepository areaRepository,
                               CursoRepository cursoRepository,
                               CoordenadorRepository coordenadorRepository) {
        this.ofertaRepository = ofertaRepository;
        this.empresaRepository = empresaRepository;
        this.areaRepository = areaRepository;
        this.cursoRepository = cursoRepository;
        this.coordenadorRepository = coordenadorRepository;
    }
    
    public List<OfertaEstagio> getAllOfertas() {
        return ofertaRepository.findAll();
    }
    
    public OfertaEstagio getOfertaById(String id) {
        return ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada com ID: " + id));
    }
    
    public OfertaEstagio createOferta(OfertaEstagio oferta, String empresaId, 
                                     String areaId, String cursoId, String coordenadorId) {
        // Buscar empresa
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com ID: " + empresaId));
        oferta.setEmpresa(empresa);
        
        // Buscar área (opcional)
        if (areaId != null) {
            AreaEstagio area = areaRepository.findById(areaId)
                    .orElseThrow(() -> new RuntimeException("Área não encontrada com ID: " + areaId));
            oferta.setArea(area);
        }
        
        // Buscar curso (opcional)
        if (cursoId != null) {
            Curso curso = cursoRepository.findById(cursoId)
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + cursoId));
            oferta.setCurso(curso);
        }
        
        // Buscar coordenador (opcional)
        if (coordenadorId != null) {
            Coordenador coordenador = coordenadorRepository.findById(coordenadorId)
                    .orElseThrow(() -> new RuntimeException("Coordenador não encontrado com ID: " + coordenadorId));
            oferta.setCoordenadorResponsavel(coordenador);
        }
        
        return ofertaRepository.save(oferta);
    }
    
    public OfertaEstagio updateOferta(String id, OfertaEstagio ofertaAtualizada) {
        OfertaEstagio ofertaExistente = getOfertaById(id);
        
        ofertaExistente.setTitulo(ofertaAtualizada.getTitulo());
        ofertaExistente.setDescricao(ofertaAtualizada.getDescricao());
        ofertaExistente.setTipo(ofertaAtualizada.getTipo());
        ofertaExistente.setLocalizacao(ofertaAtualizada.getLocalizacao());
        ofertaExistente.setDuracaoMeses(ofertaAtualizada.getDuracaoMeses());
        ofertaExistente.setRequisitos(ofertaAtualizada.getRequisitos());
        ofertaExistente.setDataInicio(ofertaAtualizada.getDataInicio());
        ofertaExistente.setDataFim(ofertaAtualizada.getDataFim());
        ofertaExistente.setDataLimiteInscricao(ofertaAtualizada.getDataLimiteInscricao());
        ofertaExistente.setNumeroVagas(ofertaAtualizada.getNumeroVagas());
        
        // Atualizar empresa se fornecida
        if (ofertaAtualizada.getEmpresa() != null) {
            ofertaExistente.setEmpresa(ofertaAtualizada.getEmpresa());
        }
        
        // Atualizar área se fornecida
        if (ofertaAtualizada.getArea() != null) {
            ofertaExistente.setArea(ofertaAtualizada.getArea());
        }
        
        // Atualizar curso se fornecido
        if (ofertaAtualizada.getCurso() != null) {
            ofertaExistente.setCurso(ofertaAtualizada.getCurso());
        }
        
        // Atualizar coordenador se fornecido
        if (ofertaAtualizada.getCoordenadorResponsavel() != null) {
            ofertaExistente.setCoordenadorResponsavel(ofertaAtualizada.getCoordenadorResponsavel());
        }
        
        return ofertaRepository.save(ofertaExistente);
    }
    
    public void aprovarOferta(String id) {
        OfertaEstagio oferta = getOfertaById(id);
        oferta.aprovar();
        ofertaRepository.save(oferta);
    }
    
    public void rejeitarOferta(String id) {
        OfertaEstagio oferta = getOfertaById(id);
        oferta.rejeitar();
        ofertaRepository.save(oferta);
    }
    
    public void encerrarOferta(String id) {
        OfertaEstagio oferta = getOfertaById(id);
        oferta.encerrar();
        ofertaRepository.save(oferta);
    }
    
    public void deleteOferta(String id) {
        if (!ofertaRepository.existsById(id)) {
            throw new RuntimeException("Oferta não encontrada com ID: " + id);
        }
        ofertaRepository.deleteById(id);
    }
    
    public List<OfertaEstagio> getOfertasByEmpresa(String empresaId) {
        return ofertaRepository.findByEmpresaId(empresaId);
    }
    
    public List<OfertaEstagio> getOfertasByCurso(String cursoId) {
        return ofertaRepository.findByCursoId(cursoId);
    }
    
    public List<OfertaEstagio> getOfertasByArea(String areaId) {
        return ofertaRepository.findByAreaId(areaId);
    }
    
    public List<OfertaEstagio> getOfertasByStatus(StatusOferta status) {
        return ofertaRepository.findByStatus(status);
    }
    
    public List<OfertaEstagio> getOfertasByTipo(TipoEstagio tipo) {
        return ofertaRepository.findByTipo(tipo);
    }
    
    public List<OfertaEstagio> getOfertasByCoordenador(String coordenadorId) {
        return ofertaRepository.findByCoordenadorResponsavelId(coordenadorId);
    }
    
    public List<OfertaEstagio> searchOfertasByTitulo(String titulo) {
        return ofertaRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    public List<OfertaEstagio> getOfertasAtivas() {
        LocalDate hoje = LocalDate.now();
        return ofertaRepository.findByStatusAndDataLimiteInscricaoAfter(StatusOferta.APROVADO, hoje);
    }
    
    public long countOfertasByStatus(StatusOferta status) {
        return ofertaRepository.countByStatus(status);
    }
}
