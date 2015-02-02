package mhyhre.rsamobile.fragments;

import mhyhre.rsamobile.RSAComplexKey;
import mhyhre.rsamobile.R;
import mhyhre.rsamobile.RSAKeyGenerator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KeyFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private View rootView;
	
	private static RSAComplexKey RSAKey;
	private EditText editN;
	private EditText editE;	
	private EditText editD;

	public static KeyFragment newInstance(int sectionNumber) {
		KeyFragment fragment = new KeyFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public KeyFragment() {
		RSAKey = null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_key, container, false);

		Button buttonGenerate = (Button) rootView.findViewById(R.id.buttonGenerateKey);
		Button buttonSave = (Button) rootView.findViewById(R.id.buttonSave);
		
		editN = (EditText) rootView.findViewById(R.id.editTextN);
		editE = (EditText) rootView.findViewById(R.id.editTextE);		
		editD = (EditText) rootView.findViewById(R.id.editTextD);

		buttonGenerate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RSAKey = RSAKeyGenerator.generate();
				editN.setText(Integer.toString(RSAKey.getN()));
				editE.setText(Integer.toString(RSAKey.getE()));
				editD.setText(Integer.toString(RSAKey.getD()));
				Toast.makeText(rootView.getContext(), getString(R.string.KeyWasGenerated), Toast.LENGTH_SHORT).show();  
			}
		});
		
		buttonSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				updateInput();
			}
		});
		
		return rootView;
	}
	
	public void updateInput() {
		if(RSAKey == null) {
			try {
				RSAKey = new RSAComplexKey(
						Integer.parseInt(editE.getText().toString()),
						Integer.parseInt(editN.getText().toString()),
						Integer.parseInt(editD.getText().toString()));
			} catch(NumberFormatException e) {
				Toast.makeText(rootView.getContext(), getString(R.string.WrongKeyArguments), Toast.LENGTH_SHORT).show();  
			}
		}
	}
	
	
	public static RSAComplexKey getLastRSAKey() {
		return RSAKey;
	}  
}