package excelWord.excel.jxls2;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private boolean sign;
    private byte[] imgBytes;
    private List<User> clients = new ArrayList<User>();

    public User(String name) {
        super();
        this.name = name;
    }

    public User(String name, boolean sign, byte[] imgBytes) {
        super();
        this.name = name;
        this.sign = sign;
        this.imgBytes = imgBytes;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getSign() {
        return sign;
    }
    public void setSign(boolean sign) {
        this.sign = sign;
    }
    public byte[] getImgBytes() {
        return imgBytes;
    }
    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public List<User> getClients() {
        return clients;
    }

    public void setClients(List<User> clients) {
        this.clients = clients;
    }
}