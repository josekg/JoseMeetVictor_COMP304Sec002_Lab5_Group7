package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {

    private List<Patient> patients = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_item, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        Patient currentPatient = patients.get(position);
        holder.textViewName.setText(currentPatient.getName());
        holder.textViewAge.setText(String.valueOf(currentPatient.getAge()));
        holder.textViewDisease.setText(currentPatient.getDisease());
        holder.textViewBill.setText(String.valueOf(currentPatient.getBill()));

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients)
    {
        this.patients = patients;
        notifyDataSetChanged();
    }

    public Patient getPatientAt(int position)
    {
        return patients.get(position);
    }

    class PatientHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewName;
        private TextView textViewDisease;
        private TextView textViewAge;
        private TextView textViewBill;

        public PatientHolder (View itemView)
        {
            super(itemView);
            textViewName =  itemView.findViewById(R.id.patient_name);
            textViewDisease = itemView.findViewById(R.id.patient_dis);
            textViewAge = itemView.findViewById(R.id.patient_age);
            textViewBill = itemView.findViewById(R.id.patient_bill);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(patients.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Patient patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;

    }
}
