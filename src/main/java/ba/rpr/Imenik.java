package ba.rpr;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Imenik {
    private HashMap<TelefonskiBroj, String> imenik; //ipak bolje TelefonskiBroj kao kljuc jer ista osoba moze imati
                                                    //vise brojeva
    private void TestPrazanImenik(){
        if(imenik.size() == 0) throw new PrazanImenik("Prazan imenik");
    }
    public Imenik(){
        imenik = new HashMap<TelefonskiBroj, String>();
    }
    /*
    dodaje ime i telefonski broj u imenik
     */
    public void dodaj(String ime, TelefonskiBroj broj){
        imenik.put(broj, ime);
    }
    /*
    Vraca sve brojeve koje korisnik ima
     */
    public String dajBroj(String ime) {
        TestPrazanImenik();
        if(!imenik.containsValue(ime)) throw new ElementNijeUImeniku("Ne postoji korisnik");
        String brojevi = "";
        for(Map.Entry<TelefonskiBroj, String> par: imenik.entrySet()){
            if(ime.equals(par.getValue())) brojevi = brojevi.concat(par.getKey().ispisi() + "\n");
        }
        return brojevi;
    }

    /*
    Vraca ime korisnika korisnika ciji je telefonski broj 'broj'
     */
    public String dajIme(TelefonskiBroj broj){
        TestPrazanImenik();
        if(!imenik.containsKey(broj)) throw new ElementNijeUImeniku("Ne postoji broj");
        return imenik.get(broj);
    }
    /*
    Vraca string koji sadrzi sva imena i brojeve korisnika cija imena pocinju sa s.
    Formatiran u obliku
    1. Ime Prezime - Broj
    2. Ime Prezime - Broj....
     */
    public String naSlovo(char s){
        TestPrazanImenik();
        int i = 1;
        String brojevi = "";
        for(Map.Entry<TelefonskiBroj, String> par: imenik.entrySet()){
            if(par.getValue().charAt(0) == s) {
                brojevi = brojevi.concat(i + ". " + par.getValue() + " - " + par.getKey().ispisi() + "\n");
                i++;
            }
        }
        if(i == 1) throw new ElementNijeUImeniku("Ne postoji ni jedan korisnik cije ime pocinje sa " + s);
        return brojevi;
    }
    /*
    vraca Set koji sadrzi sva imena korisnika koji su iz grada 'grad'
     */
    public Set<String> izGrada(Grad grad){
        TestPrazanImenik();
        Set<String> imena = new TreeSet<String>();
        for(Map.Entry<TelefonskiBroj, String> par: imenik.entrySet()){
            if(par.getKey() instanceof FiksniBroj){
                FiksniBroj fiksni = (FiksniBroj) par.getKey();
                if(fiksni.getGrad().equals(grad)) imena.add(par.getValue());
            }
        }
        if(imena.size() == 0) throw new ElementNijeUImeniku("Nema korisnika iz grada " + grad);
        return imena;
    }
    /*
    vraca Set koji sadrzi sve telefonske brojeve iz grada 'grad'
     */
    public Set<TelefonskiBroj> izGradaBrojevi(Grad grad){
        TestPrazanImenik();
        Set<TelefonskiBroj> brojevi = new TreeSet<TelefonskiBroj>();
        for(Map.Entry<TelefonskiBroj, String> par: imenik.entrySet()){
            if(par.getKey() instanceof FiksniBroj){
                FiksniBroj broj = (FiksniBroj) par.getKey();
                if(broj.getGrad().equals(grad)) brojevi.add(broj);
            }
        }
        if(brojevi.size() == 0) throw new ElementNijeUImeniku("Nema korisnika iz grada " + grad);
        return brojevi;
    }
}
