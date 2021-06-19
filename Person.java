package termProject;

import java.util.Iterator;

import java.util.PriorityQueue;
/**
 abstract , base class for Moderator Admin and User classes
 */
public abstract class Person {
	protected String name,password;
	private MovieSystem movieSystem;
	/**
	 * Constructs Person class
	 * @param name name of person
	 * @param pw password of person
	 */
	public Person(String name,String pw){
		this.name = name;
		password = pw;
	}
	/**
	 * Abstract method which makes person login to the system
	 * @param sys entered system
	 */
	public abstract void Login(MovieSystem sys);
	/**
	 * Outputs the movies
	 * @return returns true if the person belongs to the system
	 */
	public boolean checkListOfMovies() {
		
		if(movieSystem == null)
			return false;
		int i=1;
		Iterator<Movie> iter= movieSystem.getMovies().iterator();
		StringBuilder s = new StringBuilder();
		Movie movie;
		while(iter.hasNext()) {
			movie = iter.next();
			s.append(i);
			s.append(") ");
			s.append(movie);
			s.append("\n");
			i++;
			System.out.print(s.toString());
			s.setLength(0);
		}
		return true;
	}
	/**
	 * returns null if the given movie does not exist. Otherwise returns the original movie to be able to make changes on it.
	 * @param m searched movie
	 * @return found movie , if the movie does not exist, returns null;
	 */
	public Movie searchMovie(Movie m) {
		Iterator<Movie> iter= movieSystem.getMovies().iterator();
		Movie movie;
		while(iter.hasNext()) {
			movie = iter.next();
			if(movie.equals(m)) {
				return movie;
			}
		}
		return null;
		
	}
	/**
	 * returns the information about movie if the given movie belongs to the system
	 * @param m searched movie
	 * @return returns the information about movie if the given movie belongs to the system
	 */
	public Movie getMovieInformation(Movie m) {
		return searchMovie(m);
	}
	/**
	 * returns the comments of given movie 
	 * @param m movie whose comments are wanted
	 * @return returns the comments of given movie
	 */
	public PriorityQueue<Comment> seeComments(Movie m){
		Movie movie = searchMovie(m);
		return (movie == null) ? null : movie.getComments();
	}
	/**
	 * Logouts the Person from the system
	 * @return return the system which lost its member
	 */ 
	public MovieSystem Logout() {
		
		if(movieSystem == null )
			return null;
		boolean flag = false;
		if(this instanceof Admin) {
			flag = movieSystem.getAdmins().remove(this);
		}else if(this instanceof Moderator) {
			flag = movieSystem.getModerators().remove(this);
		} else { // person is audience
			flag = movieSystem.getAudiences().remove(this);
		}
		if(!flag) {
			// Person could not be deleted
			throw new IllegalStateException();
		}
		MovieSystem temp = movieSystem;
		movieSystem= null;
		return temp;
	}
	/**
	 * returns the MovieSystem which has this person
	 * @return the MovieSystem which has this person
	 */
	public MovieSystem getMovieSystem() {
		return movieSystem;
	}
	/**
	 * checks if the given object is a Person whose name and password is equal to the this person
	 * @param o other Person
	 * @return returns true if the 2 people are equal
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(o instanceof Person) {
			Person other = (Person) o;
			return name.equals(other.name) && password.equals(other.password);
		}else {
			return false;		
		}
	}
	
}
