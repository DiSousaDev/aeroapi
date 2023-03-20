package br.dev.diego.aeroapi.controllers.records;

import br.dev.diego.aeroapi.entities.Register;

public record RegisterResponse(
        String matricula,
        String proprietario,
        String cpfcnpj,
        String operador,
        String fabricante,
        Integer anoFabricacao,
        String modelo,
        String numeroSerie,
        String numeroMatricula,
        String pesoMaxDecolagem,
        Integer maximoPassageiros,
        Integer quantidadeAssentos

) {

    public RegisterResponse(Register entity) {
        this(
                entity.getMarca(),
                entity.getProprietario(),
                entity.getCpfCgc(),
                entity.getNmOperador(),
                entity.getNmFabricante(),
                Integer.parseInt(entity.getNrAnoFabricacao()),
                entity.getDsModelo(),
                entity.getNrSerie(),
                entity.getNrCertMatricula(),
                String.format("%s %s", entity.getNrPmd(), "Kg"),
                Integer.parseInt(entity.getNrPassageirosMax()),
                Integer.parseInt(entity.getNrAssentos())
        );
    }

}
