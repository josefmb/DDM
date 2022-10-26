package ifsc.bischof.laucher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ifsc.bischof.laucher.model.Fruta;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ExibeFrutaAdapter extends ArrayAdapter {
    Context m_Context;
    int m_Resource = 0;

    public ExibeFrutaAdapter(Context context, int resource, List objects) {
        super(context, resource,  objects);

        m_Context  = context ;
        m_Resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(m_Context);
        convertView = layoutInflater.inflate(m_Resource, parent, false);

        TextView  tvCodigo     = (TextView ) convertView.findViewById(R.id.tvCodigo    );
        TextView  tvNome       = (TextView ) convertView.findViewById(R.id.tvNome      );
        TextView  tvPreco      = (TextView ) convertView.findViewById(R.id.tvPreco     );
        TextView  tvPrecoVenda = (TextView ) convertView.findViewById(R.id.tvPrecoVenda);
        ImageView imgView      = (ImageView) convertView.findViewById(R.id.imageView   );

        Fruta fruta = (Fruta) getItem(position);

        NumberFormat nf = new DecimalFormat("#,###.00");

        tvCodigo.setText(Integer.toString(fruta.getCodigo()));
        tvNome.setText(fruta.getNome());
        tvPreco.setText(nf.format(fruta.getPreco()));
        tvPrecoVenda.setText(nf.format(fruta.getPreco_venda()));
        imgView.setImageResource(fruta.getImagem());

        return convertView;
    }
}
