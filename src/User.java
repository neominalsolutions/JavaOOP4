
// somut sınıflarda User nesnesini Id değeri Integer olarak kullanılsın diye BaseEntity extends olduğunda Interger Wrapper type ile işaretlendi.
public class User extends BaseEntity<Integer> {
  
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



}
