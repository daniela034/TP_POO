import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Equipa implements Serializable {
    private String nome;
   private Map<Integer,Jogador> jogadores;

    public Equipa(){
        this.nome = "";
        this.jogadores = new HashMap<Integer,Jogador>();
    }

    public Equipa(String nome) {
        this.nome = nome;
        this.jogadores = new HashMap<>();
    }

    public Equipa(String n, Map<Integer, Jogador> j){
        this.nome = n;
        this.jogadores = new HashMap<Integer,Jogador>();
        for(Jogador e : j.values())
            this.jogadores.put(e.getID(), e);
    }


    public Equipa(Equipa j){
        this.nome = j.getNome();
        this.jogadores = j.getJogadores();
    }

    public String getNome(){
        return this.nome;
    }
    public Map<Integer,Jogador>  getJogadores() {
        Map<Integer,Jogador> res = new HashMap<>();
        for(Integer i : this.jogadores.keySet()) {
            Jogador j = this.jogadores.get(i);
            res.put(i, j.clone());
        }
        return res;
    }
    public void setJogadores(Map<Integer,Jogador> e){
        Map<Integer,Jogador> res = new HashMap<>();
        for(Integer i : e.keySet()) {
            Jogador j = e.get(i);
            res.put(i, j.clone());
        }
        this.jogadores = res;
    }

    public void setNome(String nome){
        this.nome = nome ;
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && o.getClass() == this.getClass() ) {
            Equipa e = (Equipa) o;
            return this.jogadores.equals(e.getJogadores()) && this.nome.equals(e.getNome());
        } else {
            return false;
        }
    }

    public void insereJogador(Jogador j) {
        this.jogadores.put(j.getID(), j.clone());
    }

    public void removeJogador(int idJ){
        Jogador jogador = jogadores.get(idJ);
        this.jogadores.remove(jogador.getID());
    }


    public Equipa clone(){
        return new Equipa(this);
    }

    public String toString(){
        return  this.nome + this.jogadores;
    }

    public List<String> getLog(){
        ArrayList<String> logEquipa = new ArrayList<String>();
        logEquipa.add("Equipa:"+this.getNome());
        for(Integer idJogador : this.jogadores.keySet()){
            Jogador jogador = this.jogadores.get(idJogador);
            if(jogador instanceof GuardaRedes) {
                GuardaRedes guardaRedes = (GuardaRedes) jogador;
                logEquipa.add(guardaRedes.getLog());
            } else if(jogador instanceof Lateral) {
                Lateral lateral = (Lateral) jogador;
                logEquipa.add(lateral.getLog());
            } else if(jogador instanceof Medio) {
                Medio medio = (Medio) jogador;
                logEquipa.add(medio.getLog());
            } else if(jogador instanceof Defesa) {
                Defesa defesa = (Defesa) jogador;
                logEquipa.add(defesa.getLog());
            } else if(jogador instanceof Avancado) {
                Avancado avancado = (Avancado) jogador;
                logEquipa.add(avancado.getLog());
            }
        }
        return logEquipa;
    }
}
