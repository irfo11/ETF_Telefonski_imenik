package ba.rpr;

import java.util.Objects;

public class FiksniBroj extends TelefonskiBroj implements Comparable<FiksniBroj> {
    private Grad grad;
    private String broj;

    public FiksniBroj(Grad grad, String broj) {
        this.grad = grad;
        this.broj = broj;
    }

    public Grad getGrad(){ //treba za metodu izGrada u Imenik
        return grad;
    }
    /*
    ispisuje FiksniBroj u formatu xxx/xxx-xxx
     */
    @Override
    public String ispisi(){
        return grad.toString() + "/" + broj;
    }

    @Override
    public int hashCode(){
        return Objects.hash(grad, broj);
    }
    /*
    poredi dva fiksna broja na nacin da testira da li su jednaki grad i broj
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof FiksniBroj)) return false;
        FiksniBroj drugiBroj = (FiksniBroj) obj;
        return grad.equals(drugiBroj.grad) && broj.equals(drugiBroj.broj);
    }
    /*
    leksikografski poredi ispise dva Fiksna broja
     */
    @Override
    public int compareTo(FiksniBroj drugiBroj){
        return ispisi().compareTo(drugiBroj.ispisi());
    }
}
