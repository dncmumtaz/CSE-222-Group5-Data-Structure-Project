import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieList {
    ArrayList<Movie> movieList=new ArrayList<Movie>();
    ArrayList<User> userList=new ArrayList<User>(); //Hashtable olacak
    ArrayList<Admin> adminList=new ArrayList<Admin>(); //Hashtable olacak
    ArrayList<Moderator> moderatorList=new ArrayList<Moderator>(); //Hashtable olacak
    String movieFileName;
    Scanner input=new Scanner(System.in);

    public MovieList(String movieFileName) {
        this.movieFileName=movieFileName;
        try (BufferedReader br = new BufferedReader(new FileReader(movieFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); //It's not working well because of wrong comma readings
                String[] real_values=new String[10]; //Values that we using for store
                String temp=null;
                int real_counter=0;
                for(int i=0;i<values.length;i++){
                    temp=values[i];
                    if(temp.contains("\"")){
                        while(true){
                            i++;
                            if(!values[i].contains("\"")){
                                temp=temp+", "+values[i];
                            }
                            else{
                                temp=temp+", "+values[i];
                                real_values[real_counter]=temp;
                                real_counter++;
                                break;
                            }
                        }

                    }
                    else{
                        real_values[real_counter]=temp;
                        real_counter++;
                    }
                }
                for(String tokens: real_values) {
                    System.out.print(tokens + " -- "); //Writing all values recorded into arraylist
                }
                System.out.println();
                movieList.add(new Movie(real_values[0], real_values[1],real_values[2],real_values[3],
                        real_values[4],real_values[5],real_values[6],real_values[7],real_values[8],real_values[9]));


            }
        }catch(IOException e){
            System.out.println("File Reading Error!");
        }
    }

    public void addNewMovie(){
        int line=movieList.toArray().length-1;
        String lineNo=String.valueOf(line);
        System.out.println("Please Enter the Title of the movie");
        String title=input.nextLine();
        System.out.println("Please Enter the Certificate of the movie");
        String certificate=input.nextLine();
        System.out.println("Please Enter the Duration of the movie");
        String duration=input.nextLine();
        System.out.println("Please Enter the Genre of the movie");
        String genre=input.nextLine();
        System.out.println("Please Enter the Rate of the movie");
        String rate=input.nextLine();
        System.out.println("Please Enter the Metascore of the movie");
        String metaScore=input.nextLine();
        System.out.println("Please Enter the Description of the movie");
        String description=input.nextLine();
        System.out.println("Please Enter the Cast of the movie");
        String cast=input.nextLine();
        System.out.println("Please Enter the Information of the movie");
        String information=input.nextLine();
        movieList.add(new Movie(lineNo,title,certificate,duration,genre,rate,metaScore,description,cast,information));
        updateMovieFile();
    }

    public void updateMovieFile(){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(this.movieFileName))){
            for(int i=0;i<movieList.toArray().length;i++)
                writer.write(movieList.get(i).toString()+"\n");

        }catch(IOException e){
            System.out.println("File Reading Error");
        }
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }



    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

    public ArrayList<Moderator> getModeratorList() {
        return moderatorList;
    }

    public void setModeratorList(ArrayList<Moderator> moderatorList) {
        this.moderatorList = moderatorList;
    }

    public void adminLogin(String adminId,String adminPassword){

    }

    public void userLogin(String userId,String userPassword){

    }

    public void moderatorLogin(String moderatorId,String moderatorPassword){

    }
}
