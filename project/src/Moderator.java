import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Moderator extends Person{
    @Override
    public boolean menu(){
        Scanner input = new Scanner(System.in);
        System.out.println("1)Add Movie Suggestion\n2)Remove Movie Suggestion\n3)Delete Inappropriate Comment"+
                "\n4)Set Info About a Movie\n5)Exit");
        String input_=new String();
        int numberOfMovie;
        ArrayList<Movie>temp = MovieList.getMovieList();
        input_=input.nextLine();
        switch (input_){
            case "1":
                suggestMovie();
                return true;
            case "2":
                checkListOfMovies();
                System.out.println("Enter Number of Movie Which Is Suggested For Remove");
                numberOfMovie=input.nextInt();
                //call movie from movelist with index value
                if(numberOfMovie>0 && numberOfMovie<temp.size()) {
                    Movie editted = temp.get(numberOfMovie - 1);
                    suggestRemovingMovie(editted);
                }
                else
                    System.out.println("Wrong Choice");
                return true;

            case "3":
                checkListOfMovies();
                System.out.println("Enter Number of Movie Which You Want to See Comments");
                numberOfMovie=input.nextInt();
                //call movie from movelist with index value
                if(numberOfMovie>0 && numberOfMovie<temp.size()) {
                    Movie editted = temp.get(numberOfMovie - 1);
                    reviewComments(editted);
                }
                else
                    System.out.println("Wrong Choice");
                return true;

            case"4":
                checkListOfMovies();
                System.out.println("Enter Number of Movie Which You Want to Edit Information");
                numberOfMovie=input.nextInt();
                //call movie from movelist with index value
                if(numberOfMovie>0 && numberOfMovie<temp.size()) {
                    Movie editted = temp.get(numberOfMovie - 1);
                    addInfoAboutMovie(editted);
                }
                else
                    System.out.println("Wrong Choice");
                return true;
            case "5":
                System.out.println("Have a Nice Day Sir");
                return false;
            default:
                System.out.println("Wrong Choice");
                return false;
        }

    }
    public Moderator(String name,String password){
        super(name,password);
    }
    /**
     * Add a Suggestion For Add Movie And Write Suggestion to addSuggestion.csv
     * Admin Can Read This File and Add or Delete Suggested Movie From File
     * */
    public void suggestMovie(){
        Scanner input = new Scanner(System.in);
        String line,Title,Certificate,Duration,Genre,Rate,Metascore,Description,Cast,Info;
        String addSuggestion=new String();

        System.out.println("Enter Line of Movie");
        line = input.nextLine();

        System.out.println("Enter Title of Movie");
        Title = input.nextLine();

        System.out.println("Enter Certificate of Movie");
        Certificate=input.nextLine();

        System.out.println("Enter Duration of Movie");
        Duration=input.nextLine();

        System.out.println("Enter Genre of Movie");
        Genre=input.nextLine();

        System.out.println("Enter Rate of Movie");
        Rate = input.nextLine();

        System.out.println("Enter Metascore of Movie");
        Metascore=input.nextLine();

        System.out.println("Enter Description of Movie");
        Description = input.nextLine();

        System.out.println("Enter Cast of Movie");
        Cast = input.nextLine();

        System.out.println("Enter Info about Movie");
        Info=input.nextLine();

       addSuggestion="Movie{" +
               ";" + line +
               ";" + Title +
               ";" + Certificate +
               ";" + Duration +
               ";" + Genre +
               ";" + Rate +
               ";" + Metascore +
               ";" + Description +
               ";" + Cast +
               ";" + Info;

        try {
            FileWriter csvWriter = new FileWriter("addSuggestion.csv",true);
            csvWriter.append(addSuggestion);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Add a Suggestion For Remove Movie And Write Suggestion to removeSuggestion.csv
     * Admin Can Read This File and Can Delete This Movies
     * @return movie which is suggested for delete
     * */
    public Movie suggestRemovingMovie(Movie a){
        //if a is not null or if a is not in MovieList
        if(a==null || searchMovie(a)!=null) {
            System.out.println("That is Wrong");
            return null;
        }
        String movie_ = new String();
        movie_="Movie{" +
                ";" + a.line +
                ";" + a.Title +
                ";" + a.Certificate +
                ";" + a.Duration +
                ";" + a.Genre +
                ";" + a.Rate +
                ";" + a.Metascore +
                ";" + a.Description +
                ";" + a.Cast +
                ";" + a.Info;

        try {
            FileWriter csvWriter = new FileWriter("removeSuggestion.csv",true);
            csvWriter.append(movie_);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }
    /**
     * Moderator can see comments about movie and delete comment
     * */
    public void reviewComments(Movie a){
        PriorityQueue<Comment>commentss=a.getComments();
        ArrayList<Comment>temp=new ArrayList<>();
        while(!commentss.isEmpty()){
            temp.add(commentss.poll());
        }
        for(int i=0;i<temp.size();i++){
            System.out.println((i+1)+"-)"+temp.get(i));
        }
        Scanner b = new Scanner(System.in);
        int j;
        System.out.println("Enter Number of Inappropriate Comment");
        j=b.nextInt();
        if(j<temp.size() && j>0){
            Movie c = searchMovie(a);
            c.removeComment(temp.get(j-1));
        }
    }
    /**
     * Edit Info About Movie
     * @return updated movie
     * */
    public Movie addInfoAboutMovie(Movie a){

        Scanner input = new Scanner(System.in);
        //find movie from movielist

        Movie c = searchMovie(a);
        System.out.println((c.Title)+"\n");

        System.out.println("Old Version:  "+a.line);
        System.out.println("Enter New Line: ");
        c.line= input.nextLine();

        System.out.println("Old Version:  "+a.Certificate);
        System.out.println("Enter New Certificate: ");
        c.Certificate= input.nextLine();

        System.out.println("Old Version:  "+a.Duration);
        System.out.println("Enter New Duration: ");
        c.Duration= input.nextLine();

        System.out.println("Old Version:  "+a.Genre);
        System.out.println("Enter New Genre: ");
        c.Genre= input.nextLine();

        System.out.println("Old Version:  "+a.Rate);
        System.out.println("Enter New Rate: ");
        c.Rate= input.nextLine();

        System.out.println("Old Version:  "+a.Metascore);
        System.out.println("Enter New Metascore: ");
        c.Metascore= input.nextLine();

        System.out.println("Old Version:  "+a.Description);
        System.out.println("Enter New Description: ");
        c.Description= input.nextLine();

        System.out.println("Old Version:  "+a.Cast);
        System.out.println("Enter New Cast: ");
        c.Cast= input.nextLine();

        System.out.println("Old Version:  "+a.Info);
        System.out.println("Enter New Info: ");
        c.Info= input.nextLine();

        //call update function and update new info

        MovieList.updateMovieFile();
        return c;
    }
}
