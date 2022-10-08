package model.cliente;

import model.cliente.endereco.Endereco;
import model.cupom.Cupom;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;


class ClienteTest {

    @Test
    public void testaEqualsAndHashCode() {
        Endereco endereco = new Endereco();
        endereco.setCep("12312331");

        Endereco endereco2 = new Endereco();
        endereco2.setCep("24789632");

        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
        cartaoDeCredito.setBandeira("Visa");

        CartaoDeCredito cartaoDeCredito2 = new CartaoDeCredito();
        cartaoDeCredito2.setBandeira("Mastercard");

        Cupom cupom = new Cupom();
        cupom.setDescricao("Teste");

        Cupom cupom2 = new Cupom();
        cupom2.setDescricao("Teste 2");

        EqualsVerifier.forClass(Cliente.class)
                .withPrefabValues(Endereco.class, endereco, endereco2)
                .withPrefabValues(Cupom.class, cupom, cupom2)
                .suppress(Warning.NONFINAL_FIELDS)
                .withIgnoredFields("id", "ativo")
                .verify();
    }
}