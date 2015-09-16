package com.happy.samuelalva.gankmeizhi.support.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.happy.samuelalva.gankmeizhi.model.Results;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by SamuelGjk on 2015/9/8.
 */
public class GankMeizhiApi {
    private static GankMeizhiApi instance;
    final static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

    public static final int LOAD_SIZE = 10;

    private final GankMeizhiService mService;

    public static GankMeizhiApi getInstance() {
        if (null == instance) {
            synchronized (GankMeizhiApi.class) {
                if (null == instance) {
                    instance = new GankMeizhiApi();
                }
            }
        }
        return instance;
    }

    private GankMeizhiApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://gank.avosapps.com/api")
                .setConverter(new GsonConverter(gson))
                .build();
        mService = restAdapter.create(GankMeizhiService.class);
    }

    public interface GankMeizhiService {
        @GET("/data/Android/" + LOAD_SIZE + "/{page}")
        Observable<Results> getAndroidData(@Path("page") int page);

        @GET("/data/iOS/" + LOAD_SIZE + "/{page}")
        Observable<Results> getIosData(@Path("page") int page);

        @GET("/data/福利/" + LOAD_SIZE + "/{page}")
        Observable<Results> get福利Data(@Path("page") int page);

        @GET("/random/data/福利/" + LOAD_SIZE)
        Observable<Results> get随机福利();
    }

    public Observable<Results> getAndroidData(int page) {
        return mService.getAndroidData(page);
    }

    public Observable<Results> getIosData(int page) {
        return mService.getIosData(page);
    }

    public Observable<Results> get福利Data(int page) {
        return mService.get福利Data(page);
    }

    public Observable<Results> get随机福利() {
        return mService.get随机福利();
    }
}
