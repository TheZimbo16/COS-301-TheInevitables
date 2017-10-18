package theinevitables.navup;

/**
 * Created by dawie on 10/17/2017.
 */

public class DataItem {

    String text;
    Integer imageId;
    public DataItem(String text, Integer imageId){
        this.text=text;
        this.imageId=imageId;
    }

    public String getText(){
        return text;
    }

    public Integer getImageId(){
        return imageId;
    }
}