package br.dev.diego.aeroapi.controllers.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterInsert(
        @JsonProperty("MARCA")
        String marca,
        @JsonProperty("PROPRIETARIO")
        String proprietario,
        @JsonProperty("OUTROSPROPRIETARIOS")
        String outrosProprietarios,
        @JsonProperty("SGUF")
        String sgUf,
        @JsonProperty("CPFCNPJ")
        String cpfCnpj,
        @JsonProperty("NMOPERADOR")
        String nmOperador,
        @JsonProperty("OUTROSOPERADORES")
        String outrosOperadores,
        @JsonProperty("UFOPERADOR")
        String ufOperador,
        @JsonProperty("CPFCGC")
        String cpfCgc,
        @JsonProperty("NRCERTMATRICULA")
        String nrCertMatricula,
        @JsonProperty("NRSERIE")
        String nrSerie,
        @JsonProperty("CDCATEGORIA")
        String cdCategoria,
        @JsonProperty("CDTIPO")
        String cdTipo,
        @JsonProperty("DSMODELO")
        String dsModelo,
        @JsonProperty("NMFABRICANTE")
        String nmFabricante,
        @JsonProperty("CDCLS")
        String cdCls,
        @JsonProperty("NRPMD")
        String nrPmd,
        @JsonProperty("CDTIPOICAO")
        String cdTipoIcao,
        @JsonProperty("NRTRIPULACAOMIN")
        String nrTripulacaoMin,
        @JsonProperty("NRPASSAGEIROSMAX")
        String nrPassageirosMax,
        @JsonProperty("NRASSENTOS")
        String nrAssentos,
        @JsonProperty("NRANOFABRICACAO")
        String nrAnoFabricacao,
        @JsonProperty("DTVALIDADECVA")
        String dtValidadeCva,
        @JsonProperty("DTVALIDADECA")
        String dtValidadeCa,
        @JsonProperty("DTCANC")
        String dtCanc,
        @JsonProperty("DSMOTIVOCANC")
        String dsMotivoCanc,
        @JsonProperty("CDINTERDICAO")
        String cdInterdicao,
        @JsonProperty("CDMARCANAC1")
        String cdMarcaNac1,
        @JsonProperty("CDMARCANAC2")
        String cdMarcaNac2,
        @JsonProperty("CDMARCANAC3")
        String cdMarcaNac3,
        @JsonProperty("CDMARCAESTRANGEIRA")
        String cdMarcaEstrangeira,
        @JsonProperty("DSGRAVAME")
        String dsGravame,
        @JsonProperty("DT_MATRICULA")
        String dtMatricula
) {
}
