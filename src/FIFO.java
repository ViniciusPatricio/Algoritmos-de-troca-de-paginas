public class FIFO {

    int pageFaults; // quantidade de páginas não encontradas
    int indicePaginaMaisAntiga;


    public FIFO(){
        this.pageFaults = 0;
    }

    public void execFIFO(CadeiaReferencias cadeiaR,Memoria memoria){
        for(ElementoCadeiaReferencias elementoCadeia : cadeiaR.listaCadeiaReferencias){

            //verificar se a página já está em memória
            if(memoria.verificarPaginaMemoria(elementoCadeia)){
                pageFaults++; // quantidade de páginas não encontradas incrementado
                if(memoria.memoriaCheia()){ // se a página estiver cheia, procura-se a página mais "antiga" para ser substituída
                    indicePaginaMaisAntiga = memoria.indicePaginaMaisAntiga();
                    memoria.alocarMemoria(elementoCadeia,indicePaginaMaisAntiga);
                }else{ // caso não esteja cheia, é alocada num quadro que está disponível
                    int indice = memoria.indiceQuadroDisponivel();
                    memoria.alocarMemoria(elementoCadeia,indice);
                }
            }
        }
    }
    // Método exibe a quantidade total de páginas que não foram encontradas em memória
    public void getPageFaults(){
        System.out.println("FIFO - "+pageFaults);
    }
}
