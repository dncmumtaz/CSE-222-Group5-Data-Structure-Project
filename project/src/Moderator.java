package project;
public class Moderator extends Person{
    @Override
    public boolean menu(){
        System.out.println("Moderator menusu");
        return false;
    }
    public Moderator(String name,String password){
        super(name,password);
    }
}
