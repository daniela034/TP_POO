import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Jogador implements Serializable {
    private int id;
    private int numeroCamisola;
    private String nome;
    private int impulsao;
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int jogocabeca;
    private int remate;
    private int capacidade_passe;
    private List<Equipa> historicoEquipas;
    private int habilidade;

    public Jogador(){
        this.id = 0;
        this.numeroCamisola = 0;
        this.nome = "";
        this.impulsao = 0;
        this.velocidade = 0;
        this.resistencia = 0;
        this.destreza = 0;
        this.jogocabeca = 0;
        this.remate = 0;
        this.capacidade_passe = 0;
        this.historicoEquipas = new ArrayList<>();
    }

    public Jogador(int id, String n,int nc, int v, int r,int d, int imp, int jc, int rem, int cp){
        this.id = id;
        this.numeroCamisola = nc;
        this.nome = n;
        this.velocidade = v;
        this.resistencia = r;
        this.destreza =d;
        this.impulsao = imp;
        this.jogocabeca = jc;
        this.remate = rem;
        this.capacidade_passe = cp;
        this.historicoEquipas = new ArrayList<>();
    }

    public Jogador(Jogador j){
        this.id = j.getID();
        this.numeroCamisola = j.getNumeroCamisola();
        this.nome = j.getNome();
        this.impulsao = j.getImpulsao();
        this.velocidade = j.getVelocidade();
        this.resistencia = j.getResistencia();
        this.destreza = j.getDestreza();
        this.jogocabeca = j.getJogoCabeca();
        this.remate = j.getRemate();
        this.capacidade_passe = j.getCapacidadePasse();
        this.historicoEquipas = j.getHistoricoEquipas();
    }

    public int getID(){return  this.id;}
    public int getNumeroCamisola(){return this.numeroCamisola;}
    public String getNome(){return this.nome;}
    public int getImpulsao() { return this.impulsao; }
    public int getVelocidade() {return this.velocidade; }
    public int getResistencia(){ return  this.resistencia;}
    public int getDestreza(){return this.destreza;}
    public int getJogoCabeca(){return this.jogocabeca;}
    public int getRemate(){return this.remate;}
    public int getCapacidadePasse(){return this.capacidade_passe;}
    public List<Equipa> getHistoricoEquipas() {
        List<Equipa> r = new ArrayList<>();
        Iterator<Equipa> it = this.historicoEquipas.iterator();
        while(it.hasNext()){
            r.add((Equipa) it.next().clone());
        }
        return r;
    }

    public void setId(int id){this.id = id;}
    public void setNumeroCamisola(int nc){this.numeroCamisola = nc;}
    public void setNome(String n){this.nome = n;}
    public void setImpulsao(int i){this.impulsao = i;}
    public void setVelocidade(int v){this.velocidade = v;}
    public void setResistencia(int res){this.resistencia = res;}
    public void setDestreza(int d){this.destreza = d;}
    public void setJogocabeca(int jc){this.jogocabeca = jc;}
    public void setRemate(int rem){this.remate = rem;}
    public void setCapacidade_passe(int cp){this.capacidade_passe = cp;}
    public void setHistoricoEquipas(List<Equipa> hE){
        this.historicoEquipas = new ArrayList<>();
        Iterator<Equipa> it = hE.iterator();
        while(it.hasNext()){
            this.historicoEquipas.add((Equipa) it.next().clone());
        }

    }
    public void insereEquipaHistorico(String equipa){
        this.historicoEquipas.add((Equipa.parse(equipa)));
    }

    public String historicoToString(){
        String equipas = "";
        Iterator<Equipa> it = this.historicoEquipas.iterator();
        while(it.hasNext()){
            equipas += it.next().getNome();
        }
        return equipas;
    }

    public int calculaH(){
        if(this instanceof Avancado){
            Avancado jogador = (Avancado) this;
             return jogador.calculahabilidade();
        }
        else if(this instanceof Defesa){
             Defesa jogador = (Defesa) this;
            return jogador.calculahabilidade();
        }
        else if(this instanceof GuardaRedes){
             GuardaRedes jogador = (GuardaRedes) this;
            return jogador.calculahabilidade();
        }
        else if(this instanceof Lateral){
             Lateral jogador = (Lateral) this;
            return jogador.calculahabilidade();
        }
        else if(this instanceof Medio) {
             Medio jogador = (Medio) this;
            return jogador.calculahabilidade();
        }
        else return 0;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o!=null && o.getClass() == this.getClass()) return false;
        Jogador jo = (Jogador) o;
        return this.getID() == jo.getID() && this.getNumeroCamisola() == jo.getNumeroCamisola() && this.getNome().equals(jo.getNome()) &&  this.getVelocidade() == jo.getVelocidade() &&
                this.getResistencia() == jo.getResistencia() && this.getDestreza() == jo.getDestreza() &&
                this.getJogoCabeca() == jo.getJogoCabeca() && this.getRemate() == jo.getRemate() &&
                this.getCapacidadePasse() == jo.getCapacidadePasse()  &&
                this.getHistoricoEquipas().equals(jo.getHistoricoEquipas());
    }

    public Jogador clone(){
        return new Jogador(this);
    }

    public String toString(){
        return this.id  +" - " + this.nome+ " - "+this.numeroCamisola + " - " + this.historicoToString() + " - " + this.calculaH();
    }

    public String getLog(){
        return this.getNome() + ","
                + this.getNumeroCamisola() + ","
                + this.getVelocidade() + ","
                + this.getResistencia() + ","
                + this.getDestreza() + ","
                + this.getImpulsao() + ","
                + this.getJogoCabeca() + ","
                + this.getRemate() + ","
                + this.getCapacidadePasse();
    }
}
