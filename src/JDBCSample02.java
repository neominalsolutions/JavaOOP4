import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCSample02 {
  public static void main(String[] args) throws SQLException {

    Scanner s = new Scanner(System.in);
    System.out.println("Adınızı giriniz");

    User usr = new User();
    usr.setName(s.nextLine());

    String connectionString = "jdbc:mysql://localhost:3306/testdb";
    String username = "root";
    String password = "admin";

    Connection connection = DriverManager.getConnection(connectionString, username, password);

    if (connection != null) {
      System.out.println("Bağlantı kuruldu");
    }

    try {
      // Sql Injection saldırı tipi korunmak için sorgularda parametrik çalışıyor.
      // parametrik değerleri veritabanına gönderirken statement yerine
      // preparedStatement denen bir sınıf tanımlammışlar.
      String sqlQuery = "Insert into user (Name) values (?)";
      // Insert, Update, Delete gibi parametre içeren sorgular.

      PreparedStatement statement = connection.prepareStatement(sqlQuery);
      statement.setString(1, usr.getName());
      // statement.close() bu satırda olursa db ye birşey
      int affectedResult = statement.executeUpdate(); // execute query veri okuma işlemleri için, veri ekleme, update
                                                      // etme işlemleri içinde executeUpdate method kullanılıyor

      if (affectedResult > 0) {
        System.out.println("Kayıt başarılı");
      } else {
        System.out.println("Bir hata oluştu. Tekrar deneyiniz");
      }
      // veritabanı işlemi yaptığımız için hata riskimiz var
      // hata riski olan kodlar bu tarz durumlarda try bloguna yazılıyor.
      // bizden kaynaklanmayan bir hata durumunda ise catch blogu ile hatalarımız
      // yakalaybilir ve ekrana hata çıktılarını yazdırabilir.

      statement.close();

    } catch (Exception e) { // Exception uygulamadaki hata istisnaları
      // TODO: handle exception
      System.out.println("Hata" + e.getMessage());
    } finally {
      // tüm işlem sorunlu yada sorunsuz bittiğinde
      // try yada catch blogu ile de bitebilir.
      // connection.close(); bağlntı kaynaklarını memory leak hataları oluşmaması için
      // kapatmalıyız.
      connection.close();
    }

  }
}