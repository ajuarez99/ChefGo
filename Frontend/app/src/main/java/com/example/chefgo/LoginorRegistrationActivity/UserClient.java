package com.example.chefgo.LoginorRegistrationActivity;

import com.example.chefgo.DomainObjects.LoginDomain;
import com.example.chefgo.DomainObjects.UserDomain;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import com.auth0.android.Auth0;
import com.auth0.android.Auth0Exception;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.VoidCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
public interface UserClient {
    @POST("login")
    Call<UserDomain> login(@Body LoginDomain login);

    @GET
    Call<ResponseBody> getSecret(@Header("Authorization")String authToken);
}
