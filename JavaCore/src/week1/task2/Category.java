package week1.task2;

enum Category {
    ELECTRONICS(0), CLOTHING(1), BOOKS(2), HOME(3), BEAUTY(4), TOYS(5);
    private int code;
	Category(int code){
       this.code = code;
    }
    public int getCode(){ return code;}
    
    public static Category getName(int idNr) {
        for (Category col: Category.values()) {
             if (col.getCode() ==  idNr) return col;
        }
        return TOYS;   // default value if ID is out of range
   }
 
}