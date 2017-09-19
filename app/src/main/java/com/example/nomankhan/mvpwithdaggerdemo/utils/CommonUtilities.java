package com.example.nomankhan.mvpwithdaggerdemo.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.example.nomankhan.mvpwithdaggerdemo.ui.main.MyContact;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Noman Khan on 19/09/17.
 */

public class CommonUtilities {

    public static List<MyContact> getAllContacts(Context ctx) {

        List<MyContact> list = new ArrayList<>();
        ContentResolver contentResolver = ctx.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);


                    while (cursorInfo.moveToNext()) {
                        MyContact info = new MyContact();

                        info.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts
                                .DISPLAY_NAME)));
                        String pn = cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        info.setContactNumber(pn);

                        list.add(info);
                    }

                    cursorInfo.close();
                }
            }
        }

        Collections.sort(list, new Comparator<MyContact>() {
            @Override
            public int compare(MyContact contactModel, MyContact t1) {
                return contactModel.getName().compareToIgnoreCase(t1.getName());
            }
        });

        return list;
    }

    public static boolean hasPermission(Context context, String permission) {
        int res = context.checkCallingOrSelfPermission(permission);
        return res == PackageManager.PERMISSION_GRANTED;
    }

}
