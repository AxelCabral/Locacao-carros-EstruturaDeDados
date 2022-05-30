package locadora;

public class Veiculos {
    private NohVeiculos inicio;
    private NohVeiculos fim;
    
    public Veiculos(){
        this.inicio = null;
        this.fim = null;
    }

    public int tamanho(){
        NohVeiculos p = inicio;
        int i = 0;
        while (p!=null){
            p = p.getProx();
            i++;
        }
        return i;
    }

    public void inserirVeiculo(Veiculo veiculo){
        NohVeiculos novo = new NohVeiculos(veiculo);
        /*NohVeiculos ultimo;
        ultimo = null;
        if (inicio == null){
            inicio = novo;
        }
        else{
            for(NohVeiculos i=inicio; i != null; i=i.getProx()){
                ultimo = i;
            }
            ultimo.setProx(novo);
        }*/
        if (fim == null){
            inicio = novo;
            fim = novo;
        } else {
            novo.setAnt(fim);
            fim.setProx(novo);
            fim = novo;
        }
    }

    public boolean removerVeiculo(Veiculo veiculo) {
        NohVeiculos ant, p;
        p = inicio;
        ant = null;
        while (p!=null && p.getVeiculo() != veiculo){ //busca
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

    public NohVeiculos busca(NohVeiculos p, String placa){
        while (p!=null && !placa.equals(p.getVeiculo().getPlaca())){//busca
            p = p.getProx();
        }
        return p;
    }


    public boolean existeVeiculoPorCategoria(NohVeiculos p, int id){
        while (p!=null && p.getVeiculo().getCategoria().getId() != id ){//busca
            p = p.getProx();
        }
        return p != null;
    }

    public void imprime(int asc){
        if(asc == 1){
            NohVeiculos p = inicio;
            while (p!=null){
                System.out.println("Veiculo: "+p.getVeiculo().toString());
                p = p.getProx();
            }
        }
        else if(asc == 0){
            NohVeiculos f = fim;
            while (f!=null){
                System.out.println("Veiculo: "+f.getVeiculo().toString());
                f = f.getAnt();
            }
        }
    }

    public void editar(String placa, String modelo, String marca, int ano, int potencia, int num_lugares, Categoria categoria){
        NohVeiculos p = inicio;
        p = busca(p, placa);
        Veiculo veiculo = p.getVeiculo();
        if(!modelo.equals("") && !marca.equals("") && ano != 0 && potencia != 0 &&
        num_lugares != 0 && categoria != null){
            veiculo.setModelo(modelo);
            veiculo.setMarca(marca);
            veiculo.setAno(ano);
            veiculo.setPotencia(potencia);
            veiculo.setNum_lugares(num_lugares);
            veiculo.setCategoria(categoria);
        }
    }

    public NohVeiculos getInicio() {
        return inicio;
    }

    public void setInicio(NohVeiculos inicio) {
        this.inicio = inicio;
    }

    public NohVeiculos getFim() {
        return fim;
    }

    public void setFim(NohVeiculos fim) {
        this.fim = fim;
    }
}
