package friendsforever.fyp.app.friendsforever.UserCallFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import friendsforever.fyp.app.friendsforever.R;

public class FragmentCallog extends Fragment {
    View v;

    public FragmentCallog() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.callog_fragment,container,false);
        return v;
    }
}
