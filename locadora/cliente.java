package locadora;

public class Cliente{
    private String nome;
    private String CNH;
    private String telefone;
    private String CPF;

    public Cliente(String nome, String CNH, String telefone, String CPF) {
        this.nome = nome;
        this.CNH = CNH;
        this.telefone = telefone;
        this.CPF = CPF;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNH() {
        return this.CNH;
    }
    public void setCNH(String CNH) {
        this.CNH = CNH;
    }

    public String getTelefone() {
        return this.telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCPF() {
        return this.CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

}
