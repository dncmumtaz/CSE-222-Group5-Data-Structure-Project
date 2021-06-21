package project;
public class User extends Person{
    @Override
    public boolean menu(){
        System.out.println("User menusu");
        return false;
    }
    public User(String name,String password){
        super(name,password);
    }
}
