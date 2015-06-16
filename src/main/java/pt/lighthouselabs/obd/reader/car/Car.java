package pt.lighthouselabs.obd.reader.car;

import pt.lighthouselabs.obd.reader.manufacturer.Manufacturer;
import pt.lighthouselabs.obd.reader.user.User;

public class Car {
    private int id;
    private String modelo;
    private float motor;
    private int ano;
    private String observacao;
    private User usuario;
    private int idFabricante;

    public int getAno() {
        return ano;
    }

    public String getObservacao() {
        return observacao;
    }

    public float getMotor() {
        return motor;
    }

    public String getModelo() {
        return modelo;
    }

    public User getUsuario() {
        return usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMotor(float motor) {
        this.motor = motor;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public int getFabricante() {
        return idFabricante;
    }

    public void setFabricante(int fabricante) {
        this.idFabricante = fabricante;
    }
}
