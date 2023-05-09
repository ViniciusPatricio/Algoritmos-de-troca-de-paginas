import java.io.File;
import java.util.Scanner;


public class PaginacaoMain {

    // Função que realiza a leitura do arquivo
    public static String leituraArquivo(String nomeArquivo) throws Exception{
        String arquivoLido;

        File file = new File(nomeArquivo);
        Scanner sc = new Scanner(file);
        arquivoLido = sc.nextLine();

        return arquivoLido;
    }

    // funão irá criar a Cadeia de Referências
    public static CadeiaReferencias criarCadeiaReferencias(String arquivoLido){

        CadeiaReferencias cadeiaR = new CadeiaReferencias();
        ElementoCadeiaReferencias elemento;

        int inicio = 0;
        int fim = arquivoLido.indexOf(';');

        String elementoCadeia;
        String processo;
        String pagina;
        String aux = arquivoLido;
        int tempo = 0;
        do{
            elementoCadeia = aux.substring(inicio,fim);
            processo = elementoCadeia.substring(0,elementoCadeia.indexOf(','));
            pagina = elementoCadeia.substring(elementoCadeia.indexOf(',')+1);

            elemento = new ElementoCadeiaReferencias(processo,pagina,tempo);
            cadeiaR.addElemento(elemento);

            //System.out.println("Processo: "+processo+" Pagina: "+pagina);
            aux = aux.substring(fim+1);
            fim = aux.indexOf(';');
            tempo++;
        }while (!aux.substring(inicio,fim).equals("0,0"));

        return cadeiaR;
    }

    public static void main(String[] args)throws Exception{

        Scanner scan = new Scanner(System.in);
        // **************************** REALIZANDO A LEITURA DO ARQUIVO  **************************** //

        System.out.print("Nome do arquivo: ");
        String nomeArquivo = scan.next();

        String arquivo = leituraArquivo(nomeArquivo);

        // **************************** CRIAÇÃO DA CADEIRA DE REFERÊNCIAS  **************************** //

        CadeiaReferencias cadeiaRF = criarCadeiaReferencias(arquivo);

        // **************************** INICIALIZANDO A ESTRUTURA MEMORIA  **************************** //

        Memoria memoria = new Memoria(); // criando a estrutura que representa memória
        memoria.estruturandoMemoria(); // estruturando a memória com 8000 quadros

        // ************************** EXECUÇÕES DOS ALGORITMOS DA PAGINAÇÂO  ************************* //

        FIFO algoritmoFIFO = new FIFO();
        algoritmoFIFO.execFIFO(cadeiaRF,memoria);
        algoritmoFIFO.getPageFaults();



        Memoria memoria2 = new Memoria(); // criando a estrutura que representa memória
        memoria2.estruturandoMemoria(); // estruturando a memória com 8000 quadros


        LRU algoritmoLRU = new LRU();
        algoritmoLRU.execLRU(cadeiaRF,memoria2);
        algoritmoLRU.getPageFaults();

        Memoria memoria3 = new Memoria(); // criando a estrutura que representa memória
        memoria3.estruturandoMemoria();  // estruturando a memória com 8000 quadros

        SegundaChance algoritmoSC = new SegundaChance();
        algoritmoSC.execSegundaChance(cadeiaRF,memoria3);
        algoritmoSC.getPageFaults();

    }
}
