package locadora;

import java.util.Date;

public class Locacao {
    private String CNH;
    private String placa;
    private Date dataRetirada;
    private Date dataEntrega;
    private float valor;

    public Locacao(String CNH, String placa, Date dataRetirada, Date dataEntrega, float valor) {
        this.CNH = CNH;
        this.placa = placa;
        this.dataRetirada = dataRetirada;
        this.dataEntrega = dataEntrega;
        this.valor = valor;
    }

    public String getCNH() {
        return this.CNH;
    }
    public void setCNH(String CNH) {
        this.CNH = CNH;
    }

    public String getPlaca() {
        return this.placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getDataRetirada() {
        return this.dataRetirada;
    }
    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataEntrega() {
        return this.dataEntrega;
    }
    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public float getValor() {
        return this.valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

}
