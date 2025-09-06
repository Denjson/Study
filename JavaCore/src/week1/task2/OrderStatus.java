package week1.task2;

enum OrderStatus {
    NEW(0), PROCESSING(1), SHIPPED(2), DELIVERED(3), CANCELLED(4);
    private int code;
    OrderStatus(int code){
       this.code = code;
    }
    public int getCode(){ return code;}
    
    public static OrderStatus getName(int idNr) {
        for (OrderStatus col: OrderStatus.values()) {
             if (col.getCode() ==  idNr) return col;
        }
        return CANCELLED;   // default value if ID is out of range
   }
    
}