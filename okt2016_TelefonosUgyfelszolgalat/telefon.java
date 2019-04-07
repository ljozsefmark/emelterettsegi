package okt2016_TelefonosUgyfelszolgalat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class telefon {
	static int n= 0;
	static Adatok[] adattar= new Adatok[1000];
	public static void main(String[] args) throws IOException {
		feladat2();
		feladat3();
		feladat4();
		feladat5();
		feladat6();
		feladat7();
	}
	private static void feladat7() throws IOException {
		RandomAccessFile output= new RandomAccessFile("sikeres.txt", "rw");
		int elozotelefonalo=0;
		int utolsobeszelo=0;
		int kezdo=-1;
		int vegora= mpbe(12,0,0);
		
		for (int i = 0; i < n; i++) {
			int erkezik=mpbe(adattar[i].beora, adattar[i].beperc, adattar[i].bemp);
			int tavozik=mpbe(adattar[i].kiora, adattar[i].kiperc, adattar[i].kimp);
			if (erkezik <= vegora && tavozik>=mpbe(8,0,0)) {
				kezdo=i;
				break;
			}		
		}
		output.writeBytes(+(kezdo+1)+" "+adattar[kezdo].beora+" "+adattar[kezdo].beperc+" "+adattar[kezdo].bemp+" "+adattar[kezdo].kiora+" "+adattar[kezdo].kiperc+" "+adattar[kezdo].kimp);
		output.writeBytes("\n");

		utolsobeszelo=kezdo;
		for (int i = kezdo+1; i < n; i++) {
			int erkezik=mpbe(adattar[i].beora, adattar[i].beperc, adattar[i].bemp);
			int tavozik=mpbe(adattar[i].kiora, adattar[i].kiperc, adattar[i].kimp);
			if (erkezik < vegora && tavozik > mpbe(adattar[utolsobeszelo].kiora, adattar[utolsobeszelo].kiperc, adattar[utolsobeszelo].kimp)) {
				elozotelefonalo=utolsobeszelo;
				utolsobeszelo=i;
				output.writeBytes(+(i+1)+" "+adattar[elozotelefonalo].kiora+" "+adattar[elozotelefonalo].kiperc+" "+adattar[elozotelefonalo].kimp+" "+adattar[utolsobeszelo].kiora+" "+adattar[utolsobeszelo].kiperc+" "+adattar[utolsobeszelo].kimp);
				output.writeBytes("\n");
			}
		}
	
	}
	private static void feladat6() {
		System.out.println("6. feladat");
		int vegora= mpbe(12,0,0);
		int elozotelefonalo=0;
		int utolsobeszelo=0;
		int kezdo=-1;
		
		for (int i = 0; i < n; i++) {
			int erkezik=mpbe(adattar[i].beora, adattar[i].beperc, adattar[i].bemp);
			int tavozik=mpbe(adattar[i].kiora, adattar[i].kiperc, adattar[i].kimp);
			if (erkezik <= vegora && tavozik>=mpbe(8,0,0)) {
				kezdo=i;
				break;
			}		
		}
		utolsobeszelo=kezdo;
		for (int i = kezdo; i < n; i++) {
			int erkezik=mpbe(adattar[i].beora, adattar[i].beperc, adattar[i].bemp);
			int tavozik=mpbe(adattar[i].kiora, adattar[i].kiperc, adattar[i].kimp);
			if (erkezik < vegora && tavozik > mpbe(adattar[utolsobeszelo].kiora, adattar[utolsobeszelo].kiperc, adattar[utolsobeszelo].kimp)) {
				elozotelefonalo=utolsobeszelo;
				utolsobeszelo=i;
			}
		}
		int vart=mpbe(adattar[elozotelefonalo].kiora, adattar[elozotelefonalo].kiperc, adattar[elozotelefonalo].kimp)-mpbe(adattar[utolsobeszelo].beora, adattar[utolsobeszelo].beperc, adattar[utolsobeszelo].bemp);
		System.out.println("Az utolsó telefonáló adatai a "+utolsobeszelo+1+". sorban vannak! "+vart +" másodpercet várt!");
	}
	private static void feladat5() {
		System.out.println("5. feladat");
		System.out.print("Adjon meg egy idõpontot! (óra perc másodperc)");
		Scanner input= new Scanner(System.in);
		String bekert= input.nextLine();
		String bontott[]= new String[3];
		bontott=bekert.split(" ");
		int szamok[]= new int[3];
		szamok[0]=Integer.parseInt(bontott[0]);
		szamok[1]=Integer.parseInt(bontott[1]);
		szamok[2]=Integer.parseInt(bontott[2]);
		int bekertido= mpbe(szamok[0], szamok[1], szamok[2]);
		int kezdo = -1;
		int varokszama=0;
		int vegso= -1;
		int elozotelefonalo=0;
		for (int i = 0; i < n; i++) {
			int erkezik=mpbe(adattar[i].beora, adattar[i].beperc, adattar[i].bemp);
			int tavozik=mpbe(adattar[i].kiora, adattar[i].kiperc, adattar[i].kimp);
			if (erkezik <= bekertido && tavozik>=mpbe(8,0,0)) {
				kezdo=i;
				break;
			}		
			}
		int jelenlegibeszelo=kezdo;
//		System.out.println(kezdo);
		for (int i = kezdo; i < n; i++) {
			int erkezik=mpbe(adattar[i].beora, adattar[i].beperc, adattar[i].bemp);
			int tavozik=mpbe(adattar[i].kiora, adattar[i].kiperc, adattar[i].kimp);
			if (erkezik <= bekertido && tavozik > mpbe(adattar[jelenlegibeszelo].kiora, adattar[jelenlegibeszelo].kiperc, adattar[jelenlegibeszelo].kimp) ) {
				elozotelefonalo=jelenlegibeszelo;
				jelenlegibeszelo=i;
			}
		}
		for (int i = elozotelefonalo; i < jelenlegibeszelo; i++) {
			int erkezik=mpbe(adattar[i].beora, adattar[i].beperc, adattar[i].bemp);
			int tavozik=mpbe(adattar[i].kiora, adattar[i].kiperc, adattar[i].kimp);
			if (tavozik >= bekertido  ) {
				varokszama++;
			}
		}
		if (jelenlegibeszelo >= 0) {
			System.out.println("A beszélõ a "+(jelenlegibeszelo+1)+". hívó. Várók száma:"+varokszama);

		}else {
			System.out.println("Nem volt beszélõ");
		}
		
	}
	private static void feladat4() {
		System.out.println("4. feladat");
		int max=adattar[0].hossz;
		int maxpos=0;
		for (int i = 0; i < n; i++) {
			if (adattar[i].hossz > max) {
				max=adattar[i].hossz;
				maxpos=i;
			}
		}
		System.out.println("A leghosszabb ideig vonalban lévõ hívó a "+(maxpos+1)+". sorban szerepel");
		System.out.println("a hívás hossza:"+max+" másodperc");
	}
	private static void feladat3() {
		System.out.println("3. feladat");
		int db[]= new int [25];
		for (int i = 0; i < n; i++) {
			db[adattar[i].beora]++;
		}
		for (int i = 0; i < db.length; i++) {
			if (db[i] != 0) {
				System.out.println(i+" óra "+db[i]+" hívás");
			}
		}
		
		
	}
	private static void feladat2() throws IOException {
		String sor;
		String sor1[]= new String[6];
		RandomAccessFile raf= new RandomAccessFile("hivas.txt", "rw");
		
		for (sor=raf.readLine(); sor!= null; sor=raf.readLine()) {
			sor1=sor.split(" ");
			Adatok egyAdat = new Adatok();
			egyAdat.beora=Integer.parseInt(sor1[0]);
			egyAdat.beperc=Integer.parseInt(sor1[1]);
			egyAdat.bemp=Integer.parseInt(sor1[2]);
			egyAdat.kiora=Integer.parseInt(sor1[3]);
			egyAdat.kiperc=Integer.parseInt(sor1[4]);
			egyAdat.kimp=Integer.parseInt(sor1[5]);
			egyAdat.hossz=mpbe(egyAdat.kiora, egyAdat.kiperc, egyAdat.kimp)-mpbe(egyAdat.beora, egyAdat.beperc, egyAdat.bemp);
			adattar[n]=egyAdat;
			n++;
		}
	}
	private static int mpbe(int ora, int perc, int masodperc) {
		int mp=(ora*60*60)+(perc*60)+masodperc;
		return mp;
	}

}
class Adatok{
	int beora;
	int beperc;
	int bemp;
	int kiora;
	int kiperc;
	int kimp;
	int hossz;
	
	
}