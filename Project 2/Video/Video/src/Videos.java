/*
 * This is a class which builds the stuff for the videos which will be input I.E ID, name, etc.
 */
public class Videos {
	private final int id;
    private final String title;
    
    /*
     * Builds the Video object.
     * Supplying the ID and Title of a video will build this object.
     * @param id The desired ID for the movie.
     * @param title The title of the movie.
     */
    public Videos(int id, String title) {
        this.id = id;
        this.title = title;
    }
    
    /*
     * Returns the ID of the movie in the object.
     * @return ID of movie.
     */
    public int getId() {
        return id;
    }
    
    /*
     * Returns the title of the movie.
     * @return title of movie.
     */
    public String getTitle() {
        return title;
    }
    
    /*
     * Returns a string of all information for the movie.
     * @return all movie information.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title;
    }
    
}