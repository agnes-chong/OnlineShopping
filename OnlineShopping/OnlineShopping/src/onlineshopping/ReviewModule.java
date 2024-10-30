package onlineshopping;

import java.util.ArrayList;
import java.util.List;

public class ReviewModule {
    private List<Review> reviews;  // List to store reviews

    public ReviewModule() {
        this.reviews = new ArrayList<>();
    }

    // Method to submit a new review
    public void submitReview(String customerName, String comment, int rating) {
        Review review = new Review(customerName, comment, rating);
        reviews.add(review);
        System.out.println("Review submitted successfully.");
    }

    // Method to delete a review by customer name
    public void deleteReview(String customerName) {
        boolean removed = reviews.removeIf(review -> review.getCustomerName().equals(customerName));
        if (removed) {
            System.out.println("Review deleted successfully.");
        } else {
            System.out.println("Review not found for customer: " + customerName);
        }
    }

    // Method to show all reviews
    public void showReviews() {
        if (reviews.isEmpty()) {
            System.out.println("No reviews available.");
        } else {
            for (Review review : reviews) {
                System.out.println(review);
            }
        }
    }
    
    // Overriding toString method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReviewModule { \n");

        if (reviews.isEmpty()) {
            sb.append("\n\tNo reviews available.\n");
        } else {
            for (Review review : reviews) {
                sb.append("\t").append(review.toString()).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}