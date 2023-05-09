public class LRU {
    int pageFaults; // quantidade de páginas não encontradas

    // construtor da classe do algoritmo LRU
    public LRU(){
        this.pageFaults = 0;
    }

    // método que realiza a alocação de páginas de processos em memória
    public void execLRU(CadeiaReferencias cadeiaR,Memoria memoria){

        // é pego cada elemento da lista de cadeia de referências para realizar a locação
        for(ElementoCadeiaReferencias elementoCadeia : cadeiaR.listaCadeiaReferencias){

            //verifando se página está em memória
            if(memoria.verificarPaginaMemoria(elementoCadeia)){

                pageFaults++; // quantidade de páginas não encontradas incrementado
                int indice; // indice representa qual lugar deve ser alocado a página do processo na memória

                if(memoria.memoriaCheia()){ // caso a memoria está cheia, será substituído a página mais "antiga"
                    indice = memoria.indicePaginaMaisAntiga();
                    memoria.alocarMemoria(elementoCadeia,indice);
                }else{ // caso contrário, será colocado onde há espaço livre em memória
                    indice = memoria.indiceQuadroDisponivel();
                    memoria.alocarMemoria(elementoCadeia,indice);
                }

            }else{ // caso a página do processo já está em memória, é atualizado o tempo de chegada

                if(memoria.getQuantidadeQuadrosAlocados()<8000){
                    memoria.quantidadeQuadrosAlocados--;
                }
                int indice = memoria.verificarIndicePaginaMemoria(elementoCadeia); // recebe o indice em que lugar em memória está alocado a página
                memoria.alocarMemoria(elementoCadeia,indice);

                /* toda a vez inserido um elemento e a quantidade de for menor que 8000, a quantidade alocada é encrementada
                   como neste caso é só uma substituição, então a quantidade de quadros alocados é decrementado
                */

            }
        }
    }
    // Método exibe a quantidade total de páginas que não foram encontradas em memória
    public void getPageFaults(){
        System.out.println("LRU - "+pageFaults);
    }

}
