package locadora;

public class clientes {

    private Noh inicio;
    private Noh fim;
    
    public clientes (){
        this.inicio = null;
        this.fim = null;
    }

    public void insereInicio(Object info) {
        Noh novo = new Noh(info);
        if (inicio == null){
            inicio = novo;
            fim = novo;
        }
        else {
            novo.setProx(inicio);
            inicio.setAnt(novo);
            inicio = novo;
        }
     }

     public void insereFim(Object info) { 
        Noh novo = new Noh(info);
        if (fim == null){
            inicio = novo;
            fim = novo;
        } else {
            novo.setAnt(fim);
            fim.setProx(novo);
            fim = novo;
        }
    }

    public boolean estahVazia() {
        if(tamanho() == 0){
            return true;
        }
        return false;
     }

     public int tamanho() {         
        Noh p = inicio;
        int i = 0;
        while (p!=null){ 
            p = p.getProx();
            i++;
        }
        return i;
    }

    public boolean remove(Object info) {
        Noh p = inicio;
        p = busca(p, info);
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

    public Noh busca(Noh p, Object info){
        while (p!=null && p.getInfo() != info){//busca
            p = p.getProx();
        }
        return p;
    }

    public void imprime(){
        Noh p = inicio;
        System.out.println("Ascendente: ");
        while (p!=null){
            System.out.println("Aluno: "+p.getInfo().toString());
            p = p.getProx();
        }
        System.out.println("Descendente: ");
        Noh f = fim;
        while (f!=null){
            System.out.println("Aluno: "+f.getInfo().toString());
            f = f.getAnt();
        }
    }
}
