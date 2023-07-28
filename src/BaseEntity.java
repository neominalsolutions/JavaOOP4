
// Id Guid de olabilir (String) "ali" "ahmet"
// Id int gibi bir değer de olabilir 10 25
// Generic sınıflar ile bir sınıfın farklı tipler ile çalışmabilmesi için bir modelleme yapıyoruz.
// T ifadesi herhangi bir tip demek
// User class
public abstract class BaseEntity<TKey extends Comparable<TKey>> {
  // Bütün entity tiplerinde id olmak zorunda ama bu id değeri int short String
  // olabilir.
  private TKey id;

  public TKey getId() {
    return id;
  }

  public void setId(TKey id) {
    this.id = id;
  }

}
