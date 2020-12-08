package im.mobile.b_b_hobbyist.ui.book;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import im.mobile.b_b_hobbyist.R;

public class LibraryFragment extends Fragment {

    private View root;

    private String body;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_library, container, false);

        parsingLibrary("");

        return root;
    }

    public void parsingLibrary(String name) {

        new surAsyncTask().execute(getString(R.string.url_library));
        // query = "key=" + getString(R.string.key_movie);

  /*      try {
            //쿼리값 붙이기
            new SearchBookFragment.surAsyncTask().execute(getString(R.string.url_book) + URLEncoder.encode(name, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
*/
        name = "";
    }


    class surAsyncTask extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDlg;

        @Override
        protected void onPreExecute() {

            body="<gpsxfrom>129.101</gpsxfrom><gpsyfrom>35.142</gpsyfrom><gpsxto>129.101</gpsxto><gpsyto>35.142</gpsyto>";


        /*    JSONObject json = new JSONObject();

            try {
                json.put("gpsxfrom", 129.101);
                json.put("gpsyfrom", 35.142);
                json.put("gpsxto", 129.101);
                json.put("gpsyto", 35.142);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            body = json.toString();*/
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
            //parse(result);      // 파싱 수행
            Log.d("resultttttttttttttttttt", result);
            //adapter.notifyDataSetChanged();
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
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept", "application/xml");
            conn.setRequestProperty("content-type", "application/xml");

            writeStream(conn);

            if (conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + conn.getResponseCode());
            }

            return conn.getInputStream();
        }

        protected void writeStream(HttpURLConnection conn) {
            try {
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(body); //xml 형식의 메세지 전달
                wr.flush();
                wr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
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
    /*    public void parse(String xml) {
            String tag = "";

            Book book = null;

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("item")) {
                                book = new Book();
                            } else if (parser.getName().equals("title")) {
                                if (book != null) tag = "title";
                            } else if (parser.getName().equals("affiliation")) {
                                if (book != null) tag = "affiliation";
                            } else if (parser.getName().equals("rights")) {
                                if (book != null) tag = "rights";
                            } else if (parser.getName().equals("charge")) {
                                if (book != null) tag = "charge";
                            } else if (parser.getName().equals("description")) {
                                if (book != null) tag = "description";
                            } else if (parser.getName().equals("issueddate")) {
                                if (book != null) tag = "issueddate";
                            } else if (parser.getName().equals("extent")) {
                                if (book != null) tag = "extent";
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item")) {
                                list.add(book);
                            }
                            break;
                        case XmlPullParser.TEXT:
                            switch (tag) {
                                case "title":
                                    book.setTitle(parser.getText());
                                    break;
                                case "affiliation":
                                    book.setAffiliation(parser.getText());
                                    break;
                                case "rights":
                                    book.setRights(parser.getText());
                                    break;
                                case "charge":
                                    book.setCharge(parser.getText());
                                    break;
                                case "description":
                                    book.setDescription(parser.getText());
                                    break;
                                case "issueddate":
                                    book.setIssueddate(parser.getText());
                                    break;
                                case "extent":
                                    book.setExtent(parser.getText());
                                    break;
                            }
                            tag = "";
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
        }*/

    }
}