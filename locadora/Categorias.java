package locadora;

public class Categorias {
    
    private NohCategoria inicio;
    private NohCategoria fim;

    public Categorias(){
        this.inicio = null;
        this.fim = null;
    }

     public void inserirCategoria(Categoria categoria) { 
        NohCategoria novo = new NohCategoria(categoria);
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
        NohCategoria p = inicio;
        int i = 0;
        while (p!=null){ 
            p = p.getProx();
            i++;
        }
        return i;
    }

    public boolean removerCategoria(int id) {
        NohCategoria p = inicio;
        p = busca(p, id);
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

    public NohCategoria busca(NohCategoria p, int id){
        while (p!=null && id != p.getCategoria().getId()){//busca
            p = p.getProx();
        }
        return p;
    }

    public void imprime(int asc){
        if(asc == 1){
            NohCategoria p = inicio;
            while (p!=null){
                System.out.println("Categoria: "+p.getCategoria().toString());
                p = p.getProx();
            }
        }
        else if(asc == 0){
            NohCategoria f = fim;
            while (f!=null){
                System.out.println("Categoria: "+f.getCategoria().toString());
                f = f.getAnt();
            }
        }

    }

    public void editar(int id, String nome){
        NohCategoria p = inicio;
        p = busca(p, id);
        Categoria categoria = p.getCategoria();
        if(!nome.equals("")){
            categoria.setNome(nome);
        }
    }
}
