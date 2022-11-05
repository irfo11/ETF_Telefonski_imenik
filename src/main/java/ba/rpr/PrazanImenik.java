package ba.rpr;

public class PrazanImenik extends RuntimeException{
    PrazanImenik(String poruka){
        super(poruka);
    }
}
