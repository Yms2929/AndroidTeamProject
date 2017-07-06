package com.example.androidteamproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        //ListView listView = (ListView)findViewById(R.id.listView);
        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> phoneList = new ArrayList<String>();

//        ContentResolver cr = getContentResolver();
//        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//
//        int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
//        int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
//
//        StringBuilder result = new StringBuilder();
//        while (cursor.moveToNext()) {
//            result.append(cursor.getString(nameIndex) + " : ");
//            nameList.add(cursor.getString(nameIndex));
//
//            String id = cursor.getString(idIndex);
//            Cursor cursor2 = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
//                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id},null);
//
//            int typeIndex = cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
//            int numberIndex = cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//
//            while (cursor2.moveToNext()) {
//                String num = cursor2.getString(numberIndex);
//                switch (cursor2.getInt(typeIndex)) {
//                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
//                        result.append("Mobile : " + num);
//                        phoneList.add(num);
//                        break;
//                    case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
//                        result.append("Home :" + num);
//                        phoneList.add(num);
//                        break;
//                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
//                        result.append("Work :" + num);
//                        phoneList.add(num);
//                        break;
//                }
//            }
//            cursor2.close();
//            result.append("\n");
//        }
//        cursor.close();
//
//        TextView txtResult = (TextView)findViewById(R.id.textView);
//        txtResult.setText(result);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        //listView.setAdapter(adapter);
    }
}
