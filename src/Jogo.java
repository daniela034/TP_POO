import java.time.LocalDate;
import java.util.*;

public class Jogo {
    private String equipaCasa;
    private String equipaFora;
    private int golosCasa;
    private int golosFora;
    private LocalDate date;
    private List<Integer> jogadoresCasa;
    private List<Integer> jogadoresFora;
    Map<Integer, Integer> substituicoesCasa = new HashMap<>();
    Map<Integer, Integer> substitucoesFora = new HashMap<>();

    public Jogo(){
        this.equipaCasa = "";
        this.equipaFora = "";
        this.golosCasa = 0;
        this.golosFora = 0;
        this.date = LocalDate.now();
        this.jogadoresCasa = new ArrayList<>();
        this.jogadoresFora = new ArrayList<>();
        this.substitucoesFora = new HashMap<>();
        this.substituicoesCasa = new HashMap<>();
    }
    public Jogo (String ec, String ef, int gc, int gf, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        this.equipaCasa = ec;
        this.equipaFora = ef;
        this.golosCasa = gc;
        this.golosFora = gf;
        this.date = d;
        this.jogadoresCasa = new ArrayList<>(jc);
        this.jogadoresFora = new ArrayList<>(jf);
        this.substituicoesCasa = new HashMap<>(sc);
        this.substitucoesFora = new HashMap<>(sf);
    }

    //Acabar gets e sets, equals e tostring
    public Jogo(Jogo j ){
        this.equipaCasa = j.getEquipaCasa();
        this.equipaFora = j.getEquipaFora();
        this.golosCasa = j.getGolosCasa();
        this.golosFora = j.getGolosFora();
        this.date = j.getDate();
        this.jogadoresCasa = j.getJogadoresCasa();
        this.jogadoresFora = j.getJogadoresFora();
        this.substituicoesCasa = j.getSubstituicoesCasa();
        this.substitucoesFora = j.getSubstituicoesFora();
    }
    public String getEquipaCasa(){return this.equipaCasa;}
    public String getEquipaFora(){return this.equipaFora;}
    public int getGolosCasa(){return this.golosCasa;}
    public int getGolosFora(){return this.golosFora;}
    public LocalDate getDate(){ return this.date;}

    public List<Integer> getJogadoresCasa() {
        List<Integer> r = new ArrayList<>();
        Iterator<Integer> it = this.jogadoresCasa.iterator();
        while(it.hasNext()){
            r.add((Integer) it.next());
        }
        return r;
    }
    public List<Integer> getJogadoresFora(){
        List<Integer> r = new ArrayList<>();
        Iterator<Integer> it = this.jogadoresFora.iterator();
        while(it.hasNext()){
            r.add((Integer) it.next());
        }
        return r;
    }

    public Map<Integer, Integer> getSubstituicoesFora() {
        Map<Integer,Integer> res = new HashMap<>();
        for(Integer i : this.substitucoesFora.keySet()) {
            Integer j = this.substitucoesFora.get(i);
            res.put(i, j);
        }
        return res;
    }

    public Map<Integer, Integer> getSubstituicoesCasa() {
        Map<Integer,Integer> res = new HashMap<>();
        for(Integer i : this.substituicoesCasa.keySet()) {
            Integer j = this.substituicoesCasa.get(i);
            res.put(i, j);
        }
        return res;
    }


    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            //jogadores da equipa da casa
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            //substituições da equipa da casa
            String[] sub = campos[i].split("->");
            if(sub.length == 2) {
                subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
            }
        }
        for (int i = 19; i < 30; i++){
            //jogadores da equipa de fora
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            //substituições da equipa de fora
            String[] sub = campos[i].split("->");
            if(sub.length == 2){
                subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
            }
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }

    public Jogo clone(){return new Jogo(this);}
    public String toString(){
        return this.equipaCasa +", " + this.equipaFora + "," + this.golosCasa + "," + this.golosFora +
                ", " + this.date + "," + this.jogadoresCasa + "," + this.substituicoesCasa +
                ", " + this.jogadoresFora + ", " + this.substitucoesFora;
    }
    /*
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null && o.getClass() == this.getClass()) return false;
        Jogo j = (Jogo) o;
        return this.daCasa == j.getDaCasa() && this.visitante == j.getVisitante() && this.vencedora == j.getVencedora();
    }
    */

    public String jogadoresCasaToString(){
        Iterator<Integer> it = this.jogadoresCasa.iterator();
        String jogadoresCasaString = "";
        while(it.hasNext()) {
            jogadoresCasaString += it.next() + ",";
        }
        return jogadoresCasaString;
    }

    public String jogadoresForaToString(){
        Iterator<Integer> it = this.jogadoresFora.iterator();
        String jogadoresForaString = "";
        while(it.hasNext()) {
            jogadoresForaString += it.next() + ",";
        }
        return jogadoresForaString;
    }

    public String substituicoesCasaToString() {
        String substituicoesCasaString = "";
        for(Integer idSaiu : this.substituicoesCasa.keySet()){
            int idEntrou = this.substituicoesCasa.get(idSaiu);
            substituicoesCasaString += idSaiu + "->" + idEntrou + ",";
        }
        for(int i = this.substituicoesCasa.size(); i < 3; i++){
            substituicoesCasaString += " ,";
        }
        return substituicoesCasaString;
    }

    public String substituicoesForaToString() {
        String substituicoesForaString = "";
        for(Integer idSaiu : this.substitucoesFora.keySet()){
            int idEntrou = this.substitucoesFora.get(idSaiu);
            substituicoesForaString += idSaiu + "->" + idEntrou + ",";
        }
        for(int i = this.substitucoesFora.size(); i < 3; i++) {
            substituicoesForaString += " ,";
        }
        return substituicoesForaString;
    }

    public String getLog(){
        return "Jogo:" + this.equipaCasa + ","
                + this.equipaFora + ","
                + this.golosCasa + ","
                + this.golosFora + ","
                + this.date.getYear() + "-" + this.date.getMonthValue() + "-" + this.date.getDayOfMonth() + ","
                + this.jogadoresCasaToString()
                + this.substituicoesCasaToString()
                + this.jogadoresForaToString()
                + this.substituicoesForaToString();
    }

}
