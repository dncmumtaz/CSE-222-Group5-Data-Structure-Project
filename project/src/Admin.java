package project;
public class Admin extends Person{
    @Override
    public boolean menu(){
        System.out.println("Admin menusu");
        return false;
    }
    public Admin(String name,String password){
        super(name,password);
    }
}
