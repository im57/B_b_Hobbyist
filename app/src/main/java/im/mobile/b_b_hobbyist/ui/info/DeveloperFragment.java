package im.mobile.b_b_hobbyist.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import im.mobile.b_b_hobbyist.R;

public class DeveloperFragment extends Fragment {

    private View root;

    private TextView tvName;
    private TextView tvEmail;

    private Button btnGreet;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_developer, container, false);

        tvName = root.findViewById(R.id.text_developer_name);
        tvEmail = root.findViewById(R.id.text_developer_email);

        tvName.setText(R.string.dev_name);
        tvEmail.setText(R.string.dev_email);

        btnGreet = root.findViewById(R.id.btn_greet);
        btnGreet.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "안녕하세요 dev.CapIM 입니다", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}