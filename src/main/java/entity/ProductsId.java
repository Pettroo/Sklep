package entity;

import java.io.Serializable;

public class ProductsId implements Serializable {
    private int shoe_id;
    private String size;

    public int hashCode(){
        return shoe_id;
    }
    public boolean equals(Object o){
        if(o instanceof ProductsId){
            ProductsId drugi=(ProductsId) o;
            return(drugi.shoe_id==this.shoe_id)&&(drugi.size==this.size);
        }
        return false;
    }
}
