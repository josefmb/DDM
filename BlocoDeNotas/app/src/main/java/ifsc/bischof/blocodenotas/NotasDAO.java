package ifsc.bischof.blocodenotas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotasDAO {

    SQLiteDatabase bd;

    public NotasDAO(Context context) {
        bd = context.openOrCreateDatabase("meubd", context.MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS notas(" +
                "id integer primary key autoincrement," +
                "titulo varchar not null," +
                "texto varchar);");
    }

    public ArrayList<Nota> getNotas(){
        ArrayList<Nota> ArraylistadeNotas = new ArrayList<Nota>();

        Cursor cursor;
        cursor = bd.rawQuery("SELECT * FROM notas", null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            @SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            @SuppressLint("Range") String texto  = cursor.getString(cursor.getColumnIndex("texto" ));

            ArraylistadeNotas.add(new Nota(titulo, texto));

            cursor.moveToNext();
        }

        return ArraylistadeNotas;
    }

    public void inserirNota(Nota nota){
        bd.execSQL("INSERT INTO notas (titulo,texto) VALUES ('"+nota.getTitulo()+"','"+nota.getTexto()+"');");
    }
}
