    package com.example.sz_bigtest5;

    import androidx.activity.result.ActivityResult;
    import androidx.activity.result.ActivityResultCallback;
    import androidx.activity.result.ActivityResultLauncher;
    import androidx.activity.result.contract.ActivityResultContracts;
    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.database.Cursor;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ListView;
    import android.widget.SearchView;
    import android.widget.SimpleCursorAdapter;

    public class MainActivity extends AppCompatActivity {
        PhoneDatabase db;
        SimpleCursorAdapter adapter;
        public static final String KEY_ID="key_id";
        public static final String KEY_IS_NEW_DATA = "key_is_new";
        ActivityResultLauncher<Intent> launcher;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.my_main);
            db = new PhoneDatabase(getApplicationContext());
            db.open();
            launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                updateListview();
                            }
                        }
                    });
            Cursor cursor = db.queryAll();
            ListView lv=findViewById(R.id.listView);
            String[] from=new String[]{PhoneDatabase.KEY_TITLE,PhoneDatabase.KEY_MODIFY_TIME};
            int[]to =new int[]{R.id.row_view_tv_title,R.id.row_view_tv_time};
            adapter=new SimpleCursorAdapter(this,R.layout.row_view,cursor,from,to);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                    Bundle b=new Bundle();
                    b.putLong(KEY_ID,id);
                    b.putBoolean(KEY_IS_NEW_DATA,false);
                    intent.putExtras(b);
                    launcher.launch(intent);
                }
            });
        }
        @Override
        protected void onDestroy(){
            super.onDestroy();
            db.close();
        }
        public void myReset(View view){
            db.reset();
            updateListview();
        }
        private void updateListview(){
            adapter.getCursor().requery();
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.opt_menu, menu);

            MenuItem item = menu.findItem(R.id.opt_search);
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    Cursor cursor = db.fuzzyQuery(newText);
                    adapter.swapCursor(cursor);
                    return false;
                }
            });
            return super.onCreateOptionsMenu(menu);
        }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item){
            switch (item.getItemId()){
                case R.id.opt_new:
                    Intent intent =new Intent(MainActivity.this,DetailActivity.class);
                    Bundle b=new Bundle();
                    b.putBoolean(KEY_IS_NEW_DATA,true);
                    intent.putExtras(b);
                    launcher.launch(intent);
                    break;
                case R.id.opt_reset:
                    myReset(null);
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
        public static  long getIntentIdData(Intent intent){
            Bundle b=intent.getExtras();
            long id=b.getLong(KEY_ID);
            return id;
        }
        public static  boolean getIntentIsNewData(Intent intent){
            Bundle b=intent.getExtras();
            boolean is_new=b.getBoolean(KEY_IS_NEW_DATA);
            return  is_new;
        }
    }

