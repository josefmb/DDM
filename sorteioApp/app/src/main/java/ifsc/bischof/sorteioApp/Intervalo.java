package ifsc.bischof.sorteioApp;

import java.util.Random;

public class Intervalo {
    private int iMenor = 0;
    private int iMaior = 0;

    public Intervalo(int iMenor, int iMaior) {
        this.iMenor = iMenor;
        this.iMaior = iMaior;
    }

    public void setMenor(int iMenor) {
        this.iMenor = iMenor;
    }

    public int getMenor() {
        return this.iMenor;
    }

    public void setMaior(int iMaior) {
        this.iMaior = iMaior;
    }

    public int getMaior() {
        return this.iMaior;
    }

    public static class Sorteador {
        public int sorteio(Intervalo intervalo) {
            int iInferior = intervalo.getMenor();
            int iSuperior = intervalo.getMaior();

            Random random = new Random();

            int iSorteado = random.nextInt((iSuperior - iInferior) + 1) + iInferior;
            return iSorteado;
        }
    }
}
