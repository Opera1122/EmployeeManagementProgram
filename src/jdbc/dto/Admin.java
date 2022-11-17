package src.jdbc.dto;

public class Admin {
    private String id;
    private String pw;
    private int birthDate;
    private String name;
    private String address;
    private String tel;

    public Admin(String id, String pw, int birthDate, String name, String address, String tel) {
        this.id = id;
        this.pw = pw;
        this.birthDate = birthDate;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int age) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
