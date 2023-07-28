import java.util.ArrayList;

// javada final keyword ile kalıtım sonlandırıyoruz
// başka bir sınıf buradan kalıtım alamıyor
// c# sealed keyword ile yapıyoruz mühürlü sınıf
public final class ProductRepository implements Repository<Product, String> {

  // verilerimiz generic collections içerisinde tutuyor
  // burası veritabanından çekilen verilern tutulduğu tablo bağlantısı olarak
  // düşünelim
  private java.util.List<Product> dataSource; // Generic

  public ProductRepository() {
    super();
    this.dataSource = new ArrayList<>();
  }

  @Override
  public void Create(Product entity) {
    dataSource.add(entity); // listeye entity değeri eklendi.
  }

  @Override
  public void Update(Product entity) {

    // Nesneleri bulurken id unique değerleri üzerinden buluyoruz.
    // stream API JAVA 8 ve sonrasında collection yapılarına özellik kazandırmak
    // amaçlı kullan bir yapı
    // x -> x. (prediciate) koşullu ifade tanımı veriyoruz.
    // -> lambda expression diye dillerde geçiyor.
    Boolean entityExist = this.dataSource.stream().anyMatch(item -> item.getId() == entity.getId());

    if (!entityExist)
      throw new Error("Güncellenecek böyle bir nesne yok");
    else {
      // where koşulu
      // 1 tane kayıt bulacağımız findFirst ile ilk kaydı aldık.
      // get ile de bunu Product Nesnesi formatında günelledik.
      var foundEntity = this.dataSource.stream().filter(x -> x.getId() == entity.getId()).findFirst().get();

      foundEntity.setName(entity.getName());
      foundEntity.setPrice(entity.getPrice());

      System.out.println("Veri güncellendi");
    }

  }

  @Override
  public void Delete(String id) { // Integer yada String Wrapper type seçilebilir.
    // Double nm = ((Double)id).doubleValue();
    // String Id = ((String) id).toString(); // Object değerini benim key değeri
    // string olduğu için String tipine cast et.
    var entity = this.dataSource.stream().filter(x -> x.getId() == id).findFirst().get(); // entity bul getir.
    this.dataSource.remove(entity); // entity listeden sil
  }

  @Override
  public Product Find(String id) {
    // String Id = ((String) id).toString();
    return this.dataSource.stream().filter(x -> x.getId() == id).findFirst().get();
  }

  @Override
  public java.util.List<Product> List() {
    return this.dataSource;
  }

}
