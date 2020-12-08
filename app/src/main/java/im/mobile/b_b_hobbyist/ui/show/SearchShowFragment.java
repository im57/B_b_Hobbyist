package im.mobile.b_b_hobbyist.ui.show;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import im.mobile.b_b_hobbyist.R;

public class SearchShowFragment extends Fragment {

    private View root;

    private Button btnMuseum;
    private Button btnShow;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_show, container, false);

        btnMuseum = root.findViewById(R.id.btn_museum);
        btnMuseum.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_to_show_museum);
            }
        });

        btnShow = root.findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_to_show_search);
            }
        });

        return root;
    }
}