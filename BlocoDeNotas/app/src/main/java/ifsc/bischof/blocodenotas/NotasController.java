package ifsc.bischof.blocodenotas;

import android.content.Context;

import java.util.ArrayList;

public class NotasController {

    Context context;
    NotasDAO notasDAO;

    public NotasController(Context context) {
        this.context = context;
        this.notasDAO = new NotasDAO(this.context);
    }

    public ArrayList<Nota> getNotas() {
        return this.notasDAO.getNotas();
    }

    public ArrayList<String> getTitulosNotas() {
        ArrayList<String> arrayAdapterResultado = new ArrayList<String>();

        for (Nota n : this.notasDAO.getNotas())
            arrayAdapterResultado.add(n.getTitulo());

        return arrayAdapterResultado;
    }

    public void salvarNota(Nota nota){
        this.notasDAO.inserirNota(nota);
    }
}
