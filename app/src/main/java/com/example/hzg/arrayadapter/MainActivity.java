package com.example.hzg.arrayadapter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{



    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> dataList;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);
//        String[] data = new String[] { "java", "C++", "JavaScript", "Php",
//                "Python", "C++", "JavaScript", "Php",
//                "Python", "C++", "JavaScript", "Php",
//                "Python", "C++", "JavaScript", "Php",
//                "Python", "C++", "JavaScript", "Php",
//                "Python", "C++", "JavaScript", "Php",
//                "Python", "C++", "JavaScript", "Php",
//                "Python", "C++", "JavaScript", "Php",
//                "Python" };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                MainActivity.this, android.R.layout.simple_list_item_1, data);
        // 给ListView设置数据适配器
//        listView.setAdapter(adapter);

//        String[] arrayData={"huang阿贵","huang阿贵","huang阿贵","huang阿贵","huang阿贵","huang阿贵","huang阿贵","huang阿贵","huang阿贵"};
        //ArrayAdapter(上下文，当前ListView加载的每一个列表项所对应的布局文件，数据源)
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayData);
//        listView.setAdapter(arrayAdapter);

        //simpleAdapter
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        simpleAdapter = new SimpleAdapter(this, dataList, R.layout.item, new String[]{"image", "text"}, new int[]{R.id.imageView, R.id.textView});
        listView.setAdapter(simpleAdapter);

        // 设置ListView的元素被选中时的事件处理监听器
        listView.setOnItemClickListener(this);
        // 设置ListView的元素被滚动时的事件处理监听器
        listView.setOnScrollListener(this);
    }

    public List<Map<String, Object>> getData() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", R.drawable.ic_launcher);
            map.put("text", "huangzonggui" + i);
            dataList.add(map);
        }
        return dataList;
    }


//    事件处理监听器方法
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 获取点击ListView item中的内容信息
        String text = listView.getItemAtPosition(position) + "";
        // 弹出Toast信息显示点击位置和内容
        Toast.makeText(MainActivity.this, "position=" + position + "content=" + text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 手指离开屏幕前，用力滑了一下
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                Toast.makeText(MainActivity.this, "用力滑一下",Toast.LENGTH_LONG).show();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("text", "滚动添加 "+i++);
                map.put("image", R.drawable.ic_launcher);
                dataList.add(map);
                listView.setAdapter(simpleAdapter);
                simpleAdapter.notifyDataSetChanged();
                Log.i("Main","用力滑了一下");
                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main", "视图停止");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Main", "正在滑动");
                break;
        }


    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}

