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

    public boolean removerLocacao(String placa) {
        NohLocacao p = inicio;
        p = busca(p, placa);
        if (p==null){ 
            return false;
        }
        if (p.getAnt() == null){ 
            inicio = p.getProx();
            inicio.setAnt(null);
        } 
        else if (p.getProx() == null){ 
            p.getAnt().setProx(null);
            fim = p.getAnt();
        } 
        else { 
            p.getAnt().setProx(p.getProx());
            p.getProx().setAnt(p.getAnt());
        }
        return true;
     }

    public NohLocacao busca(NohLocacao p, String placa){
        while (p!=null && !placa.equals(p.getLocacao().getPlaca())){//busca
            p = p.getProx();
        }
        return p;
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

}
