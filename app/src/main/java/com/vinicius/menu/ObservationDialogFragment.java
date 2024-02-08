package com.vinicius.menu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
        // Infla o layout personalizado para o AlertDialog
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.observation_dialog, null);

        // Encontra o EditText dentro do layout inflado
        final EditText observationInput = dialogView.findViewById(R.id.observation);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Adicionar Observação")
                .setView(dialogView) // Define o layout personalizado para o AlertDialog
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Obtém a observação do EditText
                        String observation = observationInput.getText().toString();
                        listener.onDialogPositiveClick(observation, position);
                    }
                })
                .setNegativeButton("Cancelar", (dialog, id) -> {});

        return builder.create();
    }
}