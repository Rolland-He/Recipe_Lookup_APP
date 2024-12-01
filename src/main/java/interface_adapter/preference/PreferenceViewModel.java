package interface_adapter.preference;

import interface_adapter.ViewModel;

/**
 * PreferenceViewModel.
 */
public class PreferenceViewModel extends ViewModel<PreferenceState> {
    public PreferenceViewModel() {
        super("preference");
        setState(new PreferenceState());
    }
}
