package locadora;

public class NohCliente {
    private cliente info;
    private NohCliente ant; 
    private NohCliente prox;

    public NohCliente (cliente info){
    this.info = info;
    this.ant = null;
    this.prox = null;
    }

    public cliente getInfo() {
        return this.info;
     }
    public NohCliente getProx() {
        return this.prox;
     }
    public void setProx(NohCliente n) { 
        this.prox = n;
     }
    public NohCliente getAnt() {
        return this.ant;
     }
    public void setAnt(NohCliente n) { 
        this.ant = n;
     }
    
}
