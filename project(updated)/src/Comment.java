package project;

public class Comment {
	private String text;
	public Comment(String t) {
		text = t;
	}
	private int numberOfLikes;
	private int numberOfDislikes;
	private boolean isReported = false;
	
	public void incrementLike()
    	{
    		numberOfLikes++;
    	}
    	public void incrementDislike()
    	{
    		numberOfDislikes++;
    	}
	public boolean isReported() {
        	return isReported;
    	}

    	public void setReported(boolean reported) {
        	isReported = reported;
	}
}
