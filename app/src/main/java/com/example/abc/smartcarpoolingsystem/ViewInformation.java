package com.example.abc.smartcarpoolingsystem;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewInformation extends AppCompatActivity {
    private ListView listView,listViewcontactlist;
    private DatabaseReference databaseReference;
    private List<DataBean> itemlist;
    private List<DataBean> finaldatalist;
    private List<ContactListBean> contactlist;
    private FirebaseAuth firebaseAuth;
    ContactListBean contactListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_information);
        firebaseAuth=FirebaseAuth.getInstance();

        listView=(ListView)findViewById(R.id.listviewforuser);
       // listViewcontactlist=(ListView)findViewById(R.id.listviewcontactlist);
        itemlist=new ArrayList<>();
        contactlist=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("data");


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(ViewInformation.this,"Booking Done",Toast.LENGTH_LONG).show();
                return false;
            }
        });
     /*   contactlist.clear();


        RecyclerViewAdapterContactList adapterContactList=new RecyclerViewAdapterContactList
                (ViewInformation.this,contactlist);
        listViewcontactlist.setAdapter(adapterContactList);*/
     init();

    }
    private void init(){
        //creating contact list
        contactlist=new ArrayList<>();

        ContentResolver resolver=getContentResolver();
        Cursor cursor=resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            contactListBean=new ContactListBean();

            String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            contactListBean.setId(id);
            contactListBean.setName(name);
         //  System.out.println("name:"+name+"nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
            Cursor cursor1=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = ?",new String[]{ id },null);
            while (cursor1.moveToNext()){
                String contact=cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
           //    System.out.println("number:"+contact+"ccccccccccccccccccccccccccccccccccc");
                contactListBean.setNumber(contact);
            }
            contactlist.add(contactListBean);
        }


/*
        RecyclerViewAdapterContactList adapterContactList=new RecyclerViewAdapterContactList
                (ViewInformation.this,contactlist);
        listViewcontactlist.setAdapter(adapterContactList);
*/



//creating data list







    }
    @Override
    protected void onStart(){
      super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemlist.clear();
                for(DataSnapshot itemsnapshot:dataSnapshot.getChildren()){
                    DataBean dataBean=itemsnapshot.getValue(DataBean.class);
                    itemlist.add(dataBean);
                }

/*
                RecyclerViewAdapter adapter=new RecyclerViewAdapter(ViewInformation.this,itemlist);
                listView.setAdapter(adapter);*/


             //   System.out.println(itemlist.size()+"######################################");
             //   System.out.println(contactlist.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
finaldatalist=new ArrayList<>();
         finaldatalist=   searchbyno(itemlist,contactlist);

                RecyclerViewAdapter adapter=new RecyclerViewAdapter(ViewInformation.this,finaldatalist);
                listView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewInformation.this,"cancelled",Toast.LENGTH_LONG).show();
            }
        });

    }

    private List<DataBean> searchbyno(List<DataBean> itemlist, List<ContactListBean> contactlist) {
        ArrayList<DataBean> l3=new ArrayList<>();

      //  System.out.println(itemlist.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        // System.out.println("");
       // System.out.println(contactlist.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        for(int i=0;i<itemlist.size();i++){
            for(int j=0;j<contactlist.size();j++){

              //System.out.println(itemlist.get(1).getNumber()+"IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
              // System.out.println(contactlist.get(2).getNumber()+" NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
                if(itemlist.get(i).getNumber()!=null && contactlist.get(j).getNumber()!=null){
                if(contactlist.get(j).getNumber().contains(itemlist.get(i).getNumber())){
                   // System.out.println(itemlist.get(i)+"###########################");
                    l3.add(itemlist.get(i));

                }
            }}

        }
        itemlist.removeAll(l3);
        l3.addAll(itemlist);
        return l3;
    }


}
