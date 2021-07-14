public class Lateral extends Jogador{
    private int cruzamentos;

    public Lateral(){
        this.cruzamentos = 0;
    }

    public Lateral(int id, String n,int nc, int v, int r,int d, int imp, int jc, int rem, int cp, int cr){
        super( id, n,nc,v,r,d,imp,jc,rem,cp);
        this.cruzamentos = cr;
    }

    public Lateral(Lateral l){
        super(l);
        this.cruzamentos = l.getCruzamentos();
    }

    public int getCruzamentos(){return this.cruzamentos;}
    public void setCruzamentos(int c){this.cruzamentos= c;}

    public static Lateral parse(String input, int id){
        String[] campos = input.split(",");
        return new Lateral(
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
        resultado = this.cruzamentos * 0.2 + this.getVelocidade() * 0.15 + this.getResistencia() * 0.15 +
                this.getDestreza() * 0.1 + this.getImpulsao() * 0.125 + this.getJogoCabeca() * 0.1 +
                this.getRemate() * 0.05 + this.getCapacidadePasse() * 0.125;
        return (int) resultado;

    }

    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null && o.getClass() == this.getClass()) return false;
        Lateral l1 = (Lateral) o;
        return super.equals(l1) && l1.getCruzamentos() == this.getCruzamentos() ;
    }

    public Lateral clone(){
        return new Lateral(this);
    }

    public String toString(){
        return super.toString() + " - Lateral "  ;
    }

    public String getLog(){
        return "Lateral:"
                + super.getLog() + ","
                + this.getCruzamentos();
    }
}
