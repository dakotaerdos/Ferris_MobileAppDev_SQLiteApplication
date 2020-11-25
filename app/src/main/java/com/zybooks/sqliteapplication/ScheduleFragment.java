package com.zybooks.sqliteapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        recyclerView = view.findViewById(R.id.schedule_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Send bands to recycler view
        adapter = new ScheduleAdapter(ScheduleDatabase.getInstance(getContext()).selectAllClasses());
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    private class ScheduleHolder extends RecyclerView.ViewHolder {

        Class _class;

        private TextView classId;
        private TextView className;
        private TextView classDescription;
        private TextView classInstructor;
        private TextView classLocation;
        private TextView classDate;
        private TextView classTime;

        public ScheduleHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_schedule, parent, false));
            classId = itemView.findViewById(R.id.scheduleIdData);
            className = itemView.findViewById(R.id.scheduleNameData);
            classDescription = itemView.findViewById(R.id.scheduleDescriptionData);
            classInstructor = itemView.findViewById(R.id.scheduleInstructorData);
            classLocation = itemView.findViewById(R.id.scheduleLocationData);
            classDate = itemView.findViewById(R.id.scheduleDateData);
            classTime = itemView.findViewById(R.id.scheduleTimeData);
        }

        public void bind(Class _class) {
            this._class = _class;
            classId.setText(String.valueOf(this._class.getId()));
            className.setText(String.valueOf(this._class.getName()));
            classDescription.setText(this._class.getDescription());
            classInstructor.setText(this._class.getInstructor());
            classLocation.setText(this._class.getLocation());
            classDate.setText(this._class.getDate());
            classTime.setText(this._class.getTime());
        }

    }

    private class ScheduleAdapter extends RecyclerView.Adapter<ScheduleHolder> {

        private ArrayList<Class> classes;

        public ScheduleAdapter(ArrayList<Class> classes) {
            this.classes = classes;
        }

        @Override
        public ScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ScheduleHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ScheduleHolder holder, int position) {
            Class _class = classes.get(position);
            holder.bind(_class);
        }

        @Override
        public int getItemCount() {
            return classes.size();
        }

        public void updateClass(ArrayList<Class> newClasses) {
            classes = newClasses;
            adapter.notifyDataSetChanged();
        }


    }
}