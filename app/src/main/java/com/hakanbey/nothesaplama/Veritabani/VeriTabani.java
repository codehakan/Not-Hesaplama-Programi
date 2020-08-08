package com.hakanbey.nothesaplama.Veritabani;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VeriTabani {

    private FirebaseAuth auth;
    private FirebaseUser currentuser;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private static VeriTabani veritabani=null;
    private String userID;

    public FirebaseAuth getAuth() {
        auth=FirebaseAuth.getInstance();
        return auth;
    }

    public FirebaseUser getCurrentuser() {
        currentuser=getAuth().getCurrentUser();
        return currentuser;
    }

    public FirebaseDatabase getDb() {
        db=FirebaseDatabase.getInstance();
        return db;
    }

    public DatabaseReference getDbref(String referans) {
        dbref=getDb().getReference(referans);
        dbref.keepSynced(true);
        return dbref;
    }

    public static VeriTabani getVeritabani() {
        if(veritabani==null){
            veritabani=new VeriTabani();
        }
        return veritabani;
    }

    public String getUserID() {
        userID=getCurrentuser().getUid();
        return userID;
    }

    private VeriTabani() {
    }
}
