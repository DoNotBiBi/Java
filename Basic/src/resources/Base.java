package resources;

public class Base {
    private String name;
    private int age;
    private String addr;
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddr() {
        return addr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
    
    // 构造函数
    public Base(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }
    
    public void Print(){
        System.out.println(new StringBuilder().append(getName()).append(getAge()).append(getAddr()).toString());
    }
    

}
