package src;

public class Admin {
    private String Id;
    private String Pw;
    private int Age;
    private String Name;
    private String Address;
    private String Tel;

    public Admin(String id, String pw, int age, String name, String address, String tel) {
        Id = id;
        Pw = pw;
        Age = age;
        Name = name;
        Address = address;
        Tel = tel;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPw() {
        return Pw;
    }

    public void setPw(String pw) {
        Pw = pw;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
}
