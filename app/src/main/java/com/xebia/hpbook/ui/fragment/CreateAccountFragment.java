package com.xebia.hpbook.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.xebia.hpbook.R;

/**
 * CreateAccountFragment class
 */
public class CreateAccountFragment extends Fragment {

    private EditText firstname;
    private EditText lastname;
    private EditText street;
    private EditText zipCode;
    private EditText city;
    private EditText mail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstname = (EditText) view.findViewById(R.id.firstname);
        lastname = (EditText) view.findViewById(R.id.lastname);
        street = (EditText) view.findViewById(R.id.street);
        zipCode = (EditText) view.findViewById(R.id.zip_code);
        city = (EditText) view.findViewById(R.id.city);
        mail = (EditText) view.findViewById(R.id.mail);


        firstname.addTextChangedListener(new CustomTextWatcher(firstname));
        lastname.addTextChangedListener(new CustomTextWatcher(lastname));
        street.addTextChangedListener(new CustomTextWatcher(street));
        zipCode.addTextChangedListener(new CustomTextWatcher(zipCode));
        city.addTextChangedListener(new CustomTextWatcher(city));
        mail.addTextChangedListener(new CustomTextWatcher(mail));

    }

    private class CustomTextWatcher implements TextWatcher {
        private EditText mEditText;

        public CustomTextWatcher(EditText e) {
            mEditText = e;
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mEditText.setHint(" ");

        }

        public void afterTextChanged(Editable s) {
        }
    }

}
