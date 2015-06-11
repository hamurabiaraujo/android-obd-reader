package pt.lighthouselabs.obd.reader.user;

import pt.lighthouselabs.obd.reader.car.Car;

/**
 * Created by hamurabi on 6/11/15.
 */
public class User {
    public static String[] COLUNAS = new String[]{"ID", "NOME", "EMAIL", "SENHA", "CARRO"};
    private String idGoogle;
    private String nome;
    private String email;
    private String senha;
    private Car carro;

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Car getCarro() {
        return carro;
    }

    public void setCarro(Car carro) {
        this.carro = carro;
    }
}
