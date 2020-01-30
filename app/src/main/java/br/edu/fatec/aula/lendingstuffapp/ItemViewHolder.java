package br.edu.fatec.aula.lendingstuffapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView dateTextView;
    private TextView descriptionTextView;
    private TextView nameTextView;
    private Button editButton;
    private Button deleteButton;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        dateTextView = itemView.findViewById(R.id.date);
        descriptionTextView = itemView.findViewById(R.id.description);
        nameTextView = itemView.findViewById(R.id.name);
        editButton = itemView.findViewById(R.id.edit_button);
        deleteButton = itemView.findViewById(R.id.delete_button);
    }

    public TextView getDateTextView() {
        return dateTextView;
    }

    public void setDateTextView(TextView dateTextView) {
        this.dateTextView = dateTextView;
    }

    public TextView getDescriptionTextView() {
        return descriptionTextView;
    }

    public void setDescriptionTextView(TextView descriptionTextView) {
        this.descriptionTextView = descriptionTextView;
    }

    public TextView getNameTextView() {
        return nameTextView;
    }

    public void setNameTextView(TextView nameTextView) {
        this.nameTextView = nameTextView;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }
}
