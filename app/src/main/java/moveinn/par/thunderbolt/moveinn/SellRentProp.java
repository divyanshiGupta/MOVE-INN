package moveinn.par.thunderbolt.moveinn;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import moveinn.par.thunderbolt.moveinn.sql.DataBaseArea;


public class SellRentProp extends Fragment {
 private final String serverUrl = "http://www.movein.webuda.com/website/index1.php";
    RelativeLayout sellpage;
    TextView buysellt, pttext,patext,pptext;
    Spinner bsspinner,ptspinner,state,city,spinner5,spinner6;
    EditText locality,society,address,amount,area,rooms,title,desc;
    Button submit;
    CardView view,view1;
	String optiont,proptypet,statet,cityt,localityt,addresst,amountt,areat,roomst,titlet,desct,societyt;
    DataBaseArea dbAdd;
    Details details;

    View rootview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.sellrentprop,container,false);

		
        sellpage = (RelativeLayout) rootview.findViewById(R.id.sellpage);
        ScrollView sv=(ScrollView)rootview.findViewById(R.id.sv);
        buysellt=(TextView)rootview.findViewById(R.id.buysellt);
        pptext=(TextView)rootview.findViewById(R.id.pptext);
        patext=(TextView)rootview.findViewById(R.id.patext);
        pttext=(TextView)rootview.findViewById(R.id.pttext);
        bsspinner=(Spinner)rootview.findViewById(R.id.bsspinner);
        ptspinner=(Spinner)rootview.findViewById(R.id.ptspinner);
        state=(Spinner)rootview.findViewById(R.id.state);
       // spinner5=(Spinner)rootview.findViewById(R.id.spinner5);
        //spinner6=(Spinner)rootview.findViewById(R.id.spinner6);
        view=(CardView)rootview.findViewById(R.id.view);
        view1=(CardView)rootview.findViewById(R.id.view1);
        city=(Spinner)rootview.findViewById(R.id.city);
        locality=(EditText) rootview.findViewById(R.id.locality);
        society=(EditText) rootview.findViewById(R.id.society);
        address=(EditText) rootview.findViewById(R.id.address);
		amount=(EditText) rootview.findViewById(R.id.Price);
		area=(EditText) rootview.findViewById(R.id.Area);
		rooms=(EditText) rootview.findViewById(R.id.rooms);
		title=(EditText) rootview.findViewById(R.id.Title);
		desc=(EditText) rootview.findViewById(R.id.Description);
        dbAdd = new DataBaseArea(getActivity().getApplicationContext());



		submit=(Button) rootview.findViewById(R.id.submit);
		 submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				optiont=bsspinner.getSelectedItem().toString();
				proptypet=ptspinner.getSelectedItem().toString();
				statet=state.getSelectedItem().toString();
				cityt=city.getSelectedItem().toString();
				localityt=locality.getText().toString();
				societyt=society.getText().toString();
				addresst=address.getText().toString();
				amountt=amount.getText().toString();
				areat=area.getText().toString();
				roomst=rooms.getText().toString();
				titlet=title.getText().toString();
				desct=desc.getText().toString();
				
               
                if(optiont.equals("") || proptypet.equals("") || statet.equals("")|| cityt.equals("")|| localityt.equals("")|| societyt.equals("")|| addresst.equals("")|| amountt.equals("")|| areat.equals("")|| titlet.equals("")|| desct.equals("")){
                    Toast.makeText(getActivity(), "Details must be filled", Toast.LENGTH_LONG).show();
                    return;
                }
                
                // request authentication with remote server4
                /*AsyncDataClass asyncRequestObject = new AsyncDataClass();
                asyncRequestObject.execute(serverUrl,titlet,desct, optiont,amountt,areat,roomst, localityt,societyt,cityt,proptypet,statet,addresst);
            */

               // dbAdd = new DataBaseArea(getActivity().getApplicationContext());

                details = new Details();

                details.setChoice(optiont);
                details.setType(proptypet);
                details.setAmount(amountt);
                details.setRooms(roomst);

                dbAdd.addArea(details);
                Toast.makeText(getActivity().getApplicationContext(), "Successfully Submitted", Toast.LENGTH_SHORT).show();


                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);

            }
        });



        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = Integer.toString(state.getSelectedItemPosition());
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                //state.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                ArrayAdapter<String> spinnerArrayAdapter=null;
                if (state.getSelectedItemPosition() == 0) {

                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.andaman)); //selected item will look like a spinner set from XML


                }
                 else if(state.getSelectedItemPosition() == 1){
                   spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Andhra)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 2){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ArunachalPradesh)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 3){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Assam)); //selected item will look like a spinner set from XML

                }

                else if(state.getSelectedItemPosition() == 4){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Bihar)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 5){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Chandigarh)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 6){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Chhattisgarh)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 7){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.DadraNagarHaveli)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 8){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.DamanDiu)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 9){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Delhi)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 10){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Goa)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 11){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Gujarat)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 12){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Haryana)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 13){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.HimachalPradesh)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 14){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.JammuKashmir)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 15){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Jharkhand)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 16){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Karnataka)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 17){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Kerala)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 18){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Lakshadweep)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 19){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.MadhyaPradesh)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 20){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Maharashtra)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 21){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Manipur)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 22){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Meghalaya)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 23){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Mizoram)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 24){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Nagaland)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 25){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Orissa)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 26){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Pondicherry)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 27){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Punjab)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 28){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Rajasthan)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 29){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Sikkim)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 30){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.TamilNadu)); //selected item will look like a spinner set from XML

                }

                else if(state.getSelectedItemPosition() == 31){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Tripura)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 32){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.UttarPradesh)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 33){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Uttaranchal)); //selected item will look like a spinner set from XML

                }
                else if(state.getSelectedItemPosition() == 34){
                    spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.WestBengal)); //selected item will look like a spinner set from XML

                }

                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //city.removeAllViews();
                city.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CardView addphoto=(CardView)rootview.findViewById(R.id.view);
        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new addphoto()).commit();


            }
        });
        return rootview;


    }
	




    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
            HttpConnectionParams.setSoTimeout(httpParameters, 5000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpPost httpPost = new HttpPost(params[0]);

            String jsonResult = "";
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("title", params[1]));
                nameValuePairs.add(new BasicNameValuePair("desc", params[2]));
                nameValuePairs.add(new BasicNameValuePair("option", params[3]));
				nameValuePairs.add(new BasicNameValuePair("amount", params[4]));
                nameValuePairs.add(new BasicNameValuePair("area", params[5]));
                nameValuePairs.add(new BasicNameValuePair("rooms", params[6]));
				nameValuePairs.add(new BasicNameValuePair("locality", params[7]));
				nameValuePairs.add(new BasicNameValuePair("society", params[8]));
				nameValuePairs.add(new BasicNameValuePair("city", params[9]));
				nameValuePairs.add(new BasicNameValuePair("ptspinner", params[10]));
				nameValuePairs.add(new BasicNameValuePair("state", params[11]));
				nameValuePairs.add(new BasicNameValuePair("address", params[12]));




				
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
                System.out.println("Returned Json object " + jsonResult.toString());

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResult;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("Resulted Value: " + result);
            if(result.equals("") || result == null){
                Toast.makeText(getActivity(), "Server connection failed", Toast.LENGTH_LONG).show();
                return;
            }
            int jsonResult = returnParsedJsonObject(result);
            if(jsonResult == 0){
                Toast.makeText(getActivity(), "Invalid Details", Toast.LENGTH_LONG).show();
                return;
            }
            if(jsonResult == 1){
                //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                //intent.putExtra("USERNAME", enteredUsername);
                //intent.putExtra("MESSAGE", "You details have been submitted");
                //startActivity(intent);
				Toast.makeText(getActivity(), "Invalid Details", Toast.LENGTH_LONG).show();
                return;
            }
        }
        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = br.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return answer;
        }
    }
    private int returnParsedJsonObject(String result){

        JSONObject resultObject = null;
        int returnedResult = 0;
        try {
            resultObject = new JSONObject(result);
            returnedResult = resultObject.getInt("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnedResult;
    }



    }
