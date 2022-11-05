package ba.rpr;

import java.util.Scanner;
import java.util.Set;

public class Program {

    public static void main(String[] args){
        Imenik imenik = new Imenik();
        Scanner unos = new Scanner(System.in);
        for(;;) {
            System.out.print("""
                    Opcije:
                    1. Dodaj 
                    2. Broj korisnika
                    3. Ime korisnika broja
                    4. Pretrazi
                    5. Svi korisnici iz grada
                    6. Svi brojevi iz grada
                    7. Izadi
                    """);
            System.out.print("Unesite broj za opciju koju zelite: ");
            int opcija = unos.nextInt();
            unos.nextLine(); //jede /n
            try {
                if (opcija == 1) {
                    TelefonskiBroj telBroj = unesiTelefonskiBroj();
                    System.out.print("Unesi ime korisnika: ");
                    String imeKorisnika = unos.nextLine();
                    imenik.dodaj(imeKorisnika, telBroj);
                } else if (opcija == 2) {
                    System.out.print("Unesite ime korisnika: ");
                    String ime = unos.nextLine();
                    System.out.println(imenik.dajBroj(ime));
                } else if (opcija == 3) {
                    TelefonskiBroj telBroj = unesiTelefonskiBroj();
                    System.out.println(imenik.dajIme(telBroj));
                } else if (opcija == 4) {
                    System.out.print("Unesi prvo slovo imena cije brojeve zelis: ");
                    char slovo = unos.next().charAt(0);
                    unos.nextLine(); //jede /n
                    System.out.println(imenik.naSlovo(slovo));
                } else if (opcija == 5) {
                    System.out.print("Unesi grad iz kojeg zelis korisnike: ");
                    String imeGrada = unos.nextLine().toUpperCase();
                    Grad grad = Grad.valueOf(imeGrada);
                    Set<String> imena = imenik.izGrada(grad);
                    for(String ime: imena) System.out.println(ime);
                } else if (opcija == 6) {
                    System.out.print("Unesi grad iz kojeg zelis brojeve: ");
                    String imeGrada = unos.nextLine().toUpperCase();
                    Grad grad = Grad.valueOf(imeGrada);
                    Set<TelefonskiBroj> brojevi = imenik.izGradaBrojevi(grad);
                    for(TelefonskiBroj broj: brojevi) System.out.println(broj.ispisi());
                } else if (opcija == 7) break;
                else{
                    System.out.println("Pogresan unos!");
                }
            }catch(PrazanImenik e){
                System.out.println(e.getMessage());
            }catch(ElementNijeUImeniku e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static TelefonskiBroj unesiTelefonskiBroj(){
        Scanner unos = new Scanner(System.in);
        System.out.print("""
                            Opcije:
                            1. Mobilni broj
                            2. Fiksni broj
                            3. Medunarodni broj
                            """);
        System.out.print("Koji broj zelite unijeti: ");
        int opcija = unos.nextInt();
        unos.nextLine(); //jede /n
        TelefonskiBroj telBroj = null;
        if (opcija == 1) {
            System.out.print("Unesi prefiks mobilne mreze bez nule 0xx: ");
            int mobilnaMreza = unos.nextInt();
            unos.nextLine(); //jede /n
            System.out.print("Unesi broj u formatu xxx-xxx: ");
            String broj = unos.nextLine();
            telBroj = new MobilniBroj(mobilnaMreza, broj);
        } else if (opcija == 2) {
            System.out.print("Unesi grad u kojem se nalazi korisnik: ");
            String imeGrada = unos.nextLine().toUpperCase();
            Grad grad = Grad.valueOf(imeGrada); //baca IllegalArgumentException ako imeGrada nije enum konstanta
            System.out.print("Unesi broj u formatu xxx-xxx: ");
            String broj = unos.nextLine();
            telBroj = new FiksniBroj(grad, broj);
        } else if (opcija == 3) {
            System.out.print("Unesi pozivni broj drzave u formatu +xxx: ");
            String pozivniBroj = unos.nextLine();
            System.out.print("Unesi broj bez pocetne 0 u formatu xx xxx xxx");
            String broj = unos.nextLine();
            telBroj = new MedunarodniBroj(pozivniBroj, broj);
        }
        return telBroj;
    }

}
