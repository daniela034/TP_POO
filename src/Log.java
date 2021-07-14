import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

//onde vamos ter a nossa população, vamos passar do parser para aqui
public class Log {
    private Map<String,Equipa> equipas = new HashMap<>();
    private Map<Integer,Jogador> jogadores = new HashMap<>();
    private List<Jogo> jogos = new ArrayList<>();


    public List<Jogo> getJogos() {
        List<Jogo> r = new ArrayList<>();
        Iterator<Jogo> it = this.jogos.iterator();
        while(it.hasNext()){
            r.add((Jogo) it.next().clone());
        }
        return r;
    }
    public void setJogos(List<Jogo> jogos1){
        this.jogos = new ArrayList<>();
        Iterator<Jogo> it = jogos1.iterator();
        while(it.hasNext()){
            this.jogos.add((Jogo) it.next().clone());
        }
    }

    public void adicionaJogadorEquipa(Jogador jogador, String equipa){
        jogadores.put(jogador.getID(), jogador);
        equipas.get(equipa).insereJogador(jogador);
    }

    public void adicionaEquipa(Equipa equipa){
        equipas.put(equipa.getNome(), equipa);
    }

    public void eliminaJogadorEquipa(int idJogador, String equipa){
        equipas.get(equipa).removeJogador(idJogador);
        jogadores.get(idJogador).insereEquipaHistorico(equipa);
    }

    public void mudaJ(int idJogador, String equipa){
        Jogador jogador = jogadores.get(idJogador);
        equipas.get(equipa).insereJogador(jogador);

    }

    public void adicionaJogo(Jogo jogo){
        jogos.add(jogo);
    }

    public Map<String,Equipa> getEquipas(){
        Map<String,Equipa> res = new HashMap<>();
        for(String i : this.equipas.keySet()) {
            Equipa j = this.equipas.get(i);
            res.put(i, j.clone());
        }
        return res;
    }
    public void setEquipas(Map<String,Equipa> eq){
        Map<String,Equipa> res = new HashMap<>();
        for(String i : eq.keySet()) {
            Equipa j = eq.get(i);
            res.put(i, j.clone());
        }
        this.equipas = res;
    }

    public Map<Integer,Jogador> getJogadores(){
        Map<Integer,Jogador> res = new HashMap<>();
        for(Integer i : this.jogadores.keySet()) {
            Jogador j = this.jogadores.get(i);
            res.put(i, j.clone());
        }
        return res;
    }

    public void setJogadores(Map<Integer,Jogador> jo){
        Map<Integer,Jogador> res = new HashMap<>();
        for(Integer i : jo.keySet()) {
            Jogador j = jo.get(i);
            res.put(i, j.clone());
        }
        this.jogadores = res;
    }

    public Log carregarFicheiro() throws LinhaIncorretaException {
        HashMap<String, Equipa> equipas = new HashMap<>();
        HashMap<Integer, Jogador> jogadores = new HashMap<>();
        List<Jogo> jogos = new ArrayList<>();
        List<String> linhas = lerFicheiro("logsV2.txt");
        String[] linhaPartida;
        Equipa ultima = null;
        Jogador j = null;
        double resultado;
        int id_jogador = 1;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]) {
                case "Equipa":
                    Equipa e = Equipa.parse(linhaPartida[1]);
                    equipas.put(e.getNome(), e);
                    ultima = e;
                    break;
                case "Guarda-Redes":
                    j = GuardaRedes.parse(linhaPartida[1], id_jogador);
                    id_jogador++;
                    jogadores.put(j.getID(), j);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1], id_jogador);
                    id_jogador++;
                    jogadores.put(j.getID(), j);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1], id_jogador);
                    id_jogador++;
                    jogadores.put(j.getID(), j);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Lateral":
                    j = Lateral.parse(linhaPartida[1], id_jogador);
                    id_jogador++;
                    jogadores.put(j.getID(), j);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1], id_jogador);
                    id_jogador++;
                    jogadores.put(j.getID(), j);
                    if (ultima == null)
                        throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.insereJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Jogo":
                    Jogo jo = Jogo.parse(linhaPartida[1]);
                    jogos.add(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();
            }
        }

        this.equipas = equipas;
        this.jogadores = jogadores;
        this.jogos = jogos;
        return this;
    }



    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    public void guardarLog(){
        try {
            Path old_log_path = Paths.get("logsV2.txt");
            Files.delete(old_log_path);
            Path new_log_path = Files.createFile(old_log_path);
            Files.write(new_log_path, this.getLog(), StandardCharsets.UTF_8, StandardOpenOption.WRITE);
        } catch(IOException exc) {
            exc.printStackTrace();
        }
    }

    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<>();
        for(String nomeEquipa : this.equipas.keySet()){
            Equipa equipa = this.equipas.get(nomeEquipa);
            log.addAll(equipa.getLog());
        }
        Iterator<Jogo> it = this.jogos.iterator();
        while(it.hasNext()){
            Jogo jogo = it.next().clone();
            log.add(jogo.getLog());
        }
        return log;
    }

}
