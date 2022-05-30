package locadora;

public class NohLocacao {
    private Locacao Locacao;
    private NohLocacao ant; 
    private NohLocacao prox;

    public NohLocacao (Locacao Locacao){
    this.Locacao = Locacao;
    this.ant = null;
    this.prox = null;
    }

    public Locacao getLocacao() {
        return this.Locacao;
     }
    public NohLocacao getProx() {
        return this.prox;
     }
    public void setProx(NohLocacao n) { 
        this.prox = n;
     }
    public NohLocacao getAnt() {
        return this.ant;
     }
    public void setAnt(NohLocacao n) { 
        this.ant = n;
    }

    @Override
    public String toString() {
        return "[Locacao=" + Locacao;
    }
}
