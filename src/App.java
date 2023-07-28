import java.util.ArrayList;
import java.util.UUID;

public class App {

    // var a = 5; // Global Scope bu tanımlamayı yapamıyoruz.
    // var bir değişken tipi değil. lütfen javascripteki var ile karıştırmayalım.
    // Java C# gibi oop dillerde var new ifadesinde tanımlanan tipe göre kendisi
    // otomatik olarak tip değeri alır.

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        User usr = new User();
        usr.setId(10);
        usr.setName("ali");

        Product p = new Product();
        p.setId("3242432-234324234"); // String
        p.setPrice(10);
        p.setName("Ürün-1");

        // blok scope alanda var tipi ile direk tipin ismin yazmadan çalışabilme
        // fırsatımız oluyor.
        var p1 = new Product();
        p1.setId(UUID.randomUUID().toString());
        p1.setPrice(20);
        p1.setName("Ürün-2");

        // id alanlarını UUID formatında generate etmek için kullanılan bir kütüphane
        System.out.println(UUID.randomUUID().toString());

        // Repository Pattern ile veri tabanı işlemleri aynı kod formatında yapılmış
        // olur.
        var uRepo = new UserRepository();
        uRepo.Create(usr);
        uRepo.List();
        uRepo.FindByName("Ürün"); // Interface üzerinden kazandırılan özellik
        // Repository sınıflarımı interfacler ile genişletilebilir yeni yetenekler kazandırılabilir bir hale getiriyorum.

        var cRepo = new CategoryRepository();
        cRepo.Create(new Category());
        cRepo.List();
        cRepo.FindByName("sdsad");

        var pRepo = new ProductRepository();
        pRepo.Create(p);
        pRepo.Create(p1);
        pRepo.Find("1");
        var plist = pRepo.List();

        p1.setName("Güncel Ürün"); // Ürün-2
        p1.setPrice(30); // 20
        pRepo.Update(p1);

        var pBulunan = pRepo.Find("3242432-234324234"); // id göre bulma

        pRepo.Delete(p.getId()); // 3242432-234324234
        var plist2 = pRepo.List(); // listede 1 item olmalı

        // Java OOP bir dil olduğundan bütüm tipler javada Object tipinden gizli bir
        // kalıtım alıyor
        // primative types (Stack)
        Object a = 10;
        Object b = 10.5;
        Object c = true;
        Object d = "Deneme"; // String Wrapper Type Reference Type
        Object e = 'k';
        // Referance Types (HEAP)
        Object l = new ArrayList<>();
        Object k = new ProductRepository();

        int a1 = ((Integer) a).intValue();
        String a2 = d.toString();
        boolean c1 = ((Boolean) c).booleanValue();

    }
}
