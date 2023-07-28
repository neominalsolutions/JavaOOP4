public class UserRepository extends GenericRepository<User, Integer> implements FindByName<User, Integer> {

  // UserRepository ortak kod işlemleri için GenericRepository sınıfa günip işi
  // delegate ediyor.

  // FindByName ile yetenek kazandık bir veriyi ismine göre arama yeteneği
  // kazandık
  // GenericRepository sınıfı ile Create Delete Update List Find işlemlerini
  // merkezi olarak bir yerden yönetip kod yazımında kazanç sağladık
  // Bu kullandığımız Repository yapısı ile standart bir veri merkezli sistem ile
  // çalışma veri depolama yapısı meydana geldi.

  @Override
  public void Create(User entity) {
    // super sınıftaki create çağırmadan önceki ara işlemlerin yapıldığı tampon bir
    // bölge
    // bu kısımda kendi kod blogumu yazarım
    System.out.println("User Create işlemlerini loglamasını yap");

    if (entity.getName().isBlank() || entity.getName().isEmpty()) {
      throw new Error("Ürün isimini girmek zorundasınız");
    }

    // kaydı listeye eklemeden önce bazı kontroleri ovveride edip ilgili kodların
    // kontrolünden sonra super.Create() ile kalıtım aldığım sınıf üzerinden
    // işlemleri yap.

    super.Create(entity); // listeye veri eklenilir.
  }

  @Override
  public java.util.List<User> FindByName(String name) {
    // Ürün-1, Ürün-2, Süt, Çikolata, Kek (ürün) -> geçenleri getir
    return this.dataSource.stream().filter(x -> x.getName().contains(name)).toList();
  }

  // Generic Repositoryden gelen methodların dışında extra eğer bir özellil
  // eklenecek ise bu özellik diğer repositorylerde ihtiyac olabileceği
  // düşünülerek, interfaceler üzerinden yetenek kazandırılabilir.

}
