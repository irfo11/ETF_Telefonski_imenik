package ba.rpr;

import java.util.Objects;

public class MobilniBroj extends TelefonskiBroj{
    private int mobilnaMreza;
    private String broj;

    public MobilniBroj(int mobilnaMreza, String broj) {
        this.mobilnaMreza = mobilnaMreza;
        this.broj = broj;
    }
    @Override
    public String ispisi(){
        return "0" + mobilnaMreza + "/" + broj;
    }

    @Override
    public int hashCode(){
        return Objects.hash(mobilnaMreza, broj);
    }

    @Override
    public boolean equals(Object obj){
        if( !(obj instanceof MobilniBroj)) return false;
        MobilniBroj drugiBroj = (MobilniBroj) obj; //cast
        return (mobilnaMreza == drugiBroj.mobilnaMreza) && broj.equals(drugiBroj.broj);
    }
}
