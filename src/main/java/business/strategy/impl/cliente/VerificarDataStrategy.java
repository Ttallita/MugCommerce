package business.strategy.impl.cliente;

import business.strategy.IStrategy;
import model.EntidadeDominio;
import model.cliente.Cliente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VerificarDataStrategy implements IStrategy {
    @Override
    public String processa(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        LocalDate dataNascimento = cliente.getDataNascimento();

        if(dataNascimento == null)
            return "Insira uma data válida";

        LocalDate dataAtual = LocalDate.now();

        if(dataNascimento.isAfter(dataAtual))
            return "A data de nascimento não pode ser maior que a atual";


        long anosDiferenca = ChronoUnit.YEARS.between(dataNascimento, dataAtual);

        if(anosDiferenca < 18)
            return "É necessario ter mais de 18 anos para criar uma conta";

        return null;
    }
}
