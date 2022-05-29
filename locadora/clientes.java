package locadora;

public class clientes {

    private NohCliente inicio;
    private NohCliente fim;

    public clientes (){
        this.inicio = null;
        this.fim = null;
    }

     public void inserirCliente(cliente info) { 
        NohCliente novo = new NohCliente(info);
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
        NohCliente p = inicio;
        int i = 0;
        while (p!=null){ 
            p = p.getProx();
            i++;
        }
        return i;
    }

    public boolean removerCliente(String CPF) {
        NohCliente p = inicio;
        p = busca(p, CPF);
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

    public NohCliente busca(NohCliente p, String CPF){
        while (p!=null && !CPF.equals(p.getInfo().getCPF())){//busca
            p = p.getProx();
        }
        return p;
    }

    public void imprime(int asc){
        if(asc == 1){
            NohCliente p = inicio;
            while (p!=null){
                System.out.println("Cliente: "+p.getInfo().toString());
                p = p.getProx();
            }
        }
        else if(asc == 0){
            NohCliente f = fim;
            while (f!=null){
                System.out.println("Cliente: "+f.getInfo().toString());
                f = f.getAnt();
            }
        }

    }

    public void editar(String CPF, String nome, String CNH, String telefone){
        NohCliente p = inicio;
        p = busca(p, CPF);
        cliente pessoa = p.getInfo();
        if(!nome.equals("")){
            pessoa.setNome(nome);
        }
        if(!CNH.equals("")){
            pessoa.setCNH(CNH);
        }
        if(!telefone.equals("")){
            pessoa.setTelefone(telefone);
        }
    }
    
}
