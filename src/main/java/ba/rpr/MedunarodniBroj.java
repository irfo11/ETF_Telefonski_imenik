package ba.rpr;

import java.util.Objects;

public class MedunarodniBroj extends TelefonskiBroj{
    private String drzava;
    private String broj;

    public MedunarodniBroj(String drzava, String broj) {
        this.drzava = drzava;
        this.broj = broj;
    }

    @Override
    public String ispisi(){
        return drzava + "/" + broj;
    }

    @Override
    public int hashCode(){
        return Objects.hash(drzava, broj);
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof MedunarodniBroj)) return false;
        MedunarodniBroj drugiBroj = (MedunarodniBroj) obj;
        return drzava.equals(drugiBroj.drzava) && broj.equals(drugiBroj.broj);
    }
}
