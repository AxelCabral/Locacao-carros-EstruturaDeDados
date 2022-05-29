package locadora;

public class NohVeiculos {
    private Veiculo veiculo;
    private NohVeiculos ant; 
    private NohVeiculos prox;

    public NohVeiculos (Veiculo veiculo){
        this.veiculo = veiculo;
        this.ant = null;
        this.prox = null;
    }
    
    public Veiculo getVeiculo() {
        return this.veiculo;
     }
    public NohVeiculos getProx() {
        return this.prox;
     }
    public void setProx(NohVeiculos n) { 
        this.prox = n;
     }
    public NohVeiculos getAnt() {
        return this.ant;
     }
    public void setAnt(NohVeiculos n) { 
        this.ant = n;
     }
    
    
}
