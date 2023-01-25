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

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.TestListHolder>{

    private final RecyclerViewInterface testrecyclerViewInterface;
    //creating variabel for array list and context.
    private ArrayList<PatientClass> patientList;
    private final Context mContext;
    public TestListAdapter(Context context, ArrayList<PatientClass> patientList,
                       RecyclerViewInterface testrecyclerViewInterface) {
        this.patientList = patientList;
        this.mContext = context;
        this.testrecyclerViewInterface = testrecyclerViewInterface;
    }

    @NonNull
    @Override
    public TestListAdapter.TestListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_patient, parent, false);
        return new TestListAdapter.TestListHolder(view ,testrecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TestListAdapter.TestListHolder holder, int position) {
        //Settings data to our views of recycler view
        holder.patientClass = patientList.get(holder.getAdapterPosition());
        String name = holder.patientClass.getNachname()+" , "+holder.patientClass.getVorname();
        String zimmer = "Zimmer Nummer:" + holder.patientClass.getZimmerNum();
        holder.patientName.setText(name);
        holder.patientZimmer.setText(zimmer);
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }


    protected class TestListHolder extends  RecyclerView.ViewHolder{
        //creating variable for our views
        private final TextView patientName, patientZimmer;
        private TextView patientStatus;
        private PatientClass patientClass;
        @SuppressLint("RestrictedApi")
        public TestListHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            //initialize textview with their ids
            patientName = itemView.findViewById(R.id.textViewNameTest);
            patientZimmer = itemView.findViewById(R.id.textViewZimmerTest);

            if(ContainerAndGlobal.isDarkmode()){
                patientName.setTextColor(R.color.black);
                patientZimmer.setTextColor(R.color.black);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(TestListAdapter.this.testrecyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            TestListAdapter.this.testrecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }

    }
}
