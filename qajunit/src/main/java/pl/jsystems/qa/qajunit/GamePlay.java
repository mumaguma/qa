package pl.jsystems.qa.qajunit;

public class GamePlay {

    public String play(int numer) {
     if (numer == 0) throw new IllegalArgumentException("Number can not be " + numer);
     if (numer % 3 == 0)  return "ok";
     if (numer %5 == 0) return "not ok";
     return String.valueOf(numer);
    }
}
