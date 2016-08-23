package eder.padilla.personal.works.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorTitulares
        extends RecyclerView.Adapter<AdaptadorTitulares.TitularesViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private ArrayList<Visita> datos;
    Context context;

    public AdaptadorTitulares(ArrayList<Visita> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    @Override
    public TitularesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem_titular, viewGroup, false);
        itemView.setOnClickListener(this);
        TitularesViewHolder tvh = new TitularesViewHolder(itemView);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TitularesViewHolder viewHolder, int pos) {
        Visita item = datos.get(pos);
        if (item.tipo == "programada") {
            Log.e("Adapter", "Entro a aprogramada");

        }
        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }
    public  class TitularesViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtTitulo;
        private TextView txtSubtitulo;
        private View mContainer;

        public TitularesViewHolder(View itemView) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.LblTitulo);
            txtSubtitulo = (TextView) itemView.findViewById(R.id.LblSubTitulo);
            mContainer = itemView.findViewById(R.id.layout_container);
        }
        public void bindTitular(Visita t) {
            txtTitulo.setText(t.getNombre());
            txtSubtitulo.setText(t.getDireccion());
            Log.i("myLog", "tipo : " + t.getTipo());
            if (t.getTipo().trim().equalsIgnoreCase("programada")) {
                mContainer.setBackgroundColor(mContainer.getResources().getColor(R.color.visita_programada));

            }else if(t.getTipo().trim().equalsIgnoreCase("norealizada")){
                mContainer.setBackgroundColor(mContainer.getResources().getColor(R.color.visita_no_realizada));
            }else if(t.getTipo().trim().equalsIgnoreCase("cancelada")){
                mContainer.setBackgroundColor(mContainer.getResources().getColor(R.color.visita_cancelada));
            }else if(t.getTipo().trim().equalsIgnoreCase("finalizada")){
                mContainer.setBackgroundColor(mContainer.getResources().getColor(R.color.visitas_finalizadas));
            }
            else {
                mContainer.setBackgroundColor(mContainer.getResources().getColor(R.color.white));
            }
        }
    }
}

