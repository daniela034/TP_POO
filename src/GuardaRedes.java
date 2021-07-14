public class GuardaRedes extends Jogador {
    private int elasticidade;

    public GuardaRedes(){
        this.elasticidade = 0;
    }

    public GuardaRedes(int id, String n,int nc, int v, int r,int d, int imp, int jc, int rem, int cp, int elasticidade){
        super( id,n,nc,v,r,d,imp,jc,rem,cp);
        this.elasticidade = elasticidade;
    }

    public GuardaRedes(GuardaRedes g){
        super(g);
        this.elasticidade = g.getElasticidade();
    }

    public int getElasticidade(){return this.elasticidade;}
    public void setElasticidade(int elas){this.elasticidade = elas;}

    public static GuardaRedes parse(String input, int id){
        String[] campos = input.split(",");
        return new GuardaRedes(
                id,
                campos[0],
                Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }

    public int calculahabilidade(){
        double resultado;
        resultado = this.elasticidade * 0.3 + this.getVelocidade() * 0.075 + this.getResistencia() * 0.075 +
                this.getDestreza() * 0.15 + this.getImpulsao() * 0.15 + this.getJogoCabeca() * 0.075 +
                this.getRemate() * 0.075 + this.getCapacidadePasse() * 0.1;
        return (int) resultado;

    }

    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null && o.getClass() == this.getClass()) return false;
        GuardaRedes g1 = (GuardaRedes) o;
        return super.equals(g1) && g1.getElasticidade() == this.getElasticidade();
    }

    public GuardaRedes clone(){
        return new GuardaRedes(this);
    }

    public String toString(){
        return super.toString() + " - Guarda Redes "  ;
    }

    public String getLog(){
        return "Guarda-Redes:"
                + super.getLog() + ","
                + this.getElasticidade();
    }
}
