package src;

public class Employee {
    private int Number;
    private int Age;
    private String Name;
    private String Address;
    private String Email;
    private String Tel;


    public Employee(int number, int age, String name, String address, String email, String tel) {
        Number = number;
        Age = age;
        Name = name;
        Address = address;
        Email = email;
        Tel = tel;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
}
