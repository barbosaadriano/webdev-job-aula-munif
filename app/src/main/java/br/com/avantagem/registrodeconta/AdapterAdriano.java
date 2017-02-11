package br.com.avantagem.registrodeconta;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrador on 11/02/2017.
 */

public class AdapterAdriano extends BaseAdapter {
    private final List<Conta> contas;
    private final Activity act;

    public AdapterAdriano(List<Conta> contas, Activity act) {
        this.contas = contas;
        this.act = act;
    }

    @Override
    public int getCount() {
        return contas.size();
    }

    @Override
    public Object getItem(int position) {
        return contas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.lista_adriano_personalizada, parent, false);
        Conta conta = contas.get(position);
        TextView descricao = (TextView)
                view.findViewById(R.id.tvDescricaol);
        TextView valor = (TextView)
                view.findViewById(R.id.tvValorl);
        descricao.setText(conta.getDescricao());
        valor.setText("R$ "+Double.toString(conta.getValor()));
        
        TextView tvTipo = (TextView) view.findViewById(R.id.tvPr);
        tvTipo.setText(conta.getTipo());
        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.fundoLista);
        if (conta.getStatus().equals(Conta.STATUS_F)) {
            rl.setBackgroundColor(Color.argb(128,200,0,0));
        } else {
            rl.setBackgroundColor(Color.argb(128,0,200,0));
        }

        return view;
    }
}
