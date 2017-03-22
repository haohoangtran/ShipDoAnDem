package shipdoandem.amytateam.org.shipdoandem.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.databases.models.UserRespon;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SentUserIdEvent;
import shipdoandem.amytateam.org.shipdoandem.network.NetContext;
import shipdoandem.amytateam.org.shipdoandem.network.UserService;
import shipdoandem.amytateam.org.shipdoandem.utils.Utils;

public class UserInformationActivity extends AppCompatActivity {

    @BindView(R.id.iv_beer)
    ImageView ivBeer;
    @BindView(R.id.civ_user)
    CircleImageView civUser;
    @BindView(R.id.btn_update_user)
    Button btnUpdateUser;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_location)
    EditText etAddress;
    private String id;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        EventBus.getDefault().register(this);
        Utils.setTitleActionBar(this,"Cập nhật thông tin tài khoản");
        ButterKnife.bind(this);
        context=this;
        setupUI();
    }

    private void setupUI() {
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserRespon userRespon = new UserRespon(etUserName.getText().toString(),
                        etAddress.getText().toString(),
                        etPhoneNumber.getText().toString());
                UserService userService = NetContext.instance.create(UserService.class);
                userService.postUserInfo(userRespon).enqueue(new Callback<UserRespon>() {
                    @Override
                    public void onResponse(Call<UserRespon> call, Response<UserRespon> response) {
                        Toast.makeText(context,"Cập nhật thành công !",Toast.LENGTH_SHORT).show();
//                        Log.d(UserInformationActivity.class.toString(), String.format("onResponse: %s", response.body().toString()));
                    }

                    @Override
                    public void onFailure(Call<UserRespon> call, Throwable t) {
                        Toast.makeText(context,"Lỗi cập nhật !",Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    void recevice(SentUserIdEvent sentInfoUserEvent){
        this.id = sentInfoUserEvent.getId();
        Log.d(UserInformationActivity.class.toString(), String.format("recevice: %s", id));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
