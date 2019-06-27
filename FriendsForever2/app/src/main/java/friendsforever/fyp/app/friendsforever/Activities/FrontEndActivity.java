package friendsforever.fyp.app.friendsforever.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import friendsforever.fyp.app.friendsforever.R;

public class FrontEndActivity extends AppCompatActivity {
    Button mLogin_go,mSignUp_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_end);
        mSignUp_go=findViewById (R.id.signup_go);
        mSignUp_go.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                startActivity (new Intent(FrontEndActivity.this,SignUpActivity.class));
                Toast.makeText (FrontEndActivity.this, "SignUp pressed", Toast.LENGTH_SHORT).show ( );

            }
        });
        mLogin_go=findViewById (R.id.log_go);
        mLogin_go.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (FrontEndActivity.this,MainActivity.class));
                Toast.makeText (FrontEndActivity.this, "Login Clicked", Toast.LENGTH_SHORT).show ( );

            }
        });
    }
}
