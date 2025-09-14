package week1.task3;

enum Components {
  HEAD(0),
  TORSO(1),
  HANDS(2),
  FEET(3);

  private int code;

  Components(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static Components getName(int idNr) {
    for (Components col : Components.values()) {
      if (col.getCode() == idNr) return col;
    }
    return FEET;
  }
}
