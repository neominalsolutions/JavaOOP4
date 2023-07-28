import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSample {
  public static void main(String[] args) throws SQLException {

    String connectionString = "jdbc:mysql://localhost:3306/testdb";
    String user = "root";
    String pass = "admin";

    // veri tabanı bağlantı nesnesi oluşturduk.
    Connection connection = DriverManager.getConnection(connectionString, user, pass);

    if (connection != null) {
      System.out.println("Veri tabanına bağlantı kuruldu");
      // user tablomuzun ismi
      String query = "Select * from user";

      Statement statement = connection.createStatement();
      // statement nesneleri query çalıştırmak için gerekli olan tanımlamalar
      ResultSet result = statement.executeQuery(query); // sql sorgusunu çalıştır.
      // veritabanındaki tüm kayıtlar ResultSet tipinde sınıf üzerinden satır satır okunabiliyor.

      // Scanner.next() gibi satır resultSet içerisinde verileri okuyoruz
      var repo = new UserRepository();

      while (result.next()) { // next ile bir sonraki kayıt olduğu sürece veriyi çekeceğimiz anlamına geliyor.
        int userId = result.getInt("Id"); // tablodaki id alanını oku.
        String userName = result.getString("Name");

        User usr = new User();
        usr.setId(userId);
        usr.setName(userName);
        // repository sınıfın içindeki listeye db den gelen her bir kayıtı obje formatına döndürerek attığımız yer
        repo.Create(usr);

        // okuma işlemi yapılırken her bir okunan sütunu User sınıfı içerisinde
        // UserRepository ile List<User>
      }

      // okuma işlemleri bittikten sonra veritabanı bağlantıları koparılarak ilgili
      // kaynak tuketimlerinin performans amaçlı manuel olarak yok edilmesi gerekiyor.
      // Bu veri tabanı ile ilişki bağlantılar koparılmaz ise Garbage Collector bunları kendisi temizliyemiyor. Bu sebeple Memory Leak hataları Ram Bellek hataları meydana geliyor.
      connection.close();
      statement.close();
      result.close();

      var users = repo.List();

      users.forEach(item -> {
        System.out.println(String.format("id: %s username : %s,", item.getId(), item.getName()));
      });
    }
  }
}