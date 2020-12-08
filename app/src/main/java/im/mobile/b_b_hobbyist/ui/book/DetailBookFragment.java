package im.mobile.b_b_hobbyist.ui.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import im.mobile.b_b_hobbyist.R;

public class DetailBookFragment extends Fragment {

    private View root;

    private TextView tvTitle;
    private TextView tvAffiliation;
    private TextView tvCharge;
    private TextView tvDescription;
    private TextView tvExtent;
    private TextView tvIssuedDate;
    private TextView tvRights;

    private Book book;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_book_detail, container, false);

        tvTitle = root.findViewById(R.id.text_book_title);
        tvAffiliation = root.findViewById(R.id.text_book_affiliation);
        tvCharge = root.findViewById(R.id.text_book_charge);
        tvDescription = root.findViewById(R.id.text_book_description);
        tvExtent = root.findViewById(R.id.text_book_extent);
        tvIssuedDate = root.findViewById(R.id.text_book_issuedDate);
        tvRights = root.findViewById(R.id.text_book_rights);

        book = (Book) getArguments().getSerializable("book");

        tvTitle.setText(book.getTitle());
        tvAffiliation.setText(book.getAffiliation());
        tvCharge.setText(book.getCharge());
        tvDescription.setText(book.getDescription());
        tvExtent.setText(book.getExtent());
        tvIssuedDate.setText(book.getIssueddate());
        tvRights.setText(book.getRights());

        return root;
    }
}