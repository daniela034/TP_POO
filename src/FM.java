import javax.security.sasl.SaslClient;
import java.time.LocalDate;
import java.util.*;

public class FM {
    private Menu menuPrincipal;
    private Log log;

    public static void main(String[] args){
        try {
            new FM().runFM();
        } catch (LinhaIncorretaException e) {
            e.printStackTrace();
        }
    }

    /**
     * Construtor.
     * Cria os menus e a camada de negócio.
     */
    private FM(){
        // Criar menu principal
        String[]  principal = {"Pesquisa jogador",
                               "Pesquisa equipa",
                               "Ver equipas",
                               "Ver Jogadores",
                                "Ver jogo",
                                "Mudar jogador de equipa",
                               "Novo Jogador",
                               "Nova Equipa",
                               "Criar jogo entre duas equipas",
                                "Guarda"};
        this.menuPrincipal = new Menu(principal);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    private void runFM() throws LinhaIncorretaException{
        Log log = new Log().carregarFicheiro();
        this.log = log;
        do {
            menuPrincipal.executa();
            switch (menuPrincipal.getOpcao()) {
                case 1: pesquisaJogador(log);
                    break;
                case 2: pesquisaEquipa(log);
                    break;
                case 3: verEquipas(log);
                    break;
                case 4: verJogadores(log);
                    break;
                case 5: verJogo(log);
                    break;
                case 6: mudarJogador(log);
                    break;
                case 7: novoJogador(log);
                    break;
                case 8: novaEquipa(log);
                    break;
                case 9: criarJogo(log);
                    break;
                case 10: guardarLog(log);
                    break;
            }
        } while (menuPrincipal.getOpcao() != 0);
    }

    private void verEquipas(Log log){
        System.out.println("\n***VER EQUIPAS***");
        Map<String, Equipa> equipas = log.getEquipas();
        for(String key: equipas.keySet()) {
            Equipa e = equipas.get(key);
            System.out.println(e.toString());
        }
        System.out.println("> Prima Enter para continuar");
        Scanner is = new Scanner(System.in);
        is.nextLine();
    }

    private void verJogadores(Log log){
        System.out.println("\n***VER JOGADORES***");
        Map<Integer,Jogador> jogadores = log.getJogadores();
        for(Integer key: jogadores.keySet()) {
            Jogador j = jogadores.get(key);
            System.out.println(j.toString());
        }
        System.out.println("> Prima Enter para continuar");
        Scanner is = new Scanner(System.in);
        is.nextLine();
    }

    private void novoJogador(Log log) {
        System.out.println("\n***NOVO JOGADOR***");
        String nome, eq = "", posicao = "";
        int numero;
        Scanner sc = new Scanner(System.in);
        Map<Integer, Jogador> jogadores = this.log.getJogadores();
        int id = jogadores.size();
        Map<String, Equipa> equipas = this.log.getEquipas();
        System.out.println("Indique o nome do novo jogador:");
        nome = sc.next();
        System.out.println("Indique o número da camisola: ");
        numero = sc.nextInt();
        System.out.println("Indique a equipa:");
        eq = this.getNomeEquipa(log);
        System.out.println("Indique a posição:");
        int vel, res, des, imp, pc, rem, p, elas = 0,forca=0 ,ag=0, gls=0, posseb=0, cr=0, recup=0;
        boolean posicaoVal = false;
        while(!posicaoVal){
            switch (posicao = sc.next()) {
                case "Avançado":
                    System.out.println("Força: ");
                    forca = sc.nextInt();
                    System.out.println("Agilidade: ");
                    ag = sc.nextInt();
                    System.out.println("Golos: ");
                    gls = sc.nextInt();
                    posicaoVal = true;
                    break;
                case "Defesa":
                    System.out.println("Posse de bola: ");
                    posseb = sc.nextInt();
                    posicaoVal = true;
                    break;
                case "Lateral":
                    System.out.println("Cruzamentos: ");
                    cr = sc.nextInt();
                    posicaoVal = true;
                    break;
                case "Medio":
                    System.out.println("Recuperação de bolas: ");
                    recup = sc.nextInt();
                    posicaoVal = true;
                    break;
                case "Guarda-Redes":
                    System.out.println("Elasticidade: ");
                    elas = sc.nextInt();
                    posicaoVal = true;
                    break;
                default:
                    System.out.println("Posição inválida");
                    break;
            }
        }
        System.out.println("Velocidade: ");
        vel = sc.nextInt();
        System.out.println("Resistência: ");
        res = sc.nextInt();
        System.out.println("Destreza: ");
        des = sc.nextInt();
        System.out.println("Impulsão: ");
        imp = sc.nextInt();
        System.out.println("Passes cabeça: ");
        pc = sc.nextInt();
        System.out.println("Remate: ");
        rem = sc.nextInt();
        System.out.println("Passe: ");
        p = sc.nextInt();

        if(posicao.equals("Avançado")){
            Jogador avancado = new Avancado(id+1, nome, numero, vel,res,des,imp,pc,rem,p,forca,ag,gls);
            log.adicionaJogadorEquipa((Jogador) avancado, eq);
        }
        else if(posicao.equals("Defesa")){
            Defesa defesa = new Defesa(id+1, nome, numero, vel,res,des,imp,pc,rem,p,posseb);
            log.adicionaJogadorEquipa((Jogador) defesa, eq);
        }else if (posicao.equals("Lateral")){
            Lateral lateral = new Lateral(id+1, nome, numero, vel,res,des,imp,pc,rem,p,cr);
            log.adicionaJogadorEquipa((Jogador) lateral, eq);
        }else if (posicao.equals("Medio")){
            Medio medio = new Medio(id+1, nome, numero, vel,res,des,imp,pc,rem,p,recup);
            log.adicionaJogadorEquipa((Jogador) medio, eq);
        }else if(posicao.equals("Guarda-Redes")){
            GuardaRedes guardaRedes = new GuardaRedes(id+1, nome, numero, vel,res,des,imp,pc,rem,p,elas);
            log.adicionaJogadorEquipa((Jogador) guardaRedes, eq);
        }
        System.out.println("> Prima Enter para continuar");
        Scanner is = new Scanner(System.in);
        is.nextLine();
    }

    public void novaEquipa(Log log){
        System.out.println("\n***NOVA EQUIPA***");
        String nome = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o nome da nova equipa: ");
        boolean equipaNaoExiste = false;
        while (!equipaNaoExiste){
            nome = sc.nextLine();
            if(log.getEquipas().get(nome) != null){
                System.out.println("Equipa já existe");
            }
            else{
                equipaNaoExiste = true;
            }
        }
        Equipa eq = new Equipa(nome);
        log.adicionaEquipa(eq);
        System.out.println("> Prima Enter para continuar");
        Scanner is = new Scanner(System.in);
        is.nextLine();
    }

    public void verJogo(Log log){
        System.out.println("\n***VER JOGO***");
        List<Jogo> jogos = log.getJogos();
        Iterator<Jogo> it = jogos.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
        System.out.println("> Prima Enter para continuar");
        Scanner is = new Scanner(System.in);
        is.nextLine();
    }

    public void mudarJogador(Log log){
        System.out.println("\n***MUDAR JOGADOR DE EQUIPA***");
        Scanner sc = new Scanner(System.in);
        Jogador jogador;
        int idJogador;
        String eq, peq;
        System.out.println("Qual é o jogador que quer trocar:");
        idJogador = this.getIDjogador(log);
        System.out.println("Em que equipa estava: ");
        peq = this.getNomeEquipa(log);
        System.out.println("Para que equipa vai: ");
        eq = this.getNomeEquipa(log);

        log.eliminaJogadorEquipa(idJogador,peq);
        log.mudaJ(idJogador, eq);
        System.out.println("> Prima Enter para continuar");
        Scanner is = new Scanner(System.in);
        is.nextLine();
    }

    public void criarJogo(Log log){
        System.out.println("\n***CRIAR JOGO***");
        Scanner sc = new Scanner(System.in);
        String nomeCasa;
        System.out.println("Indique a equipa da casa: ");
        nomeCasa = this.getNomeEquipa(log);
        Equipa equipaCasa = log.getEquipas().get(nomeCasa);
        int n = 11, idJ;
        for(Integer i : equipaCasa.getJogadores().keySet()) {
            Jogador j = equipaCasa.getJogadores().get(i);
            System.out.println(j.toString());
        }
        List<Integer> jogadoresCasa = new ArrayList<>();
        System.out.println("Selecione os titulares: ");
        for( int i = 0; i < 11; i++){
            idJ = sc.nextInt();
            if(equipaCasa.getJogadores().get(idJ) == null){
                System.out.println("Jogador não pertence a esta equipa");
                i--;
            }else {
                jogadoresCasa.add(idJ);
            }
        }
        Map<Integer,Integer> substituicoesCasa = new HashMap<>();
        System.out.println("Selecione os substituições (prima 0 para sair) : ");
        int idsub1 = -1 , numsubs = 0, idsub2;
        while(idsub1!= 0 && numsubs < 3 ){
            System.out.println("Indique jogador para sair: ");
            idsub1 = sc.nextInt();
            System.out.println("Indique jogador para entrar: ");
            idsub2 = sc.nextInt();
            if(idsub1 == 0 || idsub2 == 0) break;
            if(jogadoresCasa.contains(idsub1)
                    && equipaCasa.getJogadores().get(idsub2)!=null
                    && !jogadoresCasa.contains(idsub2)){
                numsubs++;
                substituicoesCasa.put(idsub1,idsub2);
            }
        }

        System.out.println("Indique a equipa de fora: ");
        String nomeFora = this.getNomeEquipa(log);
        boolean equipasDiferentes = false;
        Equipa equipaFora = log.getEquipas().get(nomeFora);
        int n1 = 11, idJ1;
        for(Integer i : equipaFora.getJogadores().keySet()) {
            Jogador j = equipaFora.getJogadores().get(i);
            System.out.println(j.toString());
        }
        List<Integer> jogadoresFora = new ArrayList<>();
        System.out.println("Selecione os titulares: ");
        for( int i = 0; i < 11; i++){
            idJ1 = sc.nextInt();
            if(equipaFora.getJogadores().get(idJ1) == null){
                System.out.println("Jogador não pertence a esta equipa");
                i--;
            }else {
                jogadoresFora.add(idJ1);
            }
        }
        Map<Integer,Integer> substituicoesFora = new HashMap<>();
        System.out.println("Selecione os substituições (prima 0 para sair) : ");
        int idsub11 = -1 , numsubs1 = 0, idsub21;
        while(idsub11!= 0 && numsubs1 < 3 ){
            System.out.println("Indique jogador para sair: ");
            idsub11 = sc.nextInt();
            System.out.println("Indique jogador para entrar: ");
            idsub21 = sc.nextInt();
            if(idsub11 == 0 || idsub21 == 0) break;
            if(jogadoresFora.contains(idsub11)
                    && equipaFora.getJogadores().get(idsub21)!=null
                    && !jogadoresFora.contains(idsub21)){
                numsubs++;
                substituicoesFora.put(idsub11,idsub21);

            }
        }

        System.out.println("Selecione uma data (AAAA-MM-DD) : ");
        sc.nextLine();
        String date = sc.nextLine();
        String[] data = date.split("-");
        LocalDate novaData = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));

        int golosCasa = golos(jogadoresCasa);
        int golosFora = golos(jogadoresFora);

        Jogo jogo = new Jogo(nomeCasa, nomeFora, golosCasa, golosFora, novaData, jogadoresCasa, substituicoesCasa ,jogadoresFora, substituicoesFora);
        log.adicionaJogo(jogo);
        System.out.println(jogo);
        System.out.println("> Prima Enter para continuar");
        Scanner is = new Scanner(System.in);
        is.nextLine();

    }

    public int golos(List<Integer> idJogadores){
        int habilidadeTotal = 0;
        Iterator<Integer> it = idJogadores.iterator();
        while(it.hasNext()){
            habilidadeTotal += log.getJogadores().get(it.next()).calculaH();
        }
        return habilidadeTotal % 5;
    }

    public void pesquisaJogador(Log log){
        System.out.println("\n***PESQUISA JOGADOR***");
        Scanner sc = new Scanner(System.in);
        int idJ;
        System.out.println("Indique qual o jogador: ");
        idJ = this.getIDjogador(log);

        Jogador jogador = log.getJogadores().get(idJ);
        System.out.println(jogador);
        System.out.println("> Prima Enter para continuar");
        Scanner is = new Scanner(System.in);
        is.nextLine();

    }

    public void pesquisaEquipa(Log log){
        System.out.println("\n***PESQUISA EQUIPA***");
        Scanner sc = new Scanner(System.in);
        String nome;
        System.out.println("Indique qual a equipa: ");
        nome = this.getNomeEquipa(log);

        Equipa equipa = log.getEquipas().get(nome);
        System.out.println(equipa);
        System.out.println("> Prima Enter para continuar");
        Scanner is = new Scanner(System.in);
        is.nextLine();
    }

    public int getIDjogador(Log log){
        boolean idNaoExiste = false;
        int idJ = 0;
        Scanner sc = new Scanner(System.in);
        while(!idNaoExiste){
            idJ = sc.nextInt();
            if(log.getJogadores().get(idJ) == null){
                System.out.println("Jogador não existe");
            }
            else{
                idNaoExiste = true;
            }

        }
        return idJ;
    }

    public String getNomeEquipa(Log log){
        boolean nomeNaoExiste = false;
        String nomeEq = "";
        Scanner sc = new Scanner(System.in);
        while(!nomeNaoExiste){
            nomeEq = sc.nextLine();
            if(log.getEquipas().get(nomeEq) == null){
                System.out.println("Equipa não existe");
            }
            else{
                nomeNaoExiste = true;
            }
        }
        return nomeEq;
    }

    public void guardarLog(Log log){
        log.guardarLog();
    }



}





