package program_kasir;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Kasir {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("\nMasukan Nama Kasir        \t: ");
        String kasir = input.nextLine();
        
        System.out.print("Masukan Nama Pembeli      \t: ");
        String pembeli = input.nextLine();
        
        String jenisPesanan = inputJenisPesanan(input);
        
        int totalMakanan = 0, totalMinuman = 0, porsi = 0, gelas = 0;
        String jenisMakanan = "", jenisMinuman = "";
        
        int[] hasilMakanan = fungsimakanan(input);
        totalMakanan = hasilMakanan[0];
        porsi = hasilMakanan[1];
        jenisMakanan = hasilMakanan[2] == 1 ? "Indomie Goreng" : hasilMakanan[2] == 2 ? "Kentang Goreng" : "Nasi Goreng";
        
        int[] hasilMinuman = fungsiminuman(input);
        totalMinuman = hasilMinuman[0];
        gelas = hasilMinuman[1];
        jenisMinuman = hasilMinuman[2] == 1 ? "Kopi Gula Aren" : hasilMinuman[2] == 2 ? "Lemon Tea" : "Vanilla Latte";
        
        int totalSemua = totalMakanan + totalMinuman;
        double diskon = totalSemua >= 50000 ? totalSemua * 0.07 : 0;
        int totalAkhir = (int) (totalSemua - diskon);
        
        System.out.println("\nTotal Bayar                  \t: Rp " + totalAkhir);
        int uangTunai = inputUangTunai(input, totalAkhir);
        int kembalian = uangTunai - totalAkhir;
        
        cetakStruk(kasir, pembeli, jenisPesanan, porsi, jenisMakanan, totalMakanan, gelas, jenisMinuman, totalMinuman, totalSemua, diskon, totalAkhir, uangTunai, kembalian);
    }
    
    public static String inputJenisPesanan(Scanner input) {
        while (true) {
            System.out.println("1. Dine in");
            System.out.println("2. Take away");
            System.out.print("Pilih Jenis Pesanan (1/2) \t: ");
            if (input.hasNextInt()) {
                int pilihan = input.nextInt();
                input.nextLine(); 
                if (pilihan == 1) return "Dine In";
                else if (pilihan == 2) return "Take Away";
            } else {
                input.nextLine(); 
            }
            System.out.println("Pilihan tidak tersedia, silakan pilih 1 atau 2.");
        }
    }
    
    public static int[] fungsimakanan(Scanner input) {
        while (true) {
            System.out.println("\n========= Menu Makanan =========");
            System.out.println("1. Indomie Goreng+Telor - Rp 18.000");
            System.out.println("2. Kentang Goreng - Rp 10.000");
            System.out.println("3. Nasi Goreng - Rp 15.000");
            System.out.print("Masukan kode makanan (1/2/3) \t: ");
            if (input.hasNextInt()) {
                int nomor = input.nextInt();
                if (nomor >= 1 && nomor <= 3) {
                    System.out.print("Berapa Porsi \t\t\t: ");
                    if (input.hasNextInt()) {
                        int porsi = input.nextInt();
                        if (porsi > 0) {
                            int harga = nomor == 1 ? 18000 : nomor == 2 ? 10000 : 15000;
                            return new int[]{porsi * harga, porsi, nomor};
                        }
                    }
                }
            }
            input.nextLine(); 
            System.out.println("Input tidak valid, silakan coba lagi.");
        }
    }
    
    public static int[] fungsiminuman(Scanner input) {
        while (true) {
            System.out.println("\n========= Menu Minuman =========");
            System.out.println("1. Kopi Gula Aren - Rp 27.000");
            System.out.println("2. Lemon Tea - Rp 20.000");
            System.out.println("3. Vanilla Latte - Rp 25.000");
            System.out.print("Masukan kode minuman (1/2/3) \t: ");
            if (input.hasNextInt()) {
                int nomor = input.nextInt();
                if (nomor >= 1 && nomor <= 3) {
                    System.out.print("Berapa Gelas \t\t\t: ");
                    if (input.hasNextInt()) {
                        int gelas = input.nextInt();
                        if (gelas > 0) {
                            int harga = nomor == 1 ? 27000 : nomor == 2 ? 20000 : 25000;
                            return new int[]{gelas * harga, gelas, nomor};
                        }
                    }
                }
            }
            input.nextLine(); 
            System.out.println("Input tidak valid, silakan coba lagi.");
        }
    }
    
    public static int inputUangTunai(Scanner input, int totalAkhir) {
        while (true) {
            System.out.print("Uang Tunai Pembeli           \t: Rp ");
            if (input.hasNextInt()) {
                int uangTunai = input.nextInt();
                if (uangTunai >= totalAkhir) return uangTunai;
            }
            input.nextLine(); 
            System.out.println("Uang tunai tidak mencukupi atau tidak valid. Silakan coba lagi.");
        }
    }
    
    public static void cetakStruk(String kasir, String pembeli, String jenisPesanan, int porsi, String jenisMakanan, int totalMakanan, int gelas, String jenisMinuman, int totalMinuman, int totalSemua, double diskon, int totalAkhir, int uangTunai, int kembalian) {
        LocalDateTime waktu = LocalDateTime.now();
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("\n=========================== STRUK PEMBELIAN ===========================");
        System.out.println("Tanggal                  \t: " + waktu.format(formatTanggal));
        System.out.println("Nama Kasir               \t: " + kasir);
        System.out.println("Nama Pembeli             \t: " + pembeli);
        System.out.println("Jenis Pesanan            \t: " + jenisPesanan);
        System.out.println("======================================================================");
        System.out.println("Makanan                  \t: " + porsi + " Porsi " + jenisMakanan + " Rp " + totalMakanan);
        System.out.println("Minuman                  \t: " + gelas + " Gelas " + jenisMinuman + " Rp " + totalMinuman);
        System.out.println("======================================================================");
                if (diskon > 0) {
            System.out.println("Total Sebelum Diskon     \t: Rp " + totalSemua);
            System.out.println("Diskon 7%                \t: Rp " + (int) diskon);
            System.out.println("Total Setelah Diskon     \t: Rp " + totalAkhir);
        } else {
            System.out.println("Total Bayar              \t: Rp " + totalSemua);
        }
        System.out.println("Cash                     \t: Rp " + uangTunai);
        System.out.println("Kembalian                \t: Rp " + kembalian);
        System.out.println("======================================================================");
    }
}
