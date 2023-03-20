package br.dev.diego.aeroapi.entities;

import br.dev.diego.aeroapi.controllers.records.RegisterInsert;
import br.dev.diego.aeroapi.services.util.DataUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    @Id
    private String marca;
    private String proprietario;
    private String outrosProprietarios;
    private String sgUf;
    private String cpfCnpj;
    private String nmOperador;
    @Column(columnDefinition = "TEXT")
    private String outrosOperadores;
    private String ufOperador;
    private String cpfCgc;
    private String nrCertMatricula;
    private String nrSerie;
    private String cdCategoria;
    private String cdTipo;
    private String dsModelo;
    private String nmFabricante;
    private String cdCls;
    private String nrPmd;
    private String cdTipoIcao;
    private String nrTripulacaoMin;
    private String nrPassageirosMax;
    private String nrAssentos;
    private String nrAnoFabricacao;
    private LocalDate dtValidadeCva;
    private LocalDate dtValidadeCa;
    private String dtCanc;
    private String dsMotivoCanc;
    private String cdInterdicao;
    private String cdMarcaNac1;
    private String cdMarcaNac2;
    private String cdMarcaNac3;
    private String cdMarcaEstrangeira;
    private String dsGravame;
    private LocalDateTime dtMatricula;
    private LocalDateTime ultimaAtualizacao;

    @PrePersist
    private void setUltimaAtualizacao() {
        ultimaAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    private void setUltimaAtualizacaoUpdate() {
        ultimaAtualizacao = LocalDateTime.now();
    }

    public Register(RegisterInsert record) {
        marca = record.marca();
        proprietario = record.proprietario();
        outrosProprietarios = record.outrosProprietarios();
        sgUf = record.sgUf();
        cpfCnpj = record.cpfCnpj();
        nmOperador = record.nmOperador();
        outrosOperadores = record.outrosOperadores();
        ufOperador = record.ufOperador();
        cpfCgc = record.cpfCgc();
        nrCertMatricula = record.nrCertMatricula();
        nrSerie = record.nrSerie();
        cdCategoria = record.cdCategoria();
        cdTipo = record.cdTipo();
        dsModelo = record.dsModelo();
        nmFabricante = record.nmFabricante();
        cdCls = record.cdCls();
        nrPmd = record.nrPmd();
        cdTipoIcao = record.cdTipoIcao();
        nrTripulacaoMin = record.nrTripulacaoMin();
        nrPassageirosMax = record.nrPassageirosMax();
        nrAssentos = record.nrAssentos();
        nrAnoFabricacao = record.nrAnoFabricacao();
        dtValidadeCva = DataUtil.formatarStringDDMMAAAA(record.dtValidadeCva());
        dtValidadeCa = DataUtil.formatarStringDDMMAAAA(record.dtValidadeCa());
        dtCanc = record.dtCanc();
        dsMotivoCanc = record.dsMotivoCanc();
        cdInterdicao = record.cdInterdicao();
        cdMarcaNac1 = record.cdMarcaNac1();
        cdMarcaNac2 = record.cdMarcaNac2();
        cdMarcaNac3 = record.cdMarcaNac3();
        cdMarcaEstrangeira = record.cdMarcaEstrangeira();
        dsGravame = record.dsGravame();
        dtMatricula = DataUtil.formatarStringAAAAMMDDHHMMSS(record.dtMatricula());
    }

}
