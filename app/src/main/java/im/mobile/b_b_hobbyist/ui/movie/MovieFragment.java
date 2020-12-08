package im.mobile.b_b_hobbyist.ui.movie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import im.mobile.b_b_hobbyist.R;

public class MovieFragment extends Fragment {

    private View root;

    private EditText etDate;

    private Button btnSearch;
    private Button btnDate;

    private ListView lvBoxOffice;

    private BoxOfficeAdapter adapter;
    private ArrayList<BoxOffice> list;

    private String targetDt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movie, container, false);

        etDate = root.findViewById(R.id.edit_box_date);

        lvBoxOffice = root.findViewById(R.id.lv_boxoffice);

        list = new ArrayList<>();
        adapter = new BoxOfficeAdapter(getContext(), R.layout.listview_boxoffice, list);
        lvBoxOffice.setAdapter(adapter);

        lvBoxOffice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("movieCd", list.get(i).getMovieCd());
                Navigation.findNavController(root).navigate(R.id.action_to_moive_detail, bundle);
            }
        });

        btnSearch = root.findViewById(R.id.btn_search_movie);
        btnSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_to_moive_search);
            }
        });

        btnDate = root.findViewById(R.id.btn_boxDate);
        btnDate.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                parsingBoxOffice("");
            }
        });

        setDate();

        parsingBoxOffice("");

        return root;
    }

    public void setDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        targetDt = format.format(calendar.getTime());
        Log.d("dateeeeeeeeeee", targetDt);

        etDate.setText(targetDt);
    }

    public void parsingBoxOffice(String query) {

        new surAsyncTask().execute(getString(R.string.url_movie_boxoffice2));
       /* query = "key=" + getString(R.string.key_movie) + "&targetDt=" + targetDt;

        try {
            //쿼리값 붙이기
            new surAsyncTask().execute(getString(R.string.url_movie_boxoffice) + URLEncoder.encode(query, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    }

    class surAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog progressDlg;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {
            String address = strings[0];

            String result = downloadContents(address);
            if (result == null) return "Error!";
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            parse(result);      // 파싱 수행

            adapter.notifyDataSetChanged();
        }

        /* 주소(address)에 접속하여 문자열 데이터를 수신한 후 반환 */
        protected String downloadContents(String address) {
            HttpURLConnection conn = null;
            InputStream stream = null;
            String result = null;

            try {
                URL url = new URL(address);
                conn = (HttpURLConnection) url.openConnection();
                stream = getNetworkConnection(conn);
                result = readStreamToString(stream);
                if (stream != null) stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return result;
        }

        // URLConnection 을 전달받아 연결정보 설정 후 연결, 연결 후 수신한 InputStream 반환
        private InputStream getNetworkConnection(HttpURLConnection conn) throws Exception {
            // 클라이언트 아이디 및 시크릿 그리고 요청 URL 선언
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "application/json");

            if (conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + conn.getResponseCode());
            }

            return conn.getInputStream();
        }

        /* InputStream을 전달받아 문자열로 변환 후 반환 */
        protected String readStreamToString(InputStream stream) {
            StringBuilder result = new StringBuilder();

            try {
                InputStreamReader inputStreamReader = new InputStreamReader(stream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String readLine = bufferedReader.readLine();

                while (readLine != null) {
                    result.append(readLine + "\n");
                    readLine = bufferedReader.readLine();
                }

                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result.toString();
        }

        //json parsing
        public void parse(String json) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject boxObject = jsonObject.getJSONObject("boxOfficeResult");
                JSONArray array = boxObject.getJSONArray("dailyBoxOfficeList");
                JSONObject object;
                BoxOffice boxOffice;

                for (int i = 0; i < array.length(); i++) {
                    object = array.getJSONObject(i);
                    boxOffice = new BoxOffice();

                    boxOffice.setMovieCd(object.getString("movieCd"));
                    boxOffice.setRank(object.getString("rank"));
                    boxOffice.setMovieNm(object.getString("movieNm"));
                    if(object.getString("rankOldAndNew").equals("NEW")){
                        boxOffice.setRankOldAndNew(object.getString("rankOldAndNew"));
                    }
                    boxOffice.setAudiAcc(object.getString("audiAcc"));
                    boxOffice.setOpenDt(object.getString("openDt"));

                    list.add(boxOffice);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
