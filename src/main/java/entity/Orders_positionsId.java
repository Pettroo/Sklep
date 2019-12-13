package entity;

import java.io.Serializable;

public class Orders_positionsId implements Serializable {
    private int orderId;
    private int produktId;

    public int hashCode(){
        return orderId+produktId;
    }
    public boolean equals(Object o){
        if(o instanceof Orders_positionsId){
            Orders_positionsId drugi=(Orders_positionsId) o;
            return(drugi.produktId==this.produktId)&&(drugi.orderId==this.orderId);
        }
        return false;
    }
}
