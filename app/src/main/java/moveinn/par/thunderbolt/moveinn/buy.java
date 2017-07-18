package moveinn.par.thunderbolt.moveinn;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import moveinn.par.thunderbolt.moveinn.sql.DataBaseArea;

import static moveinn.par.thunderbolt.moveinn.UserLocalStore.SP_NAME;

public class buy extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    SearchView sv;
    TextView tv, tv1, tv2, tv3;
    SeekBar sk;
    Spinner sp;
    RadioGroup rg;
    RadioButton rb;
    Button btn;
    View ios;
    String i="",projectStatus="",bedroom="";
    DataBaseArea dbAdd;
    String username;
    UserLocalStore u;
    Details details;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ios = inflater.inflate(R.layout.buyfrag, container, false);
        sv = (SearchView) ios.findViewById(R.id.searchView2);
        tv = (TextView) ios.findViewById(R.id.textView);
        tv1 = (TextView) ios.findViewById(R.id.textView3);
        tv2 = (TextView) ios.findViewById(R.id.textView4);
        tv3 = (TextView) ios.findViewById(R.id.textView5);
        sk = (SeekBar) ios.findViewById(R.id.seekBar);
        sp = (Spinner) ios.findViewById(R.id.spinner);
        btn=(Button) ios.findViewById(R.id.button3);
        rg=(RadioGroup) ios.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        btn.setOnClickListener(this);
        sp.setOnItemSelectedListener(this);
        u=new UserLocalStore(getActivity());
        username=u.userLocalDatabase.getString("username",SP_NAME);
     //   addListenerOnButton();
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressvalue, boolean fromuser) {
                i=sk.getProgress()+" lakh";
                tv3.setText(i);




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity().getApplicationContext(), "start", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity().getApplicationContext(), "stop", Toast.LENGTH_LONG).show();
            }
        });
        return ios;
    }

    @Override
    public void onClick(View view) {
        if(username.equals("") || i.equals("") ||projectStatus.equals("")|| bedroom.equals("")){
            Toast.makeText(getActivity().getApplicationContext(), "Details must be filled", Toast.LENGTH_LONG).show();
            return;
        }

       // Toast.makeText(getActivity().getApplicationContext(),"Entry Created",Toast.LENGTH_LONG).show();

        Intent i = new Intent(getActivity(), Lists.class);
        startActivity(i);

    }



    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        bedroom=((RadioButton) radioGroup.findViewById(i)).getText().toString();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        projectStatus=parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

/*   private void addListenerOnButton() {
        rg = (RadioGroup) ios.findViewById(R.id.rg);
        btn = (Button) ios.findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                rb = (RadioButton) ios.findViewById(selectedId);
                //write fiunction of database...
            }
        });


    } */
}