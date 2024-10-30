package onlineshopping;

public class Review {
    
    private final String customerName;
    private String comment;
    private int rating; // Rating out of 5

    public Review(String customerName, String comment, int rating) {
        this.customerName = customerName;
        this.comment = comment;
        this.rating = rating;
    }

    // Getters and setters
    public String getCustomerName() {
        return customerName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "\tCustomer: " + customerName + "\n\tRating: " + rating + "/5\n\tComment: " + comment + "\n";
    }
}