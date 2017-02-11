package br.com.avantagem.registrodeconta;

/**
 * Created by Administrador on 11/02/2017.
 */

public class Conta {

    public static final String STATUS_P = "PENDENTE";
    public static final String STATUS_F = "FINALIZADO";

    public static final String TIPO_P = "PAGAR";
    public static final String TIPO_R = "RECEBER";

    private Integer id;
    private String descricao;
    private double valor;
    private String tipo;
    private String status;

    public Conta(Integer id, String descricao, double valor, String tipo, String status) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "" + id +
                ":" + descricao  +
                "," + valor +
                "," + tipo  +
                ", " + status;
    }
}
