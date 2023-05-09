import java.util.ArrayList;

public class Memoria {

    ArrayList<Quadros> vetorQuadros; // memória possui uma vetor de quadros
    int quantidadeQuadrosAlocados = 0; // valor indica quantos quadros estão em uso da memória

    // construtor da classe memória
    public Memoria(){
        vetorQuadros = new ArrayList<>(8000);
    }

    // a função irá alocar os quadros da memória para serem utilizados
    public void estruturandoMemoria(){
        for(int i = 0; i <8000;i++){
            Quadros novoQuadro = new Quadros();
            vetorQuadros.add(novoQuadro);
        }
    }

    // função que veirifica se a página do processo passado na chamada da função está já alocado na memória
    public  boolean verificarPaginaMemoria(ElementoCadeiaReferencias elementoExec){
        boolean flag = false;

        for(int i = 0; i<8000; i++){
            if(vetorQuadros.get(i).elementoExec!=null){
                if(vetorQuadros.get(i).elementoExec.equals(elementoExec)){
                    flag = true;
                    break;
                }
            }
        }
        return !flag;
    }

    // função retorna o indice do quadro do que está alocado a página do processo passado na chamada da função
    // caso não for encontrado a página do processo passado, retorna -1
    public  int verificarIndicePaginaMemoria(ElementoCadeiaReferencias elementoExec){
        int indice = -1;

        for(int i = 0; i<8000; i++){
            if(vetorQuadros.get(i).elementoExec!=null){
                if(vetorQuadros.get(i).elementoExec.equals(elementoExec)){
                    indice = i;
                    break;
                }
            }
        }
        return  indice;
    }

    public boolean memoriaCheia(){

        return quantidadeQuadrosAlocados == 8000;

    }

    // A função verifica qual o quadro que está disponível e retorna o indice desse quadro do vetor de quadros
    public int indiceQuadroDisponivel(){
        int indice = 0;
        for(int i = 0; i<8000;i++){
            if(vetorQuadros.get(i).flagOcupacao == 0){
                indice = i;
                break;
            }
        }
        return  indice;
    }

    // função irá retornar qual é o indice da página mais antiga que está em memória
    public int indicePaginaMaisAntiga(){
        int tempoMaisAntigo = vetorQuadros.get(0).elementoExec.tempoChegada;
        int indice = 0;
        for(int i = 1; i<8000;i++){
            if(vetorQuadros.get(i).elementoExec.tempoChegada<tempoMaisAntigo){
                indice = i;
                tempoMaisAntigo = vetorQuadros.get(i).elementoExec.tempoChegada;
            }
        }
        return indice;
    }

    // insere a página de um processo em memória
    public void alocarMemoria(ElementoCadeiaReferencias elementoExec, int indice){
        vetorQuadros.get(indice).carregarPagina(elementoExec);
        if(quantidadeQuadrosAlocados < 8000){ // caso não foram utilizados todos os quadros, a quantidade de quadros utilizados é incrementado
            quantidadeQuadrosAlocados++;
        }
    }
    public int getQuantidadeQuadrosAlocados(){
        return quantidadeQuadrosAlocados;
    }
}
