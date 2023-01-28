package Modeller.Calisanlar;

import Modeller.Departmanlar.Departman;
import Veritabani.Calisanlar;
import Veritabani.Departmanlar;

import java.util.ArrayList;

public class Calisan {

    private String calisanId;
    private String adSoyad;
    private int maas;
    private Departman Departman;
    private String isimKodu = "";

    private static int sayac = 1;

    public Calisan(String adSoyad, int maas, String departmanKodu) {
        setAdSoyad(adSoyad);
        setMaas(maas);
        setDepartman(departmanKodu);
        this.setCalisanId();
        Calisanlar.addACalisan(this);
        sayac++;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public int getMaas() {
        return maas;
    }

    public void setMaas(int maas) {
        this.maas = maas;
    }

    // Kullanicinin departman koduna göre, gerekli departman set edilmelidir.
    private void setDepartman(String departman) {
        /*
            İpucu: Departman listesinin (Veritabani.Departmanlar.DepartmanList) içerisindeki departmanların kodları var,
        bu kodlari donguye tutmak ise yarayabilir.
       */
        for (Departman abc : Departmanlar.getDepartmanList()) {
            if (abc.getDepartmanKodu().equals(departman))
                this.Departman = abc;
        }
    }


    // Calisanin ID sinin kendisine özel olduğundan bahsetmistik, ID nin nasil kaydedileceği CalisanKaydetmeProjesiTanıtım.txt
    // içerisinde yer aliyor.
    private void setCalisanId() {
        /*
            İpucu: Detayli anlatim CalisanKaydetmeProjesiTanıtım.txt içerisinde.
         */
        calisanId = Departman.getDepartmanKodu() + sayac + getCalisanIsimKodu();
    }

    // Calisanin ID sinin sonuna isim kodu eklenmesi için, ismi parçalayan bir method.
    private String getCalisanIsimKodu() {
        /*

            Basit string metodlari ise isinice cok yarayacaktir fakat dinamik olmasina dikkat edelim... Mesela 2 isim bir
            soyisim girildiğinde hata vermesin.

         */
        int boslukSayisi = 0;
        for (int i = 0; i < adSoyad.length(); i++) {
            if (adSoyad.charAt(i) == ' ') {
                boslukSayisi++;
            }
        }

        if (boslukSayisi == 1) {
            int ilkBosluk = adSoyad.indexOf(" ");
            isimKodu = adSoyad.substring(0, 1).
                    concat(adSoyad.substring((ilkBosluk + 1), (ilkBosluk + 2)));
        } else if (boslukSayisi == 2) {
            int ilkBosluk = adSoyad.indexOf(" ");
            int sonBosluk = adSoyad.lastIndexOf(" ");
            isimKodu = adSoyad.substring(0, 1).
                    concat(adSoyad.substring((ilkBosluk + 1), (ilkBosluk + 2))).
                    concat(adSoyad.substring((sonBosluk + 1), (sonBosluk + 2)));
        }


        return isimKodu;
    }

    // Calisanin id sini almak icin basit getter method
    public String getCalisanId() {
        return this.calisanId;
    }

    // Calisanin departmanini almak icin basit getter method
    public Departman getDepartman() {
        return this.Departman;
    }

    // Departman adini verebilmek için bir method
    public String getDepartmanAdi() {
        /*
                İpucu: Departman Kodu YD ise departman adi Yonetim Departmani olarak kaydedilmelidir.
         */
        String depAdi = "";
        if (Departman.getDepartmanKodu().equals("YD"))
            depAdi = "Yonetim Departmani";
        else if (Departman.getDepartmanKodu().equals("BTD"))
            depAdi = "Bilisim Teknolojileri Departmani";
        else if (Departman.getDepartmanKodu().equals("IKD"))
            depAdi = "Insan Kaynaklari Departmani";
        return depAdi;
    }

    // Calisana zam yapilmasi için gerekli bir method
    public static void zamYap(String calisanId) {
        /*

            İpucu: Calisan ID si kullanilarak yapilmalidir, diğer attributelarin aynilarindan 1 er tane daha
            olabilirdi.

         */
        ArrayList<Calisan> calisanList = Calisanlar.getCalisanList();
        for (Calisan calisan : calisanList) {
            if (calisan.getCalisanId().equals(calisanId)) {
                calisan.maas += (calisan.maas * calisan.Departman.getZamOrani()) / 100;
            }
        }
    }

    @Override
    public String toString() {

        /*

            İpucu: Detayli anlatim CalisanKaydetmeProjesiTanıtım.txt içerisinde.

         */
        return "Calisan ID : " + "'" + getCalisanId() + "'" + ", Isim Soyisim : " + "'" + getAdSoyad() + "'" +
                ", Maas : " + "'" + getMaas() + "'" + ", Departman : " + "'" + getDepartmanAdi() + "'";
    }
}
