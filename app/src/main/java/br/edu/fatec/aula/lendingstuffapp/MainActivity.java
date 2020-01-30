package br.edu.fatec.aula.lendingstuffapp;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemDialogFragment.NoticeDialogListener, ItemAdapter.ItemAdapterListener {

    private RecyclerView recyclerView;
    private List<Item> items;
    private ItemDAO itemDAO;

    private ItemAdapter adapter;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListItem();
        setRecyclerView();
    }

    private void setListItem(){
        itemDAO = new ItemDAO(this);
        try {
            items = itemDAO.readAll();
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao ler item", Toast.LENGTH_LONG).show();
        }
    }
    private void setRecyclerView(){
        recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new ItemAdapter(items, this);
        recyclerView.setAdapter(adapter);
    }
    private void showItemDialogFragment(){

    }
    private void showConfirmDeleteDialogFragment(int position){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Excluindo");
        dialog.setMessage("Tem certeza que deseja excluir o item?");
        dialog.setNegativeButton("Cancelar", null);
        dialog.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, final int position) {
                deleteItem(position);
            }
        });
        dialog.create();
        dialog.show();
    }

    public void createItem(Item item){
        try {
            itemDAO.create(item);
            items.add(item);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        } catch (Exception e){
            Toast.makeText(this, "Erro ao criar item", Toast.LENGTH_LONG).show();
        }
    }

    public void updateItem(Item item, int position){
        try {
            itemDAO.update(item);
            items.set(position, item);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(position);
        } catch (Exception e){
            Toast.makeText(this, "Erro ao atualizar item", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteItem(int position){
        try {
            itemDAO.update(items.get(position));
            items.remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(position);
        } catch (Exception e){
            Toast.makeText(this, "Erro ao excluir item", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDialogPositiceClick(Item item, int position) {
        if (item.getId() == 0){
            createItem(item);
        } else {
            updateItem(item, position);
        }
    }

    @Override
    public void onEditItemClick(View view, int position) {

    }

    @Override
    public void onDeleteItemClick(View view, int position) {
        showConfirmDeleteDialogFragment(position);
    }
}
