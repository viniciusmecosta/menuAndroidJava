package com.vinicius.menu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class ObservationDialogFragment extends DialogFragment {
    public interface ObservationDialogListener {
        void onDialogPositiveClick(String observation, int position);
    }
    ObservationDialogListener listener;
    private final int position;
    public ObservationDialogFragment(ObservationDialogListener listener, int position) {
        this.listener = listener;
        this.position = position;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Adicionar Observação")
                .setView(R.layout.observation_dialog)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog d = Dialog.class.cast(dialog);
                        String observation = ((EditText) d.findViewById(R.id.observation)).getText().toString();
                        listener.onDialogPositiveClick(observation, position);
                    }
                })
                .setNegativeButton("Cancelar", (dialog, id) -> {});
        return builder.create();
    }
}