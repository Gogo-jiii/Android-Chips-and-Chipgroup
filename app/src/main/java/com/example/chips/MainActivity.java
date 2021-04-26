package com.example.chips;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Chip defaultChip, chipEntry, chipChoice, chipAction, chipFilter,
            chip1EntryChip, chip2EntryChip, chip3EntryChip;

    ChipGroup chipGroupChoice, chipGroupEntry, chipGroupFilter;

    private ArrayList<String> multipleCheckedFilterChips = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultChip = findViewById(R.id.defaultChip);
        chipChoice = findViewById(R.id.chipChoice);
        chipAction = findViewById(R.id.chipAction);
        chipFilter = findViewById(R.id.chipFilter);
        chipEntry = findViewById(R.id.chipEntry);
        chip1EntryChip = findViewById(R.id.chip1EntryChip);
        chip2EntryChip = findViewById(R.id.chip2EntryChip);
        chip3EntryChip = findViewById(R.id.chip3EntryChip);

        chipGroupChoice = findViewById(R.id.chipGroupChoice);
        chipGroupFilter = findViewById(R.id.chipGroupFilter);
        chipGroupEntry = findViewById(R.id.chipGroupEntry);

        defaultChip.setOnClickListener(this);
        chipChoice.setOnClickListener(this);
        chipAction.setOnClickListener(this);
        chipFilter.setOnClickListener(this);
        chipEntry.setOnClickListener(this);
        chip1EntryChip.setOnClickListener(this);
        chip2EntryChip.setOnClickListener(this);
        chip3EntryChip.setOnClickListener(this);


        chip1EntryChip.setOnCloseIconClickListener(v -> {
            chipGroupEntry.removeView(v);
        });

        chip2EntryChip.setOnCloseIconClickListener(v -> {
            chipGroupEntry.removeView(v);
        });

        chip3EntryChip.setOnCloseIconClickListener(v -> {
            chipGroupEntry.removeView(v);
        });

        chipEntry.setOnCloseIconClickListener(v -> {
            Toast.makeText(MainActivity.this, "closed",
                    Toast.LENGTH_SHORT).show();
        });

        chipGroupChoice.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if (chip != null) {
                Toast.makeText(MainActivity.this, "Choice Chip: " + chip.getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        chipGroupEntry.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if (chip != null) {
                Toast.makeText(MainActivity.this, "Entry Chip: " + chip.getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        getMultipleCheckedFilterChips();
    }

    private void getMultipleCheckedFilterChips() {
        for (int i = 0; i < chipGroupFilter.getChildCount(); i++) {
            Chip chip = (Chip) chipGroupFilter.getChildAt(i);

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override public void onCheckedChanged(CompoundButton buttonView,
                                                       boolean isChecked) {
                    if (isChecked) {
                        multipleCheckedFilterChips.add(buttonView.getText().toString());
                    } else {
                        multipleCheckedFilterChips.remove(buttonView.getText().toString());
                    }

                    if (!multipleCheckedFilterChips.isEmpty()) {
                        Toast.makeText(MainActivity.this, multipleCheckedFilterChips.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.defaultChip:
                Toast.makeText(this, "clicked chip", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chipEntry:
                Toast.makeText(this, "clicked entry chip", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chipChoice:
                Toast.makeText(this, "clicked choice chip", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chipAction:
                Toast.makeText(this, "clicked action chip", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chipFilter:
                Toast.makeText(this, "clicked filter chip", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}