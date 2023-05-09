public class SegundaChance {

    int pageFaults; // quantidade de páginas não encontradas
    int[] flagAcesso; // vetor que auxilia para identificar se a página foi registrada mais de uma vez em memória
    private  static int indice = 0; // indica em qual posição foi feita a última substituição da memória

    // construtor da classe
    public SegundaChance(){
        this.pageFaults = 0;
        this.flagAcesso = new int[8000];

    }

    // método que realiza a alocação de páginas de processos em memória
    public void execSegundaChance(CadeiaReferencias cadeiaR, Memoria memoria){
        // é pego cada elemento da lista de cadeia de referências para realizar a locação
        for(ElementoCadeiaReferencias elementoCadeia: cadeiaR.listaCadeiaReferencias){

            // verificando se a página do processo já está alocado em memória
            if(memoria.verificarPaginaMemoria(elementoCadeia)){ // caso não esteja em memória

                pageFaults++; // quantidade de páginas não encontradas incrementado

                if(memoria.memoriaCheia()){ // verificando se a memória está cheia

                    while(flagAcesso[indice] == 1){ // verificando qual indice da página a ser substituída

                        flagAcesso[indice] = 0;
                        indice++;

                        if(indice == 8000){
                            indice = 0;
                        }
                    }

                    memoria.alocarMemoria(elementoCadeia,indice); // realizando a substituição
                    flagAcesso[indice] = 0;

                    indice++;
                    if(indice == 8000){ // caso o indice seja igual a 8000 é necessário voltar para o início
                        indice = 0;
                    }

                }else{ // caso a memória não esteja cheia, é identificado qual quadro está livre para alocar a página
                    int indice = memoria.indiceQuadroDisponivel();
                    flagAcesso[indice] = 0;
                    memoria.alocarMemoria(elementoCadeia,indice);
                }

            }else{ // caso a página do processo já está em memória, o vetor de flag de acesso recebe 1
                int indice = memoria.verificarIndicePaginaMemoria(elementoCadeia);
                flagAcesso[indice] = 1;

            }
        }
    }

    // Método exibe a quantidade total de páginas que não foram encontradas em memória
    public void getPageFaults(){
        System.out.println("Segunda Chance - "+pageFaults);
    }

}
