package jamk.l3326.excercise1;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextEntryDialogFragment.TextEntryDialogListener {

    private final String TEXTVIEW_STATEKEY = "TEXTVIEW_STATEKEY";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        TextView textView = (TextView)findViewById(R.id.textView);
        outState.putString(TEXTVIEW_STATEKEY, textView.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.textView);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(TEXTVIEW_STATEKEY)) {
                String text = savedInstanceState.getString(TEXTVIEW_STATEKEY);
                textView.setText(text);
            }
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment, String text) {
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(text);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {
        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        TextEntryDialogFragment dialogFragment = new TextEntryDialogFragment();
        dialogFragment.show(getFragmentManager(), "Text dialog");
    }
}
