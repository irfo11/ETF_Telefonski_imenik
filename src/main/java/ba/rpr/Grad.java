package ba.rpr;

public enum Grad{
    SARAJEVO ("033"),
    TUZLA ("032"),
    BANOVICI("035");

    private final String pozivniBroj;
    Grad(String pozivniBroj){
        this.pozivniBroj = pozivniBroj;
    }

    @Override
    public String toString() {
        return pozivniBroj;
    }
}
