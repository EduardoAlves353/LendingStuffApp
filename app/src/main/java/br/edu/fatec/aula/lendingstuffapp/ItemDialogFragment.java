package br.edu.fatec.aula.lendingstuffapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ItemDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public interface NoticeDialogListener {
        void onDialogPositiceClick(Item item, int position);
    }

    public static final String TAG_CREATE = "CREATE_ITEM";
    public static final String TAG_EDIT = "EDIT_ITEM";

    public static final String EXTRA_TITLE = "EXTRE_TITLE";
    public static final String EXTRA_POSITIVE_BUTTON = "EXTRA_POSITIVE_BUTTON";
    public static final String EXTRA_ITEM = "EXTRA_ITEM";
    public static final String EXTRA_POSITION = "EXTRA_POSITION";

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;

    private Item currebtItem;
    private NoticeDialogListener listener;

    public ItemDialogFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (NoticeDialogListener)context;
        } catch (ClassCastException e){
            e.printStackTrace();
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Context context = getActivity();
        final Bundle bundle = getArguments();

        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        dialog.setTitle(bundle.getString(EXTRA_TITLE));
        dialog.setPositiveButton(bundle.getString(EXTRA_POSITIVE_BUTTON), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currebtItem.setName(nameEditText.toString());
                currebtItem.setDescription(descriptionEditText.toString());
                currebtItem.setDate(dateEditText.toString());
                listener.onDialogPositiceClick(currebtItem, -1);
            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_item, null,false);
        dialog.setView(view);

        setFindViewById(view, bundle);
        setDatePickerDialog(context);
        return dialog.create();
    }

    private void setFindViewById(View view, final Bundle bundle){
        nameEditText = view.findViewById(R.id.name);
        descriptionEditText = view.findViewById(R.id.description);
        dateEditText = view.findViewById(R.id.date);
        currebtItem = new Item("", "", "");

        if (getTag().equals(TAG_EDIT))
        {
            currebtItem = (Item)bundle.getSerializable(EXTRA_ITEM);
            nameEditText.setText(currebtItem.getName());
            descriptionEditText.setText(currebtItem.getDescription());
            dateEditText.setText(currebtItem.getDate());
        }
    }

    private void setDatePickerDialog(final Context context){
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));

        String currentDate = dateEditText.getText().toString();
        if (!currentDate.isEmpty()){
            try {
                Date date = sdf.parse(currentDate);
                calendar.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog picker = new DatePickerDialog(context, null, year, month, day);

        picker.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String date = String.format("%02d%02d/%04d", day, month, year);
        dateEditText.setText(date);
        dateEditText.clearFocus();
    }
}
