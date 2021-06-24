package project;
public class User extends Person{
    @Override
    public boolean menu(){
        System.out.println("User menusu);
       // unregister();
        return false;
    }
    public User(String name,String password){
        super(name,password);
    }
}
