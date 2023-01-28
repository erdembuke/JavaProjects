package Veritabani;

import Modeller.Calisanlar.Calisan;


import java.util.ArrayList;

public class Calisanlar {


    // Buradaki calisanList static cünkü proje calismaya basladiği anda oluşması lazım. Bunu bir veritabani
    // gibi görebiliriz. Calisanlarimizin hepsi bu liste icerisinde yer alacak.
    private static ArrayList<Calisan> calisanList = new ArrayList<>();

    // Calisanlari almak icin basit bir getter method
    public static ArrayList<Calisan> getCalisanList() {
        return calisanList;
    }

    // Bir çalışan eklemek için gerekli method.
    public static void addACalisan(Calisan calisan) {
        calisanList.add(calisan);
    }


    // Bir çalışan silmek için gerekli method.
    public static void deleteACalisanWithId(String calisanId) {
        calisanList.removeIf(insan -> insan.getCalisanId().equals(calisanId));
    }

    // Departman kodu verilerek, konsola sadece o departmanda calisanlari yazdirmak için
    public static void printDepartmandakiCalisanlar(String departmanKodu) {
        for (Calisan insan : calisanList) {
            if (insan.getDepartman().getDepartmanKodu().equals(departmanKodu)) {
                System.out.println(insan);
            }
        }
    }


    // Calisanlari konsola yazdirmak için
    public static void printCalisanlar() {
        for (Calisan insan : calisanList) {
            System.out.println(insan);
        }
    }


}
