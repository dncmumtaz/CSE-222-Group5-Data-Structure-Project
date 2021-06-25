package project;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Audience extends Person {
	public Audience(String name, String password) {
		super(name, password);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean menu() {
		
		Scanner sc = new Scanner(System.in);
        
		System.out.println("1) List Movies");
		System.out.println("2) Search for a movie");
		System.out.println("0) Exit");
		
		int choice = sc.nextInt();
		String movieName;
		Movie selectedMovie;
		
        if(choice == 1) {
        	checkListOfMovies();
        	System.out.println("Please select a movie");
        	int movieIndex = sc.nextInt();
        	
        	if(movieIndex <= 0 && movieIndex > MovieList.getMovieList().size()) // film sayýsýný tutan bir deðiþken atanabilir.
        		System.out.println("There isn't any movie with this index!");
        	else {
        		selectedMovie = MovieList.getMovieList().get(movieIndex); // getMoviede iteratorle index kadar dolaþýlýp film döndürülebilir.
        	}
        }
        else if(choice == 2) {
        	System.out.println("Enter the movie name to search");
        	movieName = sc.nextLine();
        	selectedMovie = searchMovie(movieName); // film yoksa null gelecek
        }
        else if(choice == 0) {
        	System.out.println("Exiting from the menu!");
        	return false;
        }
        else
        	System.out.println("Invalid input please try again!");
        
        if(selectedMovie != null)
        	while(movieMenu(selectedMovie));
        
        return true;
	}
	private boolean movieMenu(Movie selectedMovie)
	{
		int choice; 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1) Get movie information");
    	System.out.println("2) Score the selected movie");
    	System.out.println("3) React to selected movie");
    	System.out.println("4) Write Comment");
    	System.out.println("5) See Comments");
    	System.out.println("6) Return");
    	
    	choice = sc.nextInt();
    	
    	if(choice == 1)
    		selectedMovie.getInfo();
    	else if(choice == 2) {
    		System.out.println("Enter your score for the movie");
    		double score = sc.nextDouble();
    		scoreMovie(selectedMovie, score);
    	}
    	else if(choice == 3) {
    		System.out.println("Type your mood for the movie");
    		//Mood mood = new Mood();
    		//Sýkýntý var.
    	}
    	else if(choice == 4) {
    		System.out.println("Enter your comment to add to selected movie.");
    		String commentText = sc.nextLine();
    		Comment comment = new Comment(commentText, 0, 0, false);
    		addComment(selectedMovie, comment);
    	}
    	else if(choice == 5) {
    		listComments(selectedMovie);
    		int commentIndex = sc.nextInt();
        	
        	if(commentIndex <= 0 && commentIndex > selectedMovie.getComments().size())) // comment sayýsýný tutan bir deðiþken atanabilir.
        		System.out.println("There isn't any comments with this index!");
        	else {
        		Comment selectedComment = selectedMovie.getComment(commentIndex); // getComment iteratorle index kadar dolaþýlýp comment döndürülebilir.
        	}
    		
        	if(selectedComment != null)
        		while(commentMenu(selectedComment));
    	}
    	else if(choice == 6)
    		return false;
    	else
    		System.out.println("Invalid input please try again!");
    	
    	return true;	
	}
	private boolean commentMenu(Comment selectedComment) {
		int choice; 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1) Like comment");
    	System.out.println("2) Dislike comment");
    	System.out.println("3) Report Comment");
    	System.out.println("4) Return");
    	
    	choice = sc.nextInt();
    	
    	if(choice == 1)
    		selectedComment.incrementLike();
    	else if(choice == 2) 
    		selectedComment.incrementDislike();
    	else if(choice == 3)
    		reportInappropriateComment(selectedComment);
    	else if(choice == 4)
    		return false;
    	else
    		System.out.println("Invalid input please try again!");
	}
	public void reactToMovie(Movie movie, Mood reaction) 
	{
		Movie tempMovie = searchMovie(movie);
		
		if(tempMovie.addReaction(reaction))
			System.out.println("Your reaction has added to movie.");
		else
			System.out.println("This reaction exists at the movie.");
	}
	public void addComment(Movie movie, Comment comment) 
	{
		if(comment != null && movie != null)
			movie.addComment(comment);
		
	}
	public double scoreMovie(Movie movie, double score) {
		
		double oldScore = movie.getMetascore();
		int userCount = movie.getUserCount();
		
		oldScore = oldScore * userCount;
		oldScore += score;
		userCount++;
		movie.setMetascore(oldScore / userCount);
		// returns the average grade of the movie
		return movie.getMetascore();
	}
	public void reportInappropriateComment(Comment comment) {
		if(comment.isReported())
			comment.setReported(true);
	}
}
