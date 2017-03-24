package shipdoandem.amytateam.org.shipdoandem.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.greenrobot.eventbus.EventBus;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shipdoandem.amytateam.org.shipdoandem.R;
import shipdoandem.amytateam.org.shipdoandem.databases.models.UserRegisterRespon;
import shipdoandem.amytateam.org.shipdoandem.evenbus.SentUserIdEvent;
import shipdoandem.amytateam.org.shipdoandem.network.NetContext;
import shipdoandem.amytateam.org.shipdoandem.network.UserService;

public class LoginActivity extends AppCompatActivity {

    private LoginButton bt_loginFacebook;
    private CallbackManager callbackManager;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        getReferences();
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        initFaceBook();
    }

    private void getReferences() {
        bt_loginFacebook = (LoginButton) findViewById(R.id.bt_loginFaceBook);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    //Lấy Avatar
    public URL extractFacebookIcon(String id) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageURL = new URL("http://graph.facebook.com/" + id
                    + "/picture?type=large");
            return imageURL;
        } catch (Throwable e) {
            return null;
        }
    }

    public void initFaceBook() {

        bt_loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            private ProfileTracker mProfileTracker;

            @Override
            public void onSuccess(final LoginResult loginResult) {
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Procesando datos...");
                //progressDialog.show();
                if (Profile.getCurrentProfile() == null) {
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                            // profile2 is the new profile
                            UserService userService = NetContext.instance.create(UserService.class);
                            userService.postUserRegister(new UserRegisterRespon(profile2.getId()))
                                    .enqueue(new Callback<UserRegisterRespon>() {
                                        @Override
                                        public void onResponse(Call<UserRegisterRespon> call, Response<UserRegisterRespon> response) {

                                            if (response.code()==401){
                                                Intent intent = new Intent(context, MainActivity.class);
                                                startActivity(intent);
                                            }else {
                                                EventBus.getDefault().postSticky(new SentUserIdEvent(response.body().getId().get$oid()));
                                                Intent intent = new Intent(context, UserInformationActivity.class);
                                                startActivity(intent);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<UserRegisterRespon> call, Throwable t) {

                                        }
                                    });
                            Log.d(LoginActivity.class.toString(), String.format("onCurrentProfileChanged: %s", profile2.getId()));

                            mProfileTracker.stopTracking();
                            progressDialog.hide();
                        }
                    };
                    // no need to call startTracking() on mProfileTracker
                    // because it is called by its constructor, internally.
                } else {
                    Profile profile = Profile.getCurrentProfile();
                    progressDialog.hide();
                    UserService userService = NetContext.instance.create(UserService.class);
                    userService.postUserRegister(new UserRegisterRespon(profile.getId()))
                            .enqueue(new Callback<UserRegisterRespon>() {
                                @Override
                                public void onResponse(Call<UserRegisterRespon> call, Response<UserRegisterRespon> response) {

                                    if (response.code()==401){
                                        Intent intent = new Intent(context, MainActivity.class);
                                        startActivity(intent);
                                    }else {
                                        EventBus.getDefault().postSticky(new SentUserIdEvent(response.body().getId().get$oid()));
                                        Intent intent = new Intent(context, UserInformationActivity.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserRegisterRespon> call, Throwable t) {

                                }
                            });
                    Log.d(LoginActivity.class.toString(), String.format("onCurrentProfileChanged: %s", profile.getId()));

                }

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

}
