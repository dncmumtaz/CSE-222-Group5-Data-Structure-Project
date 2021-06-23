package termProject;

public class Audience extends Person
{

	private MovieSystem movieSystem;
	
	public Audience(String name, String password) 
	{
		super(name, password);
		// TODO Auto-generated constructor stub
	}
	/*public MovieSystem subscribe() 
	{
		
	}
	*/
	public void reactToComment(Comment c, boolean isLiked) 
	{
		if(isLiked)
			c.incrementLike();
		else
			c.incrementDislike();
	}
	public void ListMovies() 
	{
		checkListOfMovies();
	}
	public void listComments(Movie movie)
	{
		Movie tempMovie = searchMovie(movie);
		tempMovie.listComments();
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
		Movie tempMovie = searchMovie(movie);
		
		tempMovie.addComment(comment);
		
	}
	public double gradeMovie(Movie movie, double grade) {
		Movie tempMovie = searchMovie(movie);
		
		double oldGrade = tempMovie.getGrade();
		int userCount = tempMovie.getUserCount();
		
		oldGrade = oldGrade * userCount;
		
		oldGrade += grade;
		userCount++;
		
		tempMovie.setGrade(oldGrade / userCount);
		// returns the average grade of the movie
		return movie.getGrade();
	}
	public void reportInappropriateComment(Comment comment) 
	{
		comment.report();
	}
	@Override
	public void Login(MovieSystem system) 
	{
	}
}
