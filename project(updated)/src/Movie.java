package project;

import java.util.PriorityQueue;
public class Movie {
    String line,Title,Certificate,Duration,Genre,Rate,Description,Cast,Info;
    Double MetaScore;
    int userCount; // filmi oylayanların sayısı
    PriorityQueue<Comment> comments;
    
    public Movie(String line, String title, String certificate, String duration, String genre, String rate, String metascore, String description, String cast, String info) {
        this.line = line;
        Title = title;
        Certificate = certificate;
        Duration = duration;
        Genre = genre;
        Rate = rate;
        MetaScore = Double.parseDouble(metascore);
        Description = description;
        Cast = cast;
        Info = info;
        userCount = 0;
        comments = new PriorityQueue<Comment>();
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCertificate() {
        return Certificate;
    }

    public void setCertificate(String certificate) {
        Certificate = certificate;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public double getMetascore() {
        return MetaScore;
    }

    public void setMetascore(Double metascore) {
        MetaScore = metascore;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCast() {
        return Cast;
    }

    public void setCast(String cast) {
        Cast = cast;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public void setUserCount(int userCount) {
    	this.userCount = userCount;
    }
    public int getUserCount() {
    	return userCount;
    }
    @Override
    public String toString() {
        return "Movie{" +
                "," + line +
                "," + Title +
                "," + Certificate +
                "," + Duration +
                "," + Genre +
                "," + Rate +
                "," + MetaScore +
                "," + Description +
                "," + Cast +
                "," + Info;
    }
    public PriorityQueue<Comment> getComments(){
    	return comments;
    }
    public void addComment(Comment comment)
    {
    	comments.add(comment);
    }
}
