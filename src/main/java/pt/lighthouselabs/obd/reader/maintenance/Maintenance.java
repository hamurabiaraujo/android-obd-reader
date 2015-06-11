package pt.lighthouselabs.obd.reader.maintenance;

import java.util.Date;

import pt.lighthouselabs.obd.reader.car.Car;

/**
 * Created by hamurabi on 6/11/15.
 */
public class Maintenance {
    private Date data;
    private int quilometragem;
    private String oficina;
    private Car carro;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public Car getCarro() {
        return carro;
    }

    public void setCarro(Car carro) {
        this.carro = carro;
    }
}
