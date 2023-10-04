package com.example.assignment_1_mfrackowiak_100401611;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment_1_mfrackowiak_100401611.databinding.FragmentMonthlypaymentBinding;

public class monthlypayment extends Fragment {

    private FragmentMonthlypaymentBinding binding;
    private Object mortgage;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMonthlypaymentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get user input from view 1
        Bundle data = getArguments();
        MortgageCalculator mc = (MortgageCalculator) data.getSerializable("User_input");

        // Unpack bundle values
        double interest = mc.getInterest();
        double monthlyPayment = mc.monthlyPayment();

        // Set two decimal places for Payment and Interest
        String interestString = String.format("%.2f%%", interest);
        String monthlyPaymentString = String.format("$%.2f", monthlyPayment);

        //Set view texts
        binding.v2interest.setText(interestString);
        binding.v2monthly.setText(monthlyPaymentString);
        binding.v2principal.setText(String.valueOf((int) mc.getPrincipal()));
        binding.v2amortization.setText(String.valueOf((int) mc.getAmortization()) + " Years");

        //Intent call Phone Number
        binding.intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("18005554455");
            }
        });
        //Recalculate Button Navigate to Home Fragment
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(monthlypayment.this)
                        .navigate(R.id.action_monthlypayment_to_home);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    // Intent for dialing phone number function
    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);

    }

}