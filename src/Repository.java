import java.util.List;

// Generic yapıyı interface ile birlikte kullandık
// Repository yapısı ile sistemdeki tüm entitylerin veri depolama işlemlerini ortak methodlar üzerinden yapmasını sağlamak için kullandığımız bir tasarım kalıbı
// Entity de baseEntity extend olmayan bir sınıf olamaz
public interface Repository<TEntity extends BaseEntity<TKey>, TKey extends Comparable<TKey>> { // Tkey String Integer
                                                                                               // olabilir class olamaz
  void Create(TEntity entity);

  void Update(TEntity entity);

  void Delete(TKey id); // Integer veya String

  TEntity Find(TKey id); // read one sadece tek bir kayıt okuma işlemi

  List<TEntity> List(); // tüm kayıtları okuma read
}
