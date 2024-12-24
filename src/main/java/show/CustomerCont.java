/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package show;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Om Gupta
 */
@Named(value = "customerCont")
@SessionScoped
public class CustomerCont implements Serializable {

    /**
     * Creates a new instance of CustomerCont
     */
    public CustomerCont() {
    }
    
}
