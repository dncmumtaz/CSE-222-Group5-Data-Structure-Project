package project;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;
/**
 * Class that holds all system
 */
public class MovieList {
    ArrayList<Movie> movieList=new ArrayList<Movie>();
    HashSet<Audience> audienceList = new HashSet<Audience>(); //Hashtable olacak
    HashSet<Admin> adminList=new HashSet<Admin>(); //Hashtable olacak
    HashSet<Moderator> moderatorList=new HashSet<Moderator>(); //Hashtable olacak
    String movieFileName;
    private static final String adminFile = "Admins.txt";
    private static final String modFile = "Moderators.txt";
    private static final String userFile = "Audience.txt";
    Scanner input=new Scanner(System.in);
    /**
     * takes movies from file
     * @param movieFileName
     */
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
               /* for(String tokens: real_values) {
                    System.out.println(tokens + " -- "); //Writing all values recorded into arraylist
                }
                System.out.println();
                */
                movieList.add(new Movie(real_values[0], real_values[1],real_values[2],real_values[3],
                        real_values[4],real_values[5],real_values[6],real_values[7],real_values[8],real_values[9]));


            }
        }catch(IOException e){
            System.out.println("File Reading Error!");
        }
        getUsersOfSystem();
        while(menu());
        System.out.println("See you later");
        
    }
    /**
     * replaces all " " with "."
     * @return modified string
     */
    private String removeSpaceChar(String str){
        if(str == null)
            return str;
        return str.replaceAll(" ",".");
    }
    /**
     * registers user to the system if he/she have not yet
     */
    private void registerAudience(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = sc.nextLine();
        boolean flag = name.contains(" ");
        if(flag){
            name = removeSpaceChar(name);
            System.out.println("All spaces are replaced with '.'");
            System.out.println("Your new name is : "+name);
        }
        
        if(!nameTaken(name)){
            System.out.print("Please enter your password: ");
            String pw = sc.nextLine();
            if(pw.contains(" ")){
                pw = removeSpaceChar(pw);
                System.out.println("All spaces are replaced with '.'");
                System.out.println("Your new name is : "+pw);
            }
            Audience audience = new Audience(name,pw); 
            audience.setMovieList(this);
            //if(!userList.contains(user)){
        
            audienceList.add(audience);
            try{
                FileWriter fw = new FileWriter(new File(userFile),true); // append to the end of file
                fw.append(name);
                fw.append(" ");
                fw.append(pw);
                fw.append("\n");
                fw.close();
            } catch(IOException e){
                System.out.println(e);
            }
        } else{
            System.out.println("This username has been taken");
        }

    }
    /**
     * checks if there is any user who has taken that name
     * @return true if the name has been taken
     */
    private boolean nameTaken(String name){
        //Iterator<User> iter = userList.iterator();
        for(Audience audience : audienceList){
            if(name.equals(audience.getName())){
                return true;
            }
        }
        return false;
    }
    /**
     * Main menu for program
     * @return returns true if the user does not press 3)Exit
     */
    private boolean menu(){
        System.out.println("1) Register");
        System.out.println("2) Login");
        System.out.println("3) Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice == 1){
            registerAudience();
        } else if(choice == 2){
            System.out.println("1) Login as Admin");
            System.out.println("2) Login as Moderator");
            System.out.println("3) Login as User");
            System.out.println("4) Exit");
            int logChoice = sc.nextInt();
            boolean flag = false;
            if(logChoice == 1 || logChoice == 2 || logChoice == 3){
                Person p = login(logChoice); 
                if(p == null){
                    System.out.println("Please register first before logining to the system");
                } else {
                    while(p.menu());
                }
                
            } else if(logChoice == 4){
                System.out.println("Exited"); 
            } else {
                System.out.println("Invalid input");
            }
            
        } else if(choice == 3)
            return false;
        else {
            System.out.println("Invalid input try again");
        }
        return true;
        
    }

    /**
     * if logChoice == 1 Person who logs in is Admin
     *if logChoice == 2 Person who logs in is Moderator
     *if logChoice == 3 Person who logs in is User
     *  @param logChoice shows who logs in 
     * @return Person who logs in , if login is not successfull then returns null
     */
    private Person login(int logChoice){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = sc.nextLine();
        System.out.print("Please enter your password: ");
        String pw = sc.nextLine();
        if(logChoice == 1){
            Admin admin = new Admin(name,pw);
            if(adminList.contains(admin)){
                for (Admin obj : adminList) {
                    if (obj.equals(admin)){
                        return obj;
                    }
                        
                        
                } 
            }
        } else if(logChoice == 2){
            Moderator mod = new Moderator(name,pw);
            if(moderatorList.contains(mod)){
                for(Moderator obj : moderatorList){
                    if (obj.equals(mod)) 
                        return obj;
                }
            }
        } else {
            Audience audience = new Audience(name,pw);
            if(audienceList.contains(audience)){
                for(Audience obj : audienceList){
                    if(obj.equals(audience)){
                        return obj;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * takes users from files
     */
    private void getUsersOfSystem(){
        try {
            File myObj = new File(adminFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              String[] namePass = data.split(" ");
              Admin added = new Admin(namePass[0],namePass[1]);
              added.setMovieList(this);
              adminList.add(added);

            }
            myReader.close();

            myObj = new File(modFile);
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] namePass = data.split(" ");
                Moderator added = new Moderator(namePass[0],namePass[1]);
                added.setMovieList(this);
                moderatorList.add(added);
                
            }
            myReader.close();

            myObj = new File(userFile);
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] namePass = data.split(" ");
                //System.out.println("-"+data+"-");
                Audience added = new Audience(namePass[0],namePass[1]);
                added.setMovieList(this);
                audienceList.add(added);
                
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          /*System.out.println("Admins");
          Iterator<Admin> iter1 = adminList.iterator();
          while(iter1.hasNext()){
             System.out.println(iter1.next());
          }
         
          System.out.println("Moderators");
          Iterator<Moderator> iter2 = moderatorList.iterator();
          
          while(iter2.hasNext()){
            System.out.println(iter2.next());
          }

          System.out.println("Users");
          Iterator<User> iter3 = userList.iterator();
          while(iter3.hasNext()){
             
             System.out.println(iter3.next());
          }
          */
    }
    void updateUsers(Person p){
        String file = null;
        if(p instanceof Audience){
            file = userFile;
        } else if(p instanceof Moderator){
            file = modFile;    
        } else { // person is admin
            file = adminFile;
        }
        savePeople(file);
    }
    private void savePeople(String file){
        try{
            if(file.equals(userFile)){
                Iterator<Audience> iter = audienceList.iterator();
                FileWriter fw = new FileWriter(new File(userFile));
                while(iter.hasNext()){
                    fw.append(iter.next().toString());
                    fw.append("\n");
                }
                fw.close();
            } else if(file.equals(adminFile)){
                Iterator<Admin> iter = adminList.iterator();
                FileWriter fw = new FileWriter(new File(adminFile));
                while(iter.hasNext()){
                    fw.append(iter.next().toString());
                    fw.append("\n");
                }
                fw.close();
            } else if(file.equals(modFile)){
                Iterator<Moderator> iter = moderatorList.iterator();
                FileWriter fw = new FileWriter(new File(modFile));
                while(iter.hasNext()){
                    fw.append(iter.next().toString());
                    fw.append("\n");
                }
                fw.close();
            }
        } catch(IOException e){
            System.out.println(e);
        }
    }
    /**
     * adds new movie to the system
     */
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
    /**
     * updates movie file
     */
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

    public HashSet<Audience> getAudienceList() {
        return audienceList;
    }



    public void setUserList(HashSet<Audience> audienceList) {
        this.audienceList = audienceList;
    }

    public HashSet<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(HashSet<Admin> adminList) {
        this.adminList = adminList;
    }

    public HashSet<Moderator> getModeratorList() {
        return moderatorList;
    }

    public void setModeratorList(HashSet<Moderator> moderatorList) {
        this.moderatorList = moderatorList;
    }
/*
    public boolean adminLogin(String adminId,String adminPassword){
        return true;
    }

    public boolean userLogin(String userId,String userPassword){
        return true;
    }

    public boolean moderatorLogin(String moderatorId,String moderatorPassword){
        return true;
    }
*/
}
