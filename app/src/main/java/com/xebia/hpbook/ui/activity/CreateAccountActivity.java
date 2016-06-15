package com.xebia.hpbook.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.xebia.hpbook.R;
import com.xebia.hpbook.ui.fragment.CreateAccountFragment;

/**
 * CreateAccountActivity class
 */
public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.create_account, new CreateAccountFragment());
        transaction.commit();
    }
}
