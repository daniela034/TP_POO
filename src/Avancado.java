public class Avancado extends Jogador{
    public int forca;
    public int agilidade;
    public int golos;

    public Avancado(){
        this.forca = 0;
        this.agilidade = 0;
        this.golos = 0;
    }

    public Avancado(int id, String n,int nc, int v, int r,int d, int imp, int jc, int rem, int cp ,int f, int ag, int g){
        super( id, n,nc,v,r,d,imp,jc,rem,cp);
        this.forca = f;
        this.agilidade = ag;
        this.golos = g;
    }

    public Avancado(Avancado a){
        super(a);
        this.forca = a.getForca();
        this.agilidade = a.getAgilidade();
        this.golos = a.getGolos();

    }

    public int getForca(){return this.forca;}
    public int getAgilidade(){return this.agilidade;}
    public int getGolos(){return this.golos;}
    public void setForca(int f){this.forca= f;}
    public void setAgilidade(int a){this.agilidade= a;}
    public void setGolos(int g){this.golos= g;}

    public static Avancado parse(String input, int id){
        String[] campos = input.split(",");
        return new Avancado(id, campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]),
                Integer.parseInt(campos[10]),
                Integer.parseInt(campos[11]));
    }

    public int calculahabilidade(){
        double resultado;
        resultado = this.forca * 0.1 + this.golos * 0.1 + this.agilidade * 0.1+ this.getVelocidade() * 0.1 + this.getResistencia() * 0.1 +
                this.getDestreza() * 0.1 + this.getImpulsao() * 0.1 + this.getJogoCabeca() * 0.1 +
                this.getRemate() * 0.1 + this.getCapacidadePasse() * 0.1;
        return (int) resultado;

    }

    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null && o.getClass() == this.getClass()) return false;
        Avancado a1 = (Avancado) o;
        return super.equals(a1) && a1.getForca() == this.getForca() && a1.getAgilidade() == this.getAgilidade() &&
        a1.getGolos() == this.getGolos();
    }

    public Avancado clone(){
        return new Avancado(this);
    }

    public String toString(){
        return super.toString() + " - Avançado " ;
    }

    public String getLog(){
        return "Avancado:"
                + super.getLog() + ","
                + this.getForca() + ","
                + this.getAgilidade() + ","
                + this.getGolos();
    }
}
