package jamk.l3326.excercise2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class AddNewItemDialogFragment extends DialogFragment {
    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialogFragment, String name, int q, double p);
        public void onDialogNegativeClick(DialogFragment dialogFragment);
    }

    DialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement DialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.fragment_add_new_item_dialog, null);
        builder.setView(dialogView)
                .setTitle("Add new item")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editName = (EditText)dialogView.findViewById(R.id.name_edit);
                        EditText editQuantity = (EditText)dialogView.findViewById(R.id.quantity_edit);
                        EditText editPrice = (EditText)dialogView.findViewById(R.id.price_edit);

                        listener.onDialogPositiveClick(AddNewItemDialogFragment.this,
                                editName.getText().toString(),
                                Integer.valueOf(editQuantity.getText().toString()),
                                Double.valueOf(editPrice.getText().toString()));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogNegativeClick(AddNewItemDialogFragment.this);
                    }
                });

        return builder.create();
    }
}
