package pt.lighthouselabs.obd.reader.user;

import pt.lighthouselabs.obd.reader.car.Car;

public class User {
    public static String[] COLUNAS = new String[]{"ID", "NAME", "EMAIL", "PASSWORD", "IDCAR"};
    private String idGoogle;
    private String name;
    private String email;
    private String password;
    private int car;

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }
}
