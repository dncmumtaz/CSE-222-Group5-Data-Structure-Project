package termProject;

public class Comment {
	private String text;
	public Comment(String t) {
		text = t;
	}
	private int numberOfLikes;
	private int numberOfDislikes;
	private boolean isReported = false;
	private String commentOwner;
	
	/**
	 * Audience için ekledim.
	 */
	public void report()
	{
		if(!isReported)
			isReported = true;
		else
			System.out.println("This comment is already reported!");
	}
	/**
	 * Audience için ekledim.
	 */
	public void incrementLike()
	{
		numberOfLikes++;
	}
	/**
	 * Audience için ekledim.
	 */
	public void incrementDislike()
	{
		numberOfDislikes++;
	}
	/**
	 * Audience için ekledim.
	 */
	public int getNumberOfLikes()
	{
		return numberOfLikes;
	}
	/**
	 * Audience için ekledim.
	 */
	public int getNumberOfDislikes()
	{
		return numberOfDislikes;
	}
	@Override
	/**
	 * Audience için ekledim.
	 */
	public String toString()
	{
		return String.format(text + "\nCommented by " + commentOwner);
	}
}
