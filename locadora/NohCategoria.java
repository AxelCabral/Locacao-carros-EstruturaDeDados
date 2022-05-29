package locadora;

public class NohCategoria {
    private Categoria categoria;
    private NohCategoria ant; 
    private NohCategoria prox;

    public NohCategoria (Categoria categoria){
    this.categoria = categoria;
    this.ant = null;
    this.prox = null;
    }

    public Categoria getCategoria() {
        return this.categoria;
     }
    public NohCategoria getProx() {
        return this.prox;
     }
    public void setProx(NohCategoria n) { 
        this.prox = n;
     }
    public NohCategoria getAnt() {
        return this.ant;
     }
    public void setAnt(NohCategoria n) { 
        this.ant = n;
     }
    
}