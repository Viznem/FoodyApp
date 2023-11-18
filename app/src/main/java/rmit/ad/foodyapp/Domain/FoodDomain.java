package rmit.ad.foodyapp.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private String orderNote;
    private Double fee;
    private int numberInCart;

    public FoodDomain(String title, String pic, String description, String note, Double fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.orderNote = note;
        this.fee = fee;
    }

    public FoodDomain(String title, String pic, String description, String note, Double fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.orderNote = note;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
