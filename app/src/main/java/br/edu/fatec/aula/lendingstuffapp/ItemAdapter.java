package br.edu.fatec.aula.lendingstuffapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final ItemAdapterListener listener;

    public  interface ItemAdapterListener {
        void onEditItemClick(View view, int position);
        void onDeleteItemClick(View view, int position);
    }

    private List<Item> items;
    private Context context;


    public ItemAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
        this.listener = (ItemAdapterListener)context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate( R.layout.card_item, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        Item item = items.get(position);

        itemViewHolder.getDateTextView().setText(item.getDate());
        itemViewHolder.getDescriptionTextView().setText(item.getDescription());
        itemViewHolder.getNameTextView().setText(item.getName());
        itemViewHolder.getEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEditItemClick(view, position);
            }
        });
        itemViewHolder.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
