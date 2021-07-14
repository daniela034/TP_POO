public class Defesa extends Jogador{
    private int posse_bola;

    public Defesa(){
        this.posse_bola = 0;
    }

    public Defesa(int id, String n,int nc, int v, int r,int d, int imp, int jc, int rem, int cp, int pb){
        super(id, n,nc,v,r,d,imp,jc,rem,cp);
        this.posse_bola = pb;
    }

    public Defesa(Defesa d){
        super(d);
        this.posse_bola = d.getPosseBola();
    }

    public int getPosseBola(){return this.posse_bola;}
    public void setPosseBola(int pb){this.posse_bola = pb;}

    public static Defesa parse(String input, int id){
        String[] campos = input.split(",");
        return new Defesa(
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
        resultado = this.posse_bola * 0.3 + this.getVelocidade() * 0.15 + this.getResistencia() * 0.15 +
                this.getDestreza() * 0.1 + this.getImpulsao() * 0.1 + this.getJogoCabeca() * 0.05 +
                this.getRemate() * 0.05 + this.getCapacidadePasse() * 0.1;
        return (int) resultado;

    }
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null && o.getClass() == this.getClass()) return false;
        Defesa d1 = (Defesa) o;
        return super.equals(d1) && d1.getPosseBola() == this.getPosseBola();
    }

    public Defesa clone(){
        return new Defesa(this);
    }

    public String toString(){
        return super.toString() + " - Defesa " ;
    }

    public String getLog(){
        return "Defesa:"
                + super.getLog() + ","
                + this.getPosseBola();
    }
}
