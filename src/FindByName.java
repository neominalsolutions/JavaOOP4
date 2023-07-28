import java.util.List;

public interface FindByName<TEntity extends BaseEntity<TKey>, TKey extends Comparable<TKey>> {

  /**
   * name alanına göre bize bu name ile eşleşen sonuçları döndürecek bir arayüz.
   * @param name
   * @return
   */
  List<TEntity> FindByName(String name);

}
