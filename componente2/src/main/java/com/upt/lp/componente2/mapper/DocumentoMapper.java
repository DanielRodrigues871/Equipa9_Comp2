package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.DocumentoDTO;
import com.upt.lp.componente2.entity.Documento;

public class DocumentoMapper {
    
    public static DocumentoDTO toDTO(Documento documento) {
        if (documento == null) return null;
        
        DocumentoDTO dto = new DocumentoDTO(
            documento.getId(),
            documento.getNomeEmpresa(),
            documento.getContactoEmpresa(),
            documento.getObjetivoEstagio(),
            documento.getDataUpload()
        );
        
        if (documento.getEstudante() != null) {
            dto.setEstudanteId(documento.getEstudante().getId());
            dto.setEstudanteNome(documento.getEstudante().getNome());
        }
        
        return dto;
    }
    
    public static Documento toEntity(DocumentoDTO dto) {
        if (dto == null) return null;
        
        Documento documento = new Documento();
        documento.setId(dto.getId());
        documento.setNomeEmpresa(dto.getNomeEmpresa());
        documento.setContactoEmpresa(dto.getContactoEmpresa());
        documento.setObjetivoEstagio(dto.getObjetivoEstagio());
        documento.setDataUpload(dto.getDataUpload());
        
        // Estudante será definido no Service
        return documento;
    }
}
