package im.mobile.b_b_hobbyist.ui.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import im.mobile.b_b_hobbyist.R;

public class BookFragment extends Fragment {

    private View root;

    private Button btnLibrary;
    private Button btnBook;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_book, container, false);

        btnLibrary = root.findViewById(R.id.btn_library);
        btnLibrary.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_to_book_library);
            }
        });

        btnBook = root.findViewById(R.id.btn_book);
        btnBook.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_to_book_search);
            }
        });

        return root;
    }
}