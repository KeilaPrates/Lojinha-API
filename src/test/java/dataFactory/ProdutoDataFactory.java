package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComValorIgualA(double valor) {
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Scooter 180km Blue");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Bateria 1500wwt");
        componente.setComponenteQuantidade(1);

        componentes.add(componente);

        ComponentePojo segundoComponente = new ComponentePojo();
        componente.setComponenteNome("Bateria EXTRA 1200WWT");
        componente.setComponenteQuantidade(1);

        componentes.add(segundoComponente);

        produto.setComponentes(componentes);

        return produto;
    }

}
