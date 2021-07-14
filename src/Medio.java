public class Medio extends Jogador{
    private int recuperacao_bolas;

    public Medio(){
        this.recuperacao_bolas = 0;
    }

    public Medio(int id, String n,int nc, int v, int r,int d, int imp, int jc, int rem, int cp, int rb){
        super( id, n,nc,v,r,d,imp,jc,rem,cp);
        this.recuperacao_bolas = rb;
    }

    public Medio(Medio m){
        super(m);
        this.recuperacao_bolas = m.getRecuperacaoBola();
    }

    public static Medio parse(String input, int id){
        String[] campos = input.split(",");
        return new Medio(
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

    public int getRecuperacaoBola(){return this.recuperacao_bolas;}
    public void setRecuperacao_bolas(int rb){this.recuperacao_bolas= rb;}

    public int calculahabilidade(){
        double resultado;
        resultado = this.recuperacao_bolas * 0.2 + this.getVelocidade() * 0.1 + this.getResistencia() * 0.1 +
                this.getDestreza() * 0.125 + this.getImpulsao() * 0.1 + this.getJogoCabeca() * 0.125 +
                this.getRemate() * 0.05 + this.getCapacidadePasse() * 0.20;
        return (int) resultado;

    }

    public boolean equals(Medio m){
        if(m == this) return true;
        if(m == null && m.getClass() == this.getClass()) return false;
        Medio m1 = (Medio) m;
        return super.equals(m1) && m1.getRecuperacaoBola() == this.getRecuperacaoBola();
    }

    public Medio clone(){
        return new Medio(this);
    }

    public String toString(){
        return super.toString() + " - Medio " ;
    }

    public String getLog(){
        return "Medio:"
                + super.getLog() + ","
                + this.getRecuperacaoBola();
    }
}
