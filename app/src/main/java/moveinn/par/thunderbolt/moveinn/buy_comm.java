package moveinn.par.thunderbolt.moveinn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class buy_comm extends Fragment {
    SearchView sv;
    TextView tv,tv1,tv2,tv3,tv4;
    SeekBar sk,sk1;
    Spinner sp;
    Button btn;
    View android;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        android = inflater.inflate(R.layout.buycom, container, false);


        sv = (SearchView)android.findViewById(R.id.searchView2);
        tv = (TextView) android.findViewById(R.id.textView);
        tv1 = (TextView) android.findViewById(R.id.textView3);
        tv2 = (TextView) android.findViewById(R.id.textView7);
        tv3 = (TextView) android.findViewById(R.id.textView5);
        tv4 = (TextView) android.findViewById(R.id.textView6);
        sk = (SeekBar) android.findViewById(R.id.seekBar);
        sk1 = (SeekBar) android.findViewById(R.id.seekBar2);
        sp = (Spinner) android.findViewById(R.id.spinner);
        btn = (Button)android.findViewById(R.id.button3);
    

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressvalue, boolean fromuser) {
                tv3.setText( sk.getProgress() + "lakh " );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity().getApplicationContext(), "start", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity().getApplicationContext(),"stop", Toast.LENGTH_LONG).show();
            }
        });

        sk1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress1 = 0;

            public void onProgressChanged(SeekBar seekBar, int progressvalue, boolean fromuser) {
                tv4.setText( sk1.getProgress() + "sq.ft. " );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity().getApplicationContext(), "start", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity().getApplicationContext(),"stop", Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Lists.class);
                startActivity(i);
            }
        });
        

        return android;
    }
}
