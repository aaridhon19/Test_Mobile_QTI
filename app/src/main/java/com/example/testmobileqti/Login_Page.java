package com.example.testmobileqti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class Login_Page extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private RelativeLayout buttonLogin;
    private static final String PREFS_NAME = "MyPrefsFile";
    private TextView errorMessageEmail, errorMessagePassword;
    private RelativeLayout formEmail, formPassword;

    private AppCompatImageView eyePass;

    private int marginTopWithError;
    private int marginTopWithoutError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isLoggedIn()) {
            navigateToHome();
        } else {
            setContentView(R.layout.activity_login_page);

//            String swaggerUrl = "http://117.54.250.99:28089"; // Sesuaikan dengan URL Swagger Anda
//            ApiService apiService = RetrofitClient.getApiService(swaggerUrl);

//            Call<YourResponseModel> call = apiService.getYourData();
//            call.enqueue(new Callback<YourResponseModel>() {
//                @Override
//                public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
//                    if (response.isSuccessful()) {
//                        YourResponseModel yourResponseModel = response.body();
//                        // Handle respons sukses di sini
//                        int id = yourResponseModel.getId();
//                        String name = yourResponseModel.getName();
//                        String email = yourResponseModel.getEmail();
//                        // ...
//                    } else {
//                        Log.e("RetrofitError", "Response code: " + response.code());
//                        // Handle respons gagal di sini
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<YourResponseModel> call, Throwable t) {
//                    Log.e("RetrofitError", "Failed to make API call", t);
//                    // Handle kegagalan di sini
//                }

            editTextEmail = findViewById(R.id.email);
            editTextPassword = findViewById(R.id.password);
            buttonLogin = findViewById(R.id.button_login);
            errorMessageEmail = findViewById(R.id.errorMessageEmail);
            errorMessagePassword = findViewById(R.id.errorMessagePassword);
            formEmail = findViewById(R.id.form_email);
            formPassword = findViewById(R.id.form_password);

            marginTopWithError = getResources().getDimensionPixelSize(R.dimen.margin_top_with_error);
            marginTopWithoutError = getResources().getDimensionPixelSize(R.dimen.margin_top_without_error);

            eyePass = findViewById(R.id.eye_pass);

            eyePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePasswordVisibility();
                }
            });


            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    login();
                }
            });
        }
    }

    private boolean isLoggedIn() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getBoolean("isLoggedIn", false);
    }

    private void setLoggedIn(boolean loggedIn) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", loggedIn);
        editor.apply();
    }

    private void login() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        boolean isEmailEmpty = TextUtils.isEmpty(email);
        boolean isPasswordEmpty = TextUtils.isEmpty(password);
        boolean isEmailNotEmpty = !TextUtils.isEmpty(email);
        boolean isPasswordNotEmpty = !TextUtils.isEmpty(password);

        // Handle error message for email
        if (isEmailEmpty) {
            errorMessageEmail.setVisibility(View.VISIBLE);
            errorMessageEmail.setText("This form is required");

            // Set background color to red for form_email
            formEmail.setBackgroundResource(R.drawable.form_input_error);

            // Mengatur properti font size, color, dan family pada errorMessageEmail
            errorMessageEmail.setTextColor(getResources().getColor(R.color.errorText)); // Ganti dengan warna yang diinginkan
            errorMessageEmail.setTextSize(getResources().getDimension(R.dimen.errorTextSize)); // Ganti dengan ukuran yang diinginkan
            errorMessageEmail.setTypeface(Typeface.create("red_hat_display", Typeface.NORMAL)); // Ganti dengan jenis huruf yang diinginkan

            // Lower the form_password when there is an error in email
            ViewGroup.MarginLayoutParams paramsPassword = (ViewGroup.MarginLayoutParams) formPassword.getLayoutParams();
            paramsPassword.topMargin = marginTopWithError;
            formPassword.setLayoutParams(paramsPassword);
        } else {
            errorMessageEmail.setVisibility(View.GONE);

            // Return form_password to normal position when there is no error in email
            ViewGroup.MarginLayoutParams paramsPassword = (ViewGroup.MarginLayoutParams) formPassword.getLayoutParams();
            paramsPassword.topMargin = marginTopWithoutError;
            formPassword.setLayoutParams(paramsPassword);
        }

        // Handle error message for password
        if (isPasswordEmpty) {
            errorMessagePassword.setVisibility(View.VISIBLE);
            errorMessagePassword.setText("This form is required");

            // Set background color to red for form_password
            formPassword.setBackgroundResource(R.drawable.form_input_error);

            // Mengatur properti font size, color, dan family pada errorMessagePassword
            errorMessagePassword.setTextColor(getResources().getColor(R.color.errorText)); // Ganti dengan warna yang diinginkan
            errorMessagePassword.setTextSize(getResources().getDimension(R.dimen.errorTextSize)); // Ganti dengan ukuran yang diinginkan
            errorMessagePassword.setTypeface(Typeface.create("red_hat_display", Typeface.NORMAL)); // Ganti dengan jenis huruf yang diinginkan

            // Lower the form_password when there is an error in password
            ViewGroup.MarginLayoutParams paramsPassword = (ViewGroup.MarginLayoutParams) formPassword.getLayoutParams();
            paramsPassword.topMargin = marginTopWithError;
            formPassword.setLayoutParams(paramsPassword);
        } else {
            errorMessagePassword.setVisibility(View.GONE);

            // Return form_password to normal position when there is no error in password
            ViewGroup.MarginLayoutParams paramsPassword = (ViewGroup.MarginLayoutParams) formPassword.getLayoutParams();
            paramsPassword.topMargin = marginTopWithoutError;
            formPassword.setLayoutParams(paramsPassword);
        }

        if (isEmailNotEmpty) {
            errorMessageEmail.setVisibility(View.VISIBLE);
            errorMessageEmail.setText("Invalid Email");

            // Set background color to red for form_email
            formEmail.setBackgroundResource(R.drawable.form_input_error);

            // Mengatur properti font size, color, dan family pada errorMessageEmail
            errorMessageEmail.setTextColor(getResources().getColor(R.color.errorText)); // Ganti dengan warna yang diinginkan
            errorMessageEmail.setTextSize(getResources().getDimension(R.dimen.errorTextSize)); // Ganti dengan ukuran yang diinginkan
            errorMessageEmail.setTypeface(Typeface.create("red_hat_display", Typeface.NORMAL)); // Ganti dengan jenis huruf yang diinginkan

            // Lower the form_password when there is an error in email
            ViewGroup.MarginLayoutParams paramsPassword = (ViewGroup.MarginLayoutParams) formPassword.getLayoutParams();
            paramsPassword.topMargin = marginTopWithError;
            formPassword.setLayoutParams(paramsPassword);
        } else if (isEmailNotEmpty && email.equals("adam@test.qti.co.id")) {
            errorMessageEmail.setVisibility(View.GONE);

            // Set background color to blue for form_email
            formEmail.setBackgroundResource(R.drawable.form_input);

            // Return form_password to normal position when there is no error in email
            ViewGroup.MarginLayoutParams paramsPassword = (ViewGroup.MarginLayoutParams) formPassword.getLayoutParams();
            paramsPassword.topMargin = marginTopWithoutError;
            formPassword.setLayoutParams(paramsPassword);
        } else {
            errorMessageEmail.setVisibility(View.VISIBLE);
            errorMessageEmail.setText("Invalid Email");

            // Set background color to red for form_email
            formEmail.setBackgroundResource(R.drawable.form_input_error);

            // Lower the form_password when there is an error in email
            ViewGroup.MarginLayoutParams paramsPassword = (ViewGroup.MarginLayoutParams) formPassword.getLayoutParams();
            paramsPassword.topMargin = marginTopWithError;
            formPassword.setLayoutParams(paramsPassword);
        }


        if (isPasswordNotEmpty) {
            errorMessagePassword.setVisibility(View.VISIBLE);
            errorMessagePassword.setText("Invalid Password");

            // Set background color to red for form_password
            formPassword.setBackgroundResource(R.drawable.form_input_error);

            // Mengatur properti font size, color, dan family pada errorMessagePassword
            errorMessagePassword.setTextColor(getResources().getColor(R.color.errorText)); // Ganti dengan warna yang diinginkan
            errorMessagePassword.setTextSize(getResources().getDimension(R.dimen.errorTextSize)); // Ganti dengan ukuran yang diinginkan
            errorMessagePassword.setTypeface(Typeface.create("red_hat_display", Typeface.NORMAL)); // Ganti dengan jenis huruf yang diinginkan

            // Lower the form_password when there is an error in password
            ViewGroup.MarginLayoutParams paramsPassword = (ViewGroup.MarginLayoutParams) formPassword.getLayoutParams();
            paramsPassword.topMargin = marginTopWithError;
            formPassword.setLayoutParams(paramsPassword);
        }

        //Check credentials
        if (!isEmailEmpty && !isPasswordEmpty) {
            if (email.equals("adam@test.qti.co.id") && password.equals("k7rgb5kt")) {
                // Set background color to red for form_password
                // Set background color to red for form_password
                formEmail.setBackgroundResource(R.drawable.form_input);
                formPassword.setBackgroundResource(R.drawable.form_input);
                saveUserCredentials(email, password);
                navigateToHome();
            } else {
                errorMessagePassword.setVisibility(View.VISIBLE);
                errorMessagePassword.setText("Invalid Password");

                errorMessageEmail.setVisibility(View.VISIBLE);
                errorMessageEmail.setText("Invalid Email");

                // Mengatur properti font size, color, dan family pada errorMessagePassword
                errorMessagePassword.setTextColor(getResources().getColor(R.color.errorText)); // Ganti dengan warna yang diinginkan
                errorMessagePassword.setTextSize(getResources().getDimension(R.dimen.errorTextSize)); // Ganti dengan ukuran yang diinginkan
                errorMessagePassword.setTypeface(Typeface.create("red_hat_display", Typeface.NORMAL)); // Ganti dengan jenis huruf yang diinginkan

                // Lower the form_password when there is an error in password
                ViewGroup.MarginLayoutParams paramsPassword = (ViewGroup.MarginLayoutParams) formPassword.getLayoutParams();
                paramsPassword.topMargin = marginTopWithError;
                formPassword.setLayoutParams(paramsPassword);
            }
        }
    }

    private void saveUserCredentials(String email, String password) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    private void navigateToHome() {
        if (editTextEmail != null) {
            String userEmail = editTextEmail.getText().toString();

            Intent intent = new Intent(this, Home_Page.class);
            intent.putExtra("email_user", userEmail);
            startActivity(intent);
            finish();
        }
    }


    private void togglePasswordVisibility() {
        int inputType = editTextPassword.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            // Password tersembunyi, ubah ke teks biasa terlihat
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            eyePass.setImageResource(R.drawable.eye_invisible_filled);
        } else {
            // Password terlihat, ubah ke format password yang tersembunyi
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            eyePass.setImageResource(R.drawable.eye_visible_filled);
        }

        // Posisikan kursor di akhir teks
        editTextPassword.setSelection(editTextPassword.getText().length());
    }
}
