package org.wbing.app_refresh;

import android.graphics.Color;
import android.os.AsyncTask;

import org.wbing.app_refresh.data.AdData;
import org.wbing.app_refresh.data.ContentData;
import org.wbing.app_refresh.data.NewsData;
import org.wbing.base.ui.WCallback;
import org.wbing.view.list.IMultiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbing
 * @date 2018/8/24
 */
public abstract class HttpTask<Data extends IMultiItem> extends AsyncTask<Integer, Void, List<Data>> {

    WCallback<List<Data>> callback;

    public HttpTask(WCallback<List<Data>> callback) {
        this.callback = callback;
    }

    @Override
    protected List<Data> doInBackground(Integer... integers) {
        int page = integers[0] - 1;
        try {
            Thread.sleep((long) (Math.random() * 3000));
            List<Data> ls = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                ls.add(createData(page * 10 + i));
            }
            return ls;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Data> list) {
        super.onPostExecute(list);
        callback.onSuccess(list);
        callback.onComplete();
    }

    public abstract Data createData(int i);

//    public Data createData(int i) {
//        int random = (int) Math.round(Math.random() * 3);
//        switch (random) {
//            case 1:
//                return new AdData(getRandomColor(), "广告：" + i);
//            case 2:
//                return new NewsData(getRandomColor(), "新闻：" + i);
//            default:
//                return new ContentData(getRandomColor(), "内容：" + i);
//        }
//    }

    private int[] colors = {
            Color.BLACK,
            Color.DKGRAY,
            Color.GRAY,
            Color.LTGRAY,
            Color.WHITE,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.CYAN,
            Color.MAGENTA
    };

    public int getRandomColor() {
        int random = (int) (Math.random() * 9);
        return colors[random];
    }
}
