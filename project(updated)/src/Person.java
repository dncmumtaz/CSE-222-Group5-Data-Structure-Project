package project;

import java.util.Iterator;

import java.util.PriorityQueue;
/**
 abstract , base class for Moderator Admin and User classes
 */
public abstract class Person {
	protected String name,password;
	protected MovieList MovieList;
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
	 * constructs with movieList
	 * @param name name of person
	 * @param pw password of person
	 * @param ml registered MovieList
	 */
	protected Person(String name,String pw,MovieList ml){
		this(name,pw);
		setMovieList(ml);
	}
	/**
	 * returns string which shows name and password of person
	 * @return string which shows name and password of person
	 */
	@Override
	public final String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" ");
		sb.append(password);
		return sb.toString();
	} 
	/**
	 Abstract menu function which shows possible actions for user of the system
	 @return if the user wants to exit returns false
	 */
	 public abstract boolean menu();
	/**
	 * Abstract method which makes person login to the system
	 * @param sys entered system
	 */
	/*public abstract void Login(MovieList sys){
		if(this instanceof Admin) {
			flag = MovieList.getAdmins().remove(this);
		}else if(this instanceof Moderator) {
			flag = MovieList.getModerators().remove(this);
		} else { // person is audience
			flag = MovieList.getAudiences().remove(this);
		}
	}*/
	/**
	 * Outputs the movies
	 * @return returns true if the person belongs to the system
	 */
	public boolean checkListOfMovies() {
		
		if(MovieList == null)
			return false;
		int i=1;
		Iterator<Movie> iter= MovieList.getMovieList().iterator();
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
	public Movie searchMovie(Movie movie){
		if(movie == null)
			return null;
		return searchMovie(movie.getTitle());
	}
	/**
	 * returns null if the given movie does not exist. Otherwise returns the original movie to be able to make changes on it.
	 * @param m searched movie's title
	 * @return found movie , if the movie does not exist, returns null;
	 */
	public Movie searchMovie(String movieName) {
		Iterator<Movie> iter = MovieList.getMovieList().iterator();
		Movie movie;
		while(iter.hasNext()) {
			movie = iter.next();
			if(movie.getTitle().equals(movieName)) {
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
		//return searchMovie(m);
		return (m == null) ? null : searchMovie(m.getTitle());
	}
	/**
	 * returns the comments of given movie 
	 * @param m movie whose comments are wanted
	 * @return returns the comments of given movie
	 */
	public PriorityQueue<Comment> seeComments(Movie m){
		if(m == null) return null;
		Movie movie = searchMovie(m.getTitle());
		return (movie == null) ? null : movie.getComments();
	}
	/**
	 * list the comments of given movie 
	 * @param m movie whose comments are wanted
	 * @return returns true if there are comments
	 */
	public boolean listComments(Movie movie) {
		PriorityQueue<Comment> comments = movie.getComments();
		
		if(comments == null)
			return false;
		
		Iterator<Comment> iter = comments.iterator();
		StringBuilder s = new StringBuilder();
		Comment tempComment;
		int i = 1;
		
		while(iter.hasNext()) {
			tempComment = iter.next();
			s.append(i);
			s.append(") ");
			s.append(tempComment);
			s.append("\n");
			i++;
			System.out.print(s.toString());
			s.setLength(0);
		}
		
		return true;
	}
	/**
	 * unregisters the Person from the system
	 * @return return the system which lost its member
	 */ 
	public MovieList unregister() {
		if(MovieList == null )
			return null;
		boolean flag = false;
		if(this instanceof Admin) {
			flag = MovieList.getAdminList().remove(this);
		}else if(this instanceof Moderator) {
			flag = MovieList.getModeratorList().remove(this);
		} else { // person is user
			flag = MovieList.getAudienceList().remove(this);
			for(Audience u : MovieList.getAudienceList()){
				System.out.println(u);
			}
			System.out.println("SILINMIS ::::: "+this);
		}
		if(!flag) {
			// Person could not be deleted
			throw new IllegalStateException();
		}
		MovieList temp = MovieList;
		MovieList= null;
		temp.updateUsers(this);
		return temp;
	}
	/**
	 * returns the MovieList which has this person
	 * @return the MovieList which has this person
	 */
	public MovieList getMovieList() {
		return MovieList;
	}
	/**
	 * 
	 */
	void setMovieList(MovieList movieList){
		this.MovieList =  movieList;
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
	/**
	 * hashCode implementation for Person class
	 */
	@Override
	public int hashCode(){
		return name.hashCode() + password.hashCode();
	}
	/**
	 * returns name of person
	 * @return name of person
	 */
	public String getName(){
		return name;
	}
}
