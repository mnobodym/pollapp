package com.example.poolapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Second_page_Adapter extends RecyclerView.Adapter<Second_page_Adapter.ViewHolder> {

    private static final String TAG = "Second_page_Adapter";
    private static int[] cntOfInput;

    Context context;
    ArrayList<String> inputs;
    SharedPreferences sp;

    public Second_page_Adapter(Context cntX, ArrayList<String> list) {
        context = cntX;
        inputs = list;
        cntOfInput = new int[10];
        for (int i=0; i<10; i++){
            cntOfInput[i] = 0;
        }
        sp = context.getSharedPreferences("saveData", Context.MODE_PRIVATE);
    }

    @Override
    public int getItemCount() {
        return inputs.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_show_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.buttonOfField.setText(inputs.get(position));

        int percent = sp.getInt(inputs.get(position), -1);
        holder.percentOfInput.setText(percent + " %");

        int ps = position;
        holder.buttonOfField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cntOfInput[ps]++;
                holder.countOfInput.setText(String.valueOf(cntOfInput[ps]));
                updatePercent();
                notifyDataSetChanged();
            }
        });

    }

    private void updatePercent() {

        int sumOfCounts = getSumOfCounts();

        SharedPreferences.Editor editor = sp.edit();
        BigDecimal percent;
        BigDecimal sumOfCountsBD = new BigDecimal(sumOfCounts);

        BigDecimal tmp;
        for (int i = 0; i< inputs.size(); i++) {
            tmp = new BigDecimal(cntOfInput[i]);
            percent = tmp.divide(sumOfCountsBD, 4, RoundingMode.CEILING);

            int res = (int) (percent.doubleValue() * 100);
            editor.putInt(inputs.get(i), res);
            editor.apply();
        }
    }

    private int getSumOfCounts() {
        int sumOfCounts = 0;
        for (int i = 0; i< inputs.size(); i++) {
            sumOfCounts += cntOfInput[i];
        }
        return sumOfCounts;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button buttonOfField;
        TextView percentOfInput, countOfInput;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            buttonOfField = itemView.findViewById(R.id.itemBtn);
            countOfInput = itemView.findViewById(R.id.viewCount);
            percentOfInput = itemView.findViewById(R.id.viewPercent);

            countOfInput.setText("0");
            percentOfInput.setText("0 %");
        }
    }
}