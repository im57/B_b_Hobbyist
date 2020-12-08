package im.mobile.b_b_hobbyist.ui.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import im.mobile.b_b_hobbyist.R;

public class MovieAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private int layout;
    private ArrayList<Movie> list;
    private ArrayList<String> directors;
    private String str;

    public MovieAdapter(Context context, int layout, ArrayList<Movie> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        this.directors = new ArrayList<>();
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Movie getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).get_id();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder viewHolder = null;

        if (view == null) {
            view = inflater.inflate(layout, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = view.findViewById(R.id.text_movieNm);
            viewHolder.tvDate = view.findViewById(R.id.text_openDt);
            viewHolder.tvDirectors = view.findViewById(R.id.text_directors);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        Movie movie = list.get(i);

        viewHolder.tvName.setText(movie.getMovieNm());
        viewHolder.tvDate.setText(movie.getOpenDt());

        for(int j = 0; j < movie.getDirectors().size(); j++){
            if(j == 0){
                str = movie.getDirectors().get(j).getPeopleNm();
            }
            else{
                str += movie.getDirectors().get(j).getPeopleNm();
            }

            if(j != movie.getDirectors().size()-1){
                str += ", ";
            }
        }
        directors.add(i, str);
        viewHolder.tvDirectors.setText(directors.get(i));

        return view;
    }

    public void setList(ArrayList<Movie> list) {
        this.list = list;
    }

    static class ViewHolder {
        public TextView tvName = null;
        public TextView tvDate = null;
        public TextView tvDirectors = null;
    }

}
