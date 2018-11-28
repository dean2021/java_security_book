public class User {

    static {
        System.out.println("static");
    }

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTest(){
        System.out.println("33333");
        return "111";
    }

    public String age;
}
