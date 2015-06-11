package pt.lighthouselabs.obd.reader.car;

import pt.lighthouselabs.obd.reader.manufacturer.Manufacturer;
import pt.lighthouselabs.obd.reader.user.User;

/**
 * Created by hamurabi on 6/11/15.
 */
public class Car {
    private String modelo;
    private float motor;
    private int ano;
    private String observacao;
    private User usuario;
    private Manufacturer fabricante;

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

    public Manufacturer getFabricante() {
        return fabricante;
    }

    public void setFabricante(Manufacturer fabricante) {
        this.fabricante = fabricante;
    }
}
