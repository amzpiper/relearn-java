package stream;

public class Person {
    private String name; // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area; // 地区
  
    // 构造方法
    public Person(String name, int salary, int age,String sex,String area) {
      this.name = name;
      this.salary = salary;
      this.age = age;
      this.sex = sex;
      this.area = area;
    }

    public int getAge() {
        return age;
    }

    public String getArea() {
        return area;
    }
    
    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "{Age:"+this.getAge()
        +",Area:"+this.getArea()
        +",Name:"+this.getName()
        +",Salary:"+this.getSalary()
        +",Sex:"+this.getSex()
        +"}";
    }
    
}