package org.yndongyong.common.retrofit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


/**
 * 
 * Created by Dong on 2015/11/11.
 */
public class RetrofitUtils {
    private static Retrofit singleton;

    
    /**
     * 创建一个服务适配器(service for adapter)
     * @param clazz
     * @param baseUrl
     * @param <T>
     * @return
     */
    public static <T> T createApi(Class<T> clazz,String baseUrl) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                   
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(baseUrl);//设置服务地址  
                    //添加将结果转换为observable的能力，即使用rxjava回调适配器
                    builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
                    //使用gson进行数据装换
                    builder.addConverterFactory(GsonConverterFactory.create());
                    //结合okhttp作为底层的请求，同一家公司开发的,应该不用添加下面这句，retrofit会自动检测
//                    okhttp的jar，然后反射自动注入的。
//                    builder.client(OkHttpClientManager.getInstance().getOkHttpClient());
                    //2.0的日志还没有开发好
//                    builder.setLogLevel(
//                            DEBUG ? Retrofit.LogLevel.BASIC : Retrofit.LogLevel.NONE);
                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }

    /**
     * 可以在基类中使用如此使用下面的方法
     * 创建API实例 
     * @param cls Api定义类的类型 
     * @param <T> 范型 
     * @return API实例
     */
    /*public  <T> T  createApi(Class<T> cls) {
        return RetrofitUtils.createApi(this, cls);
    }*/

   /* Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();
             .addConverterFactory(GsonConverterFactory.create(gson)) 2.0需要自己添加*/

}
