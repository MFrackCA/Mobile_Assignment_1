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
        double principal = mc.getPrincipal();
        double amortization = mc.getAmortization();

        // Format output of strings
        String interestString = String.format("%.2f%%", interest);
        String monthlyPaymentString = String.format("$%.2f", monthlyPayment);
        String amortizationString = String.valueOf((int) amortization) + " Years";
        String principalString = String.valueOf((int) principal);

        //Set view texts
        binding.v2interest.setText(interestString);
        binding.v2monthly.setText(monthlyPaymentString);
        binding.v2principal.setText(amortizationString);
        binding.v2amortization.setText(principalString);

        //Intent call Phone Number
        binding.intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("18005554455");
            }
        });
        //Intent Save Data for mortage payment
        binding.intent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(principalString, interestString, monthlyPaymentString, amortizationString);
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
    // Save data taht user entered plus calculation for mortgage by using Intent
    public void saveData(String principal,
                         String interest,
                         String monthlypayment,
                         String amortization ){

        Intent dataIntent = new Intent(Intent.ACTION_SEND);
        dataIntent.setType("text/plain");
        dataIntent.putExtra(Intent.EXTRA_TEXT,
                "Monthly Payment: " + monthlypayment +
                        "\nPrincipal: " + principal +
                        "\nInterest Rate: "+ interest +
                        "\nAmortization: "+ amortization);
        startActivity(Intent.createChooser(dataIntent, "Mortgage Results"));
    }

}