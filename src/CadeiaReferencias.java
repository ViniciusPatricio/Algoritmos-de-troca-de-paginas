import java.util.LinkedList;

public class CadeiaReferencias {
    LinkedList<ElementoCadeiaReferencias> listaCadeiaReferencias; // lista de referências passadas para memória

    // construtor da classe
    public CadeiaReferencias(){
        listaCadeiaReferencias = new LinkedList<>();
    }

    // função que adiciona as referências na listaCadeiaReferências
    public void addElemento(ElementoCadeiaReferencias novoElemento){
        listaCadeiaReferencias.add(novoElemento);
    }
    // função que exibe a descrição de cada refência da listaCadeiaReferencias
    public void getDescricao(){
        for(ElementoCadeiaReferencias ele : listaCadeiaReferencias){
            ele.getDescricao();
        }
    }

}
