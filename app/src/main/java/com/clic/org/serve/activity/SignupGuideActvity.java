package com.clic.org.serve.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clic.org.R;
import com.clic.org.serve.Utils.ClicUtils;
import com.clic.org.serve.Utils.JsonUtils;
import com.clic.org.serve.constants.ClicConstants;
import com.clic.org.serve.data.Customer;
import com.clic.org.serve.data.OTP;
import com.clic.org.serve.data.OtpValidation;
import com.clic.org.serve.listener.ServiceListener;
import com.clic.org.serve.services.ServiceConstants;
import com.clic.org.serve.services.ServiceUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class SignupGuideActvity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout,layoutOR;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip,btnGuest,btnRegister,btnSignIn;
    private View register_layout;
    private EditText inputName, inputEmail, inputPassword,inputMobile;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword,inputLayoutMobile;
    private Button btnSignUp;
    private String SERVICE_TYPE;
    private boolean mobileValidity = false;
    Customer mCustomer ;
    OTP mOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mCustomer = new Customer();
        if(ClicUtils.readPreference(this,R.string.clic_username) != null)
        {
            finish();
            startActivity(new Intent(this, MainActivityClic.class));
        }
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnGuest = (Button) findViewById(R.id.btn_guest);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
        register_layout =(View)findViewById(R.id.layout_register);
        register_layout.setVisibility(View.GONE);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.help_slide_one,
                R.layout.help_slide_one,
                R.layout.help_slide_one,
                R.layout.help_slide_one};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        //changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        inputLayoutName = (TextInputLayout) register_layout.findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) register_layout.findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) register_layout.findViewById(R.id.input_layout_password);
        inputLayoutMobile = (TextInputLayout) register_layout.findViewById(R.id.input_layout_mobile);
        layoutOR = (LinearLayout) register_layout.findViewById(R.id.layout_OR);

        inputName = (EditText) register_layout.findViewById(R.id.input_name);
        inputEmail = (EditText) register_layout.findViewById(R.id.input_email);
        inputPassword = (EditText) register_layout.findViewById(R.id.input_password);
        inputMobile = (EditText) register_layout.findViewById(R.id.input_mobile);
        btnRegister = (Button) register_layout.findViewById(R.id.btn_register);
        btnSignIn = (Button) register_layout.findViewById(R.id.btn_signup);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        hideLayout();
        inputMobile.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_NEXT)
                {
                    SERVICE_TYPE = ClicConstants.MOIBILE_CHECK_SERVICE;
                    ServiceUtils.makeJSONObjectReq(SignupGuideActvity.this, ServiceConstants.MOBILE_VALIDATION+inputMobile.getText().toString(), myServiceListner, null);

                }
                return false;
            }
        });

    }

    private void hideLayout()
    {
        inputLayoutName.setVisibility(View.GONE);
        inputLayoutEmail.setVisibility(View.GONE);
        inputLayoutPassword.setVisibility(View.GONE);
        inputLayoutMobile.setVisibility(View.GONE);

    }
    private void showLayout()
    {
        inputLayoutName.setVisibility(View.VISIBLE);
        inputLayoutEmail.setVisibility(View.VISIBLE);
        inputLayoutPassword.setVisibility(View.VISIBLE);
        inputLayoutMobile.setVisibility(View.VISIBLE);
    }
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    public void onButtonClic(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_signup:
                viewPager.setVisibility(View.GONE);
                register_layout.setVisibility(View.VISIBLE);
                btnSkip.setVisibility(View.INVISIBLE);
                btnSignUp.setVisibility(View.GONE);
                showLayout();
                break;
            case R.id.btn_guest:
                startActivity(new Intent(SignupGuideActvity.this, MainActivityClic.class));
                break;
            case R.id.btn_register:
                submitForm();
                break;
            case R.id.btn_skip:
                viewPager.setVisibility(View.GONE);
                register_layout.setVisibility(View.VISIBLE);
                btnSkip.setVisibility(View.INVISIBLE);
                btnSignUp.setVisibility(View.GONE);
                showLayout();
                break;
            case R.id.btn_singIN:
                hideLayout();
                inputLayoutMobile.setVisibility(View.VISIBLE);
                inputLayoutPassword.setVisibility(View.VISIBLE);
                layoutOR.setVisibility(View.GONE);
                btnRegister.setVisibility(View.GONE);
                userLogin();
                break;

        }
    }
    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
            } else {
                // still pages are left
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    /**
     * Validating form
     */
    private void userLogin()
    {
        if (!validateMobileNumber()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
        mCustomer.setPassword(inputPassword.getText().toString());
        mCustomer.setPassword(inputMobile.getText().toString());

        SERVICE_TYPE = ClicConstants.CUSTOMER_LOGIN;

        ServiceUtils.postJsonObjectRequest(SignupGuideActvity.this,
                ServiceConstants.CUSTOMER_LOGIN,myServiceListner,JsonUtils.getJsonString(mCustomer));

    }
    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        if(!mobileValidity)
        {
            inputLayoutMobile.setError(getString(R.string.err_msg_mobile));
            return;
        }

        mCustomer.setCustomerName(inputName.getText().toString());
        mCustomer.setPassword(inputPassword.getText().toString());
        mCustomer.setEmailID(inputEmail.getText().toString());
        mCustomer.setPhoneNumber(inputMobile.getText().toString());
        SERVICE_TYPE = ClicConstants.CUSTOMER_SERVICE;

        ServiceUtils.postJsonObjectRequest(SignupGuideActvity.this,
                ServiceConstants.CREATE_CUSTOMER,myServiceListner,JsonUtils.getJsonString(mCustomer));

    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateMobileNumber() {
        if (inputMobile.getText().toString().length() <10) {
            inputLayoutMobile.setError(getString(R.string.err_msg_name));
            requestFocus(inputMobile);
            return false;
        } else {
            inputLayoutMobile.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }






    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
                case R.id.input_mobile:
                    SERVICE_TYPE = ClicConstants.MOIBILE_CHECK_SERVICE;
                    ServiceUtils.makeJSONObjectReq(SignupGuideActvity.this, ServiceConstants.MOBILE_VALIDATION+inputMobile.getText().toString(), myServiceListner, inputMobile.getText().toString());
                    break;

            }
        }
    }

    ServiceListener myServiceListner = new ServiceListener() {

        @Override
        public void onServiceResponse(String response) {

            mOtp  = new Gson().fromJson(response,OTP.class);
            if(SERVICE_TYPE.equalsIgnoreCase(ClicConstants.OTP_SERVICE))
            {

                ClicUtils.createPreferences(getApplicationContext(), inputMobile.getText().toString(), R.string.clic_username);
                ClicUtils.createPreferences(getApplicationContext(),inputPassword.getText().toString(), R.string.clic_password);
                ClicUtils.createPreferences(getApplicationContext(),mOtp.getCustomerID(),R.string.clic_ClientID);
                finish();
                startActivity(new Intent(SignupGuideActvity.this, MainActivityClic.class));

            }
            else if(SERVICE_TYPE.equalsIgnoreCase(ClicConstants.MOIBILE_CHECK_SERVICE))
            {
                mobileValidity = true;
                inputLayoutMobile.setErrorEnabled(false);

            }
            else if(SERVICE_TYPE.equalsIgnoreCase(ClicConstants.CUSTOMER_SERVICE))
            {
                OtpValidation otpValidation = new OtpValidation();
                otpValidation.setCustomerOTP(mOtp.getOtpNum());
                otpValidation.setCustomerID(mOtp.getCustomerID());
                SERVICE_TYPE = ClicConstants.OTP_SERVICE;
                ClicUtils.createPreferences(getApplicationContext(),mOtp.getCustomerID(),R.string.clic_ClientID);
                ClicUtils.createOTPValidationDialog(SignupGuideActvity.this,
                        R.layout.otp_validation,
                        JsonUtils.getJsonString(otpValidation),
                        myServiceListner);
            }
            else if(SERVICE_TYPE.equalsIgnoreCase(ClicConstants.CUSTOMER_LOGIN))
            {
                finish();
                startActivity(new Intent(SignupGuideActvity.this, MainActivityClic.class));

            }
        }

        @Override
        public void onServiceError(String response) {

            if(SERVICE_TYPE.equalsIgnoreCase(ClicConstants.OTP_SERVICE))
            {

            }
            else if(SERVICE_TYPE.equalsIgnoreCase(ClicConstants.MOIBILE_CHECK_SERVICE))
            {
                inputLayoutMobile.setError(getString(R.string.err_msg_mobile));
                requestFocus(inputMobile);
            }
            else if(SERVICE_TYPE.equalsIgnoreCase(ClicConstants.CUSTOMER_SERVICE))
            {

            }

        }
    };
}
