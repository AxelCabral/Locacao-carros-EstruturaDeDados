package locadora;

public class Veiculos {
    private NohVeiculos inicio;
    private NohVeiculos fim;
    
    public Veiculos(){
        this.inicio = null;
        this.fim = null;
    }

    public int tamanhoVeiculos(){
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
        NohVeiculos ultimo;
        ultimo = null;
        if (inicio == null){
            inicio = novo;
        }
        else{
            for(NohVeiculos i=inicio; i != null; i=i.getProx()){
                ultimo = i;
            }
            ultimo.setProx(novo);
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

    public void imprimeVeiculos(){
        NohVeiculos p = inicio;
        while (p!=null){ 
            System.out.println(p.getVeiculo());
            p = p.getProx();
        }
    }

    public NohVeiculos busca(NohVeiculos p, String placa){
        while (p!=null && !placa.equals((p().getPlaca()){
            p = p.getProx();
        }
        return p;
    }

}
