package friendsforever.fyp.app.friendsforever.UserCallFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import friendsforever.fyp.app.friendsforever.R;

public class FragmentCall extends Fragment {
    private static final int REQUEST_CALL=1;
    private EditText mEditTextNumber;
    View v;

    public FragmentCall() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.call_fragment,container,false);
        return v;
    }
}
