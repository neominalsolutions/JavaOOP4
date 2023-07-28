import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<TEntity extends BaseEntity<TKey>, TKey extends Comparable<TKey>>
    implements Repository<TEntity, TKey> {

  protected List<TEntity> dataSource; // T Type da bir liste oluştur.

  public GenericRepository() {
    super();
    this.dataSource = new ArrayList<>();
  }

  @Override
  public void Create(TEntity entity) {
    this.dataSource.add(entity);
  }

  @Override
  public void Update(TEntity entity) {
    var dbEntity = this.dataSource.stream().filter(x -> x.getId() == entity.getId()).findFirst().get();
    this.dataSource.set(0, dbEntity);
  }

  @Override
  public void Delete(TKey id) {
    var dbEntity = this.dataSource.stream().filter(x -> x.getId() == id).findFirst().get();
    this.dataSource.remove(dbEntity);
  }

  @Override
  public TEntity Find(TKey id) {
    var dbEntity = this.dataSource.stream().filter(x -> x.getId() == id).findFirst().get();
    return dbEntity;
  }

  @Override
  public java.util.List<TEntity> List() {
    return this.dataSource;
  }

}
