package com.performancetweaker.app.ui.fragments;

import android.os.Bundle;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.performancetweaker.app.R;
import com.performancetweaker.app.utils.GpuUtils;

import java.util.ArrayList;

public class SettingsFragment extends PreferenceFragment {

    MultiSelectListPreference mMultiSelectListPreference;
    GpuUtils gpuUtils;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pref_container, container, false);
    }

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        addPreferencesFromResource(R.xml.settings_preferences);

        gpuUtils = GpuUtils.getInstance();

        mMultiSelectListPreference = (MultiSelectListPreference) findPreference("select_apply_on_boot");
        ArrayList<CharSequence> entries = new ArrayList<>();
        entries.add(getString(R.string.cpu_frequency));
        if (gpuUtils.isGpuFrequencyScalingSupported()) {
            entries.add(getString(R.string.gpu_frequency));
        }
        entries.add(getString(R.string.io));
        entries.add(getString(R.string.build_prop));
        entries.add(getString(R.string.vm));

        CharSequence[] charSequences = new CharSequence[entries.size()];
        for (int i = 0; i < entries.size(); i++) {
            charSequences[i] = entries.get(i);
        }
        mMultiSelectListPreference.setEntries(charSequences);
        mMultiSelectListPreference.setEntryValues(charSequences);
    }

}
