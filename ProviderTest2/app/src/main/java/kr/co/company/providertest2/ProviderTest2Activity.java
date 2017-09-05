package kr.co.company.providertest2;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProviderTest2Activity extends Activity {
    /**
     * Called when the activity is first created.
     */

    EditText nameEdit;
    EditText phoneEdit;/*
    final String[] myDataset ={"rr","ww","22"};*/
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Impo> ImpoLIst;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        nameEdit = (EditText) findViewById(R.id.name);
        phoneEdit = (EditText) findViewById(R.id.phonenumber);

        initLayout();
        initData();


    }

    public void onClick(View v) {
        ContentValues values = new ContentValues();

        String name = nameEdit.getText().toString();
        String phone = phoneEdit.getText().toString();

        if(name==""||name.equals("")){
            Toast.makeText(getApplicationContext(),"이름 입력해!",Toast.LENGTH_LONG).show();
        }
        else if(phone==""||phone.equals("")){
            Toast.makeText(getApplicationContext(),"전화번호 입력해!",Toast.LENGTH_LONG).show();
        }else {
            values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, "com.google");
            values.put(ContactsContract.RawContacts.ACCOUNT_NAME, name);
            Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
            long rawContactId = ContentUris.parseId(rawContactUri);

            values = new ContentValues();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

            values = new ContentValues();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

            Toast.makeText(getApplicationContext(),"저장됨",Toast.LENGTH_LONG).show();

        }
    }


    public void initLayout(){

        mRecyclerView  = (RecyclerView) findViewById(R.id.my_recycler_view);
    }

    public void  initData(){

        ImpoLIst =new ArrayList<Impo>();
        for(int i=0; i<20;i++){
            Impo impo = new Impo();
            impo.setNames("이준혁");
            impo.setNumber("0104308367"+i);
            ImpoLIst.add(impo);
        }
        startAdapter();
    }
    public void startAdapter(){

        mAdapter = new MyAdapter(ImpoLIst,R.layout.my_text_view );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            mRecyclerView.setHasFixedSize(true);


            mRecyclerView.setAdapter(mAdapter);

            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        }
    }

}