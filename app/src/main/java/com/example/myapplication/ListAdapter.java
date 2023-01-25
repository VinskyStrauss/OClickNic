package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    //creating variabel for array list and context.
    private ArrayList<PatientClass> patientList;
    private final Context mContext;
    public ListAdapter(Context context, ArrayList<PatientClass> patientList,
                       RecyclerViewInterface recyclerViewInterface) {
        this.patientList = patientList;
        this.mContext = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    // method for filtering our recyclerview items.
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<PatientClass> filteredList) {
        // below line is to add our filtered
        // list in our course array list.
        patientList = filteredList;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListAdapter.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false);
        return new ListHolder(view ,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListHolder holder, int position) {
        //Settings data to our views of recycler view
        holder.patientClass = patientList.get(holder.getAdapterPosition());
        String name = holder.patientClass.getNachname()+" , "+holder.patientClass.getVorname();
        String zimmer = "Zimmer Nummer:" + holder.patientClass.getZimmerNum();
        String status = holder.patientClass.getStatus();
        holder.patientName.setText(name);
        holder.patientZimmer.setText(zimmer);
        holder.patientStatus.setText(status);

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }


    protected class ListHolder extends  RecyclerView.ViewHolder{
        //creating variable for our views
        private final TextView patientName, patientZimmer;
        private TextView patientStatus;
        private PatientClass patientClass;
        @SuppressLint("RestrictedApi")
        public ListHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            //initialize textview with their ids
            patientName = itemView.findViewById(R.id.textViewName);
            patientZimmer = itemView.findViewById(R.id.textViewZimmer);
            patientStatus =  itemView.findViewById(R.id.textViewStatus);

            if(ContainerAndGlobal.isDarkmode()){
                patientName.setTextColor(R.color.black);
                patientZimmer.setTextColor(R.color.black);
                patientStatus.setTextColor(R.color.black);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ListAdapter.this.recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        ContainerAndGlobal.setPatientSearch(patientClass);
                        if(pos != RecyclerView.NO_POSITION){
                            ListAdapter.this.recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }

    }



}
