package pt.lighthouselabs.obd.reader.manufacturer;

public class Manufacturer {
    int id;
    String name;

    public String getNome() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
