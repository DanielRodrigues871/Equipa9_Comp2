package com.upt.pt.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.pt.api.dto.RegistroDTO;
import com.upt.pt.api.entity.Empresa;
import com.upt.pt.api.entity.RepresentanteEmpresa;
import com.upt.pt.api.repository.EmpresaRepository;
import com.upt.pt.api.repository.RepresentanteEmpresaRepository;
import com.upt.pt.api.security.PasswordUtils;

@Service
public class RepresentanteEmpresaService {

    private final RepresentanteEmpresaRepository representanteRepository;
    private final EmpresaRepository empresaRepository;

    public RepresentanteEmpresaService(RepresentanteEmpresaRepository representanteRepository,
                                       EmpresaRepository empresaRepository) {
        this.representanteRepository = representanteRepository;
        this.empresaRepository = empresaRepository;
    }

    // CREATE
    public RepresentanteEmpresa createRepresentante(RepresentanteEmpresa r, String empresaId) {
        validarDadosRepresentante(r, empresaId, null);

        Empresa empresa = empresaRepository.findById(empresaId).get();
        r.setEmpresa(empresa);

        // garantir hash da password se vier em texto
        if (r.getPassword() != null && !r.getPassword().isBlank()) {
            r.setPassword(PasswordUtils.hashPassword(r.getPassword()));
        }

        return representanteRepository.save(r);
    }

    // READ all
    public List<RepresentanteEmpresa> getAllRepresentantes() {
        return representanteRepository.findAll();
    }

    // READ by id
    public RepresentanteEmpresa getRepresentanteById(String id) {
        return representanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Representante não encontrado."));
    }

    // READ by empresa
    public List<RepresentanteEmpresa> getRepresentantesByEmpresa(String empresaId) {
        return representanteRepository.findByEmpresaId(empresaId);
    }

    // UPDATE
    public RepresentanteEmpresa updateRepresentante(String id, RepresentanteEmpresa dados, String empresaId) {
        RepresentanteEmpresa existente = getRepresentanteById(id);

        validarDadosRepresentante(dados, empresaId, id);

        Empresa empresa = empresaRepository.findById(empresaId).get();

        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());

        if (dados.getPassword() != null && !dados.getPassword().isBlank()) {
            existente.setPassword(PasswordUtils.hashPassword(dados.getPassword()));
        }

        existente.setCargo(dados.getCargo());
        existente.setTelefone(dados.getTelefone());
        existente.setEmpresa(empresa);

        return representanteRepository.save(existente);
    }

    // DELETE
    public void deleteRepresentante(String id) {
        RepresentanteEmpresa r = getRepresentanteById(id);
        // aqui podes pôr regras de negócio antes de apagar (ex.: ofertas associadas)
        representanteRepository.delete(r);
    }

    // CREATE a partir do registo (AuthService)
    public RepresentanteEmpresa createFromRegister(RegistroDTO dto) {
        if (dto.getPassword() == null) {
            throw new IllegalArgumentException("Password é obrigatória.");
        }

        String hashed = PasswordUtils.hashPassword(dto.getPassword());

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada."));

        RepresentanteEmpresa r = new RepresentanteEmpresa();
        r.setNome(dto.getNome());
        r.setEmail(dto.getEmail());
        r.setPassword(hashed);
        r.setEmpresa(empresa);
        // se tiveres campo 'cargo' obrigatório, podes definir um default ou acrescentar ao DTO

        return representanteRepository.save(r);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosRepresentante(RepresentanteEmpresa r, String empresaId, String idAtual) {
        if (r == null) {
            throw new IllegalArgumentException("Representante não pode ser nulo.");
        }

        // Nome
        if (r.getNome() == null || r.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        // Email
        if (r.getEmail() == null || r.getEmail().isBlank() || !r.getEmail().contains("@")) {
            throw new IllegalArgumentException("O email é obrigatório e deve ser válido.");
        }

        // Password forte se fornecida neste fluxo
        if (r.getPassword() != null) {
            PasswordUtils.validarPasswordForte(r.getPassword());
        }

        // Cargo
        if (r.getCargo() == null || r.getCargo().isBlank()) {
            throw new IllegalArgumentException("O cargo é obrigatório.");
        }

        // Telefone opcional mas validado se existir
        if (r.getTelefone() != null && !r.getTelefone().isBlank()) {
            String tel = r.getTelefone().replaceAll("\\s+", "");
            if (!tel.matches("^(251\\d{6}|9\\d{8})$")) {
                throw new IllegalArgumentException(
                        "O telefone deve ter 9 dígitos e começar por 251 ou por 9.");
            }
        }

        // Empresa obrigatória e existente
        if (empresaId == null || empresaId.isBlank()) {
            throw new IllegalArgumentException("A empresa é obrigatória.");
        }
        if (!empresaRepository.existsById(empresaId)) {
            throw new IllegalArgumentException("A empresa indicada não existe.");
        }

        // Unicidade do email
        Optional<RepresentanteEmpresa> existenteEmail =
                representanteRepository.findByEmail(r.getEmail());

        if (existenteEmail.isPresent()
                && (idAtual == null || !existenteEmail.get().getId().equals(idAtual))) {
            throw new IllegalArgumentException("Já existe um representante com esse email.");
        }
    }
}
