package friendsforever.fyp.app.friendsforever.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;

import friendsforever.fyp.app.friendsforever.Model.ImageUploadModel;
import friendsforever.fyp.app.friendsforever.Model.Users;
import friendsforever.fyp.app.friendsforever.R;

public class SignUpActivity extends AppCompatActivity {
    private AVLoadingIndicatorView loader;
    EditText userName, Password, PhoneNo, Address, Email;
    String userNameStr, PasswordStr, PhoneNoStr, AddressStr, EmailStr;
    Button btnSignUp,mSelecteImgBtn;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    Users model;
    ImageView imgUserPhoto;
    private  static  final int RC_PHOTO_PICKER=1;
    StorageReference UserprofilePicRef;
    private Uri selectedProfileImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        UserprofilePicRef = FirebaseStorage.getInstance().getReference().child("UserProfilePics");
        reference=FirebaseDatabase.getInstance().getReference("User").child(mAuth.getUid());
        loader = findViewById(R.id.userLoader);
        userName=findViewById(R.id.username);
        Email=findViewById(R.id.useremail);
        Password=findViewById(R.id.userpwd);
        PhoneNo=findViewById(R.id.userphone);
        Address=findViewById(R.id.useradress);
        imgUserPhoto=findViewById(R.id.imgvw);
        mSelecteImgBtn=findViewById(R.id.photo);
        mSelecteImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"complete action"),2);
            }
        });
        btnSignUp=findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameStr=userName.getText().toString();
                EmailStr=Email.getText().toString();
                PasswordStr=Password.getText().toString();
                PhoneNoStr=PhoneNo.getText().toString();
                AddressStr=Address.getText().toString();
                if (userNameStr.isEmpty()){
                    userName.setError("Plz enter userName");
                }else if (PasswordStr.isEmpty()){
                    Password.setError("Plz enter Password");
                }else if (PhoneNoStr.isEmpty()){
                    PhoneNo.setError("Plz enter phone no");
                }else if (AddressStr.isEmpty()){
                    Address.setError("Plz enter Address");
                }else if (EmailStr.isEmpty()){
                    Email.setError("Plz enter Email");
                }else {
                    mAuth.createUserWithEmailAndPassword(EmailStr,PasswordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            model = new Users(userNameStr, EmailStr, PasswordStr, PhoneNoStr,AddressStr,"");
                            reference.setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(SignUpActivity.this, "data stored successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }


            }

        });



    }

    private void uploadImageAndData() {
        final StorageReference mProfilePic = UserprofilePicRef.child(selectedProfileImageUri.getLastPathSegment());
        mProfilePic.putFile(selectedProfileImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mProfilePic.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String profilrUri = uri.toString();
                        String id = reference.push().getKey();
                        model = new Users(userNameStr, EmailStr, PasswordStr, PhoneNoStr,AddressStr,profilrUri);
                        reference.setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SignUpActivity.this, "data stored successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null)
            selectedProfileImageUri = data.getData();

        imgUserPhoto.setImageURI(selectedProfileImageUri);
    }



}
