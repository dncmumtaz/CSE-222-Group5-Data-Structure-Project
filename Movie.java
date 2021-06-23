package termProject;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Movie {
	private String name;
	private PriorityQueue<Comment> comments;
	private List<Mood> reactions;
	private double grade;
	private int userCount;
	
	/**
	 * Audience için ekledim.
	 */
	public Movie()
	{
		comments = new PriorityQueue<Comment>(new CommentComparator());
	}
	
	/**
	 * Audience için ekledim.
	 */
	public int getUserCount()
	{
		return userCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public PriorityQueue<Comment> getComments() {
		return comments;
	}
	/**
	 * Audience için ekledim.
	 */
	public void listComments()
	{
		Iterator<Comment> iter = comments.iterator();
		Comment temp;
		
		while(iter.hasNext())
		{
			temp = iter.next();
			temp.toString();
		}
	}
	public boolean addReaction(Mood reaction)
	{
		if(reactions.contains(reaction))
			return false;
		
		reactions.add(reaction);
		
		return true;
	}
	public void addComment(Comment comment)
	{
		comments.add(comment);
	}
	
}
/**
 * Audience için ekledim
 * @author ahmet
 *
 */
class CommentComparator implements Comparator<Comment>{
    
    // Overriding compare()method of Comparator 
                // for descending order of cgpa
    public int compare(Comment s1, Comment s2) {
    	
    	Integer temp1 = s1.getNumberOfLikes() - s1.getNumberOfDislikes();
    	Integer temp2 = s2.getNumberOfLikes() - s2.getNumberOfDislikes();
    	
    	return temp1.compareTo(temp2);
    }
}

