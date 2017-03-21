package shipdoandem.amytateam.org.shipdoandem.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.utils.Utils;

public class UserInformationActivity extends AppCompatActivity {

    @BindView(R.id.iv_beer)
    ImageView ivBeer;
    @BindView(R.id.civ_user)
    CircleImageView civUser;
    @BindView(R.id.btn_update_user)
    Button btnUpdateUser;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        Utils.setTitleActionBar(this,"Cập nhật thông tin tài khoản");
        ButterKnife.bind(this);
        context=this;
        setupUI();
    }

    private void setupUI() {
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
