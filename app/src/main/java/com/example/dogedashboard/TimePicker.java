package com.example.dogedashboard;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calender = Calendar.getInstance();
        int hour = calender.get(Calendar.HOUR_OF_DAY);
        int minute = calender.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
    public void onTimeSet(android.widget.TimePicker view, int hourD, int minuteD) {
        TextView textView = getActivity().findViewById(R.id.time);
        textView.setText("Hour: " + hourD + " Minute: " + minuteD);
        Intent i = getActivity().getIntent();
        i.putExtra("hourD", hourD);
        i.putExtra("minuteD", minuteD);
    }
}
