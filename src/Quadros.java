public class Quadros {

    int flagOcupacao; // flag que indica se o quadro está em uso
    ElementoCadeiaReferencias elementoExec; // atributo que diz qual é a página do processo que está em memória

    // construtor da classe
    public Quadros(){
     this.flagOcupacao = 0;
    }

    // função que aloca a pagina do processo em memória
    public void carregarPagina(ElementoCadeiaReferencias elementoExec){
        this.flagOcupacao = 1;
        this.elementoExec = elementoExec;
    }
    // função que retorna se o quadro está em uso;
    public boolean quadroEmExecucao(){
        if(flagOcupacao == 1) return true; else return  false;
    }

}
