package moveinn.par.thunderbolt.moveinn;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class rent extends Fragment {
    SearchView sv;
    TextView tv,tv1,tv2,tv3;
    SeekBar sk;
    Spinner sp;
    RadioGroup rg;
    RadioButton rb;
    Button btn;
    View android;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        android = inflater.inflate(R.layout.rentfrag, container, false);


        sv = (SearchView)android.findViewById(R.id.searchView2);
        tv = (TextView) android.findViewById(R.id.textView);
        tv1 = (TextView) android.findViewById(R.id.textView3);
        tv2 = (TextView) android.findViewById(R.id.textView4);
        tv3 = (TextView) android.findViewById(R.id.textView5);
        sk = (SeekBar) android.findViewById(R.id.seekBar);
        sp = (Spinner) android.findViewById(R.id.spinner);
        addListenerOnButton();
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressvalue, boolean fromuser) {
                tv3.setText( sk.getProgress() + "lakh " );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity().getApplicationContext(),"start",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity().getApplicationContext(),"stop", Toast.LENGTH_LONG).show();
            }
        });

        return android;
    }
    private void addListenerOnButton() {
        rg = (RadioGroup) android.findViewById(R.id.rg);
        btn = (Button) android.findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Lists.class);
                startActivity(i);
            }
        });
    }}