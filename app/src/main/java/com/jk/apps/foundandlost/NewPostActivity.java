package com.jk.apps.foundandlost;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jk.apps.foundandlost.databinding.ActivityNewPostBinding;
import com.jk.apps.foundandlost.utils.Constant;
import com.jk.apps.foundandlost.utils.DbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewPostActivity extends AppCompatActivity {

    ActivityNewPostBinding binding;
    DbHelper dbHelper;
    long adTime = 0;
    Calendar calendar;
    int adType = Constant.LOST_POST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new DbHelper(this);
        calendar = Calendar.getInstance();
        onClick();
    }

    public void onClick() {
        binding.btnSave.setOnClickListener(v -> {
            saveData();
        });

        binding.edDate.setOnClickListener(v -> {
            calendar.setTimeInMillis(System.currentTimeMillis());
            @SuppressLint("SimpleDateFormat") DatePickerDialog dialog = new DatePickerDialog(this, (DatePickerDialog.OnDateSetListener) (view, year, month, dayOfMonth) -> {
                calendar.set(year, month, dayOfMonth);
                adTime = calendar.getTimeInMillis();
                binding.edDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(adTime));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            dialog.show();
        });
    }

    public void saveData() {
        if (binding.edName.getText().length() <= 0) {
            binding.edName.setError("Please Enter Name");
        } else if (binding.edPhone.getText().length() <= 0) {
            binding.edPhone.setError("Please Enter Phone");
        } else if (binding.edInfo.getText().length() <= 0) {
            binding.edInfo.setError("Please Enter Description");
        } else if (binding.edDate.getText().length() <= 0) {
            Constant.showToast(this, "Please Enter Date");
        } else if (binding.edLocation.getText().length() <= 0) {
            binding.edInfo.setError("Please Enter Location");
        } else {
            adType = binding.rdLost.isChecked() ? Constant.LOST_POST : Constant.FOUND_POST;
            dbHelper.addNewAdvert(binding.edName.getText().toString(), binding.edPhone.getText().toString(), binding.edInfo.getText().toString(), adTime, binding.edLocation.getText().toString(), adType);
            Constant.showToast(this, "Post Added Successfully");
            finish();
        }
    }
}