import java.util.List;

public class Student {
    public Student() {
    }
    private int id;
    private String name;
    private List<Phone> ph;
    private Address add;

    public void info(){
        System.out.println("Name: "+name+
                "\nID: "+id+
                "\nNumbers: "+ph+
                "\nAddress: "+add
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPh() {
        return ph;
    }

    public void setPh(List<Phone> ph) {
        this.ph = ph;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "Student\n{" +
                "id = " + id +
                "\nname = " + name +
                "\nphone = " + ph +
                "\naddress = " + add +
                "\n}";
    }
}
