import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private String suite;
    private int value;
    private Image cards;
    // Creating a parameterized constructor
    Card(String theSuite, int theValue) {
        suite = theSuite;
        value = theValue;
    }

    // implementing getters and setters for the member variables
    public void setSuite(String theSuite) {
        suite = theSuite;
    }

    public void setValue(int theValue) {
        value = theValue;
    }

    public String getSuite() {
        return suite;
    }

    public int getValue() {
        return value;
    }
    // creating a display function
    // this function displays the image of the card that is being dealt with in the
    // current moment, using the suite name and the card number (value)
    public ImageView display() {
        String imageDownloadPath = "/images/" +  suite.toLowerCase() + "_" + value + ".png";
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imageDownloadPath)));
        imageView.setFitWidth(100);
        imageView.setFitHeight(150);
        return imageView;
    }

}