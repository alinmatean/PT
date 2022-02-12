package tuc.tp.tema4.business;

import java.io.Serializable;

/**
 * MenuItem
 */
public abstract class MenuItem implements Serializable {
    private String title;

    public MenuItem(String title) {
        this.title = title;
    }

    public abstract int computePrice();
    public abstract double computeRating();
    public abstract  int computeCalories();
    public abstract int computeProteins();
    public abstract int computeFat();
    public abstract int computeSodium();

    public int hashCode(){
        int result = 7;
        result = 31 * result + title.hashCode();
        return result;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title){this.title = title;}
    public String toString(){
        return title;
    }

}
