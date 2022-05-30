package locadora;

class Clientes {

    private NohCliente inicio;
    private NohCliente fim;

    public Clientes(){
        this.inicio = null;
        this.fim = null;
    }

     public void inserirCliente(Cliente Cliente) { 
        NohCliente novo = new NohCliente(Cliente);
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

    public boolean removerCliente(Cliente cliente) {
        NohCliente ant, p;
        p = inicio;
        ant = null;
        while (p!=null && p.getCliente() != cliente){ //busca
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

    public NohCliente busca(NohCliente p, String CPF){
        while (p!=null && !CPF.equals(p.getCliente().getCPF())){//busca
            p = p.getProx();
        }
        return p;
    }

    public void imprime(int asc){
        if(asc == 1){
            NohCliente p = inicio;
            while (p!=null){
                System.out.println("Cliente: "+p.getCliente().toString());
                p = p.getProx();
            }
        }
        else if(asc == 0){
            NohCliente f = fim;
            while (f!=null){
                System.out.println("Cliente: "+f.getCliente().toString());
                f = f.getAnt();
            }
        }

    }

    public void editar(String CPF, String nome, String CNH, String telefone){
        NohCliente p = inicio;
        p = busca(p, CPF);
        Cliente pessoa = p.getCliente();
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

    public NohCliente getInicio() {
        return inicio;
    }

    public NohCliente getFim() {
        return fim;
    }
    
}
