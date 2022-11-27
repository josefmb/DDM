package ifsc.bischof.blocodenotas;

public class Nota {

    String titulo;
    String texto;

    public Nota(String titulo, String texto) {
        this.titulo = titulo;
        this.texto  = texto;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getTexto() {
        return this.texto;
    }
}
