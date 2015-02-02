package mhyhre.rsamobile.fragments;

import mhyhre.rsamobile.RSAComplexKey;
import mhyhre.rsamobile.R;
import mhyhre.rsamobile.RSACrypter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class OperationsFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private View rootView;
	
	private EditText editSource;	
	private EditText editResult;
	
	private RadioButton radioButtonEncrypt;

	
	public static OperationsFragment newInstance(int sectionNumber) {
		OperationsFragment fragment = new OperationsFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public OperationsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.fragment_operations, container, false);
		
		Button buttonProcess = (Button) rootView.findViewById(R.id.buttonProcess);

		editSource = (EditText) rootView.findViewById(R.id.editTextSource);
		editResult = (EditText) rootView.findViewById(R.id.editTextResult);
		
		radioButtonEncrypt = (RadioButton) rootView.findViewById(R.id.radioButtonEncrypt);

		buttonProcess.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				RSAComplexKey key = KeyFragment.getLastRSAKey();
				if(key != null) {
				
					if(radioButtonEncrypt.isChecked()) { // If selected EnCrypt
						int[] encryptedData = RSACrypter.EnCrypt(editSource.getText().toString(), key);
						editResult.setText(RSACrypter.convertLongArrayToString(encryptedData));

						Toast.makeText(rootView.getContext(), getString(R.string.Encrypted), Toast.LENGTH_SHORT).show(); 
						
					} else { // If selected DeCrypt
						int[] sourceData = RSACrypter.convertStringToLongArray(editResult.getText().toString());
						String decryptedData = RSACrypter.DeCrypt(sourceData, key);
						editSource.setText(decryptedData);
						Toast.makeText(rootView.getContext(), getString(R.string.Decrypted), Toast.LENGTH_SHORT).show();					
					}
				} else {
					Toast.makeText(rootView.getContext(), getString(R.string.KeyNotGenerated), Toast.LENGTH_SHORT).show(); 
					
				}
			}
		});
				
		return rootView;
	}
	
	
	
	
	
  
}
