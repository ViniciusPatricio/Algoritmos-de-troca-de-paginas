public class ElementoCadeiaReferencias {
    String processo; // nome do processo
    String pagina; // nome da página do processo
    int tempoChegada; // tempo de chegada, necessário para verificar qual página será substituída


    // construtor da classe
    public ElementoCadeiaReferencias(String processo, String pagina, int tempoChegada){
        this.processo = processo;
        this.pagina = pagina;
        this.tempoChegada = tempoChegada;
    }

    // função que exibe o nome do processo, nome da página e o tempo de chegada da página
    public void getDescricao(){
        System.out.println("Processo: "+processo+" Pagina: "+pagina+"  Tempo Chegada: "+tempoChegada);
    }

    // função criada para comparar as referências, colabora para indentificar se uma página de um processo já está alocado em memória
    public boolean equals(ElementoCadeiaReferencias elementoC){
        if(elementoC.pagina.equals(pagina) && elementoC.processo.equals(processo)) return  true; else return false;
    }

}
