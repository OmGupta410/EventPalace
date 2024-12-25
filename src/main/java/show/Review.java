/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package show;

import com.mycompany.entity.ReviewTable;
import com.mycompany.entity.UserRegistrationTable;
import com.mycompany.sessionBeans.ReviewTableFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Om Gupta
 */
@Named(value = "review")
@SessionScoped
public class Review implements Serializable {

    @EJB
    private ReviewTableFacadeLocal reviewTableFacade;
    
    private ReviewTable review = new ReviewTable();
    
    

    /**
     * Creates a new instance of Review
     */
    public Review() {
    }
    
     // cRUD on venue table
    public List<ReviewTable> findAll() {
        return this.reviewTableFacade.findAll();
    }

    public String insertreview() {
        this.reviewTableFacade.create(review);
        this.review = new ReviewTable();
        return "Customers";

    }

    public String updatereview(ReviewTable review) {
        this.review = review; // Ensure venuetable is the correct reference
        return "updatereview"; // Navigate to edit page
    }

    public String updatereview() {
        this.reviewTableFacade.edit(review);
        this.review = review;
        return "Customers";
    }

    public void deletereviews(ReviewTable rev) {
        this.reviewTableFacade.remove(review);
    }

    public ReviewTableFacadeLocal getReviewTableFacade() {
        return reviewTableFacade;
    }

    public void setReviewTableFacade(ReviewTableFacadeLocal reviewTableFacade) {
        this.reviewTableFacade = reviewTableFacade;
    }

    public ReviewTable getReview() {
        return review;
    }

    public void setReview(ReviewTable review) {
        this.review = review;
    }

    
    
}
