package com.upt.pt.api.dto;

import java.util.List;

public class EstatisticasDTO {

    private double percentAprovadas;
    private double percentPendentes;
    private double percentRejeitadas;

    private int ofertasSemCandidaturas;

    private List<String> cursosMaisProcurados;
    private List<String> empresasMaisProcuradas;
    private List<String> empresasMenosEscolhidas;

    // ===== getters e setters =====

    public double getPercentAprovadas() {
        return percentAprovadas;
    }

    public void setPercentAprovadas(double percentAprovadas) {
        this.percentAprovadas = percentAprovadas;
    }

    public double getPercentPendentes() {
        return percentPendentes;
    }

    public void setPercentPendentes(double percentPendentes) {
        this.percentPendentes = percentPendentes;
    }

    public double getPercentRejeitadas() {
        return percentRejeitadas;
    }

    public void setPercentRejeitadas(double percentRejeitadas) {
        this.percentRejeitadas = percentRejeitadas;
    }

    public int getOfertasSemCandidaturas() {
        return ofertasSemCandidaturas;
    }

    public void setOfertasSemCandidaturas(int ofertasSemCandidaturas) {
        this.ofertasSemCandidaturas = ofertasSemCandidaturas;
    }

    public List<String> getCursosMaisProcurados() {
        return cursosMaisProcurados;
    }

    public void setCursosMaisProcurados(List<String> cursosMaisProcurados) {
        this.cursosMaisProcurados = cursosMaisProcurados;
    }

    public List<String> getEmpresasMaisProcuradas() {
        return empresasMaisProcuradas;
    }

    public void setEmpresasMaisProcuradas(List<String> empresasMaisProcuradas) {
        this.empresasMaisProcuradas = empresasMaisProcuradas;
    }

    public List<String> getEmpresasMenosEscolhidas() {
        return empresasMenosEscolhidas;
    }

    public void setEmpresasMenosEscolhidas(List<String> empresasMenosEscolhidas) {
        this.empresasMenosEscolhidas = empresasMenosEscolhidas;
    }
}