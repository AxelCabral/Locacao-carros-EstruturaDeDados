package locadora;

public class Locacoes {
    
    private NohLocacao inicio;
    private NohLocacao fim;

    public Locacoes(){
        this.inicio = null;
        this.fim = null;
    }

     public void inserirLocacao(Locacao Locacao) { 
        NohLocacao novo = new NohLocacao(Locacao);
        if (fim == null){
            inicio = novo;
            fim = novo;
        } else {
            novo.setAnt(fim);
            fim.setProx(novo);
            fim = novo;
        }
    }

     public int tamanho() {         
        NohLocacao p = inicio;
        int i = 0;
        while (p!=null){ 
            p = p.getProx();
            i++;
        }
        return i;
    }

    public boolean removerLocacao(Locacao locacao) {
        NohLocacao ant, p;
        p = inicio;
        ant = null;
        while (p!=null && p.getLocacao() != locacao){ //busca
            ant = p;
            p = p.getProx();
        }
        if (p==null)
        return false;
        if (ant==null)
        inicio = p.getProx();
        else
        ant.setProx(p.getProx());
        return true;
     }

    public NohLocacao busca(NohLocacao p, String placa){
        while (p!=null && !placa.equals(p.getLocacao().getPlaca())){//busca
            p = p.getProx();
        }
        return p;
    }

    public boolean existeVeiculoLocado(NohLocacao p, String placa){
        while (p!=null && !placa.equals(p.getLocacao().getPlaca())){//busca
            p = p.getProx();
        }
        return p != null;
    }

    public boolean existeClienteVinculado(NohLocacao p, String cnh){
        while (p!=null && !cnh.equals(p.getLocacao().getCNH())){//busca
            p = p.getProx();
        }
        return p != null;
    }

    public void imprime(int asc){
        if(asc == 1){
            NohLocacao p = inicio;
            while (p!=null){
                System.out.println("Locacao: "+p.getLocacao().toString());
                p = p.getProx();
            }
        }
        else if(asc == 0){
            NohLocacao f = fim;
            while (f!=null){
                System.out.println("Locacao: "+f.getLocacao().toString());
                f = f.getAnt();
            }
        }

    }

    public NohLocacao getInicio() {
        return inicio;
    }

    public NohLocacao getFim() {
        return fim;
    }
}
