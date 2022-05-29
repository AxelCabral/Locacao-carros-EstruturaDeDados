package locadora;

public class NohCliente {
    private Cliente cliente;
    private NohCliente ant; 
    private NohCliente prox;

    public NohCliente (Cliente cliente){
    this.cliente = cliente;
    this.ant = null;
    this.prox = null;
    }

    public Cliente getCliente() {
        return this.cliente;
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
