package com.csoft.wing.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csoft.wing.R;
import com.csoft.wing.adapter.ContactAdapter;
import com.csoft.wing.common.Utils;

public class ContactsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contact_layout, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        RecyclerView contactList = (RecyclerView) v.findViewById(R.id.list);
        contactList.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactList.setAdapter(new ContactAdapter(Utils.getContacts(getActivity())));
    }
}
