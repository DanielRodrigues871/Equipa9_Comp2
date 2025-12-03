package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.DocumentoDTO;
import com.upt.lp.componente2.entity.Documento;
import com.upt.lp.componente2.entity.Estudante;

public final class DocumentoMapper {

    private DocumentoMapper() {
    }

    public static DocumentoDTO toDTO(Documento entity) {
        if (entity == null) {
            return null;
        }

        DocumentoDTO dto = new DocumentoDTO();
        dto.setId(entity.getId());
        dto.setNomeEmpresa(entity.getNomeEmpresa());
        dto.setContactoEmpresa(entity.getContactoEmpresa());
        dto.setObjetivoEstagio(entity.getObjetivoEstagio());
        dto.setDataUpload(entity.getDataUpload());

        Estudante e = entity.getEstudante();
        if (e != null) {
            dto.setEstudanteId(e.getId());
            dto.setEstudanteNome(e.getNome());
        }

        return dto;
    }

    public static Documento toEntity(DocumentoDTO dto) {
        if (dto == null) {
            return null;
        }

        Documento d = new Documento();
        d.setNomeEmpresa(dto.getNomeEmpresa());
        d.setContactoEmpresa(dto.getContactoEmpresa());
        d.setObjetivoEstagio(dto.getObjetivoEstagio());
        // dataUpload será definido no service (agora)
        return d;
    }
}