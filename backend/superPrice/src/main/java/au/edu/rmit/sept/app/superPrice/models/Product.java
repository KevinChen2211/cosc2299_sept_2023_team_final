package au.edu.rmit.sept.app.superPrice.models;

public record Product (Long id, String title) {
    public String giveTitle() {
        return (title == null ? "No title" : title);
    }
}
