// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Box {
  String place;
  int depth,type;
  Box() {
   }
  Box(String xPlace, int xDepth, int xType){
    place=xPlace;depth=xDepth; type=xType;
   }
  public String toString(){
    return("(" +place+","+depth + "," + type + ")");
   }
 }
