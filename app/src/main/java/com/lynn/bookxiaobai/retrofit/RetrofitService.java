package com.lynn.bookxiaobai.retrofit;

import com.lynn.bookxiaobai.entity.BookBean;
import com.lynn.bookxiaobai.entity.BooksResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/2/7.
 */

public interface RetrofitService {

    /**
     * @param strIsbn
     * @return
     */
    @GET("book/isbn/{strIsbn}")
    Observable<BookBean> getBookByIsbn(@Path("strIsbn") String strIsbn);


    /**
     * https://api.douban.com/v2/book/1003078
     *
     * @param strId
     * @return
     */
    @GET("book/{strId}")
    Observable<BookBean> getBookById(@Path("strId") String strId);


    /**
     * https://api.douban.com/v2/book/search?q="小王子"
     *
     * @param name
     * @param tag
     * @param start
     * @param count
     * @return
     */
    @GET("book/search")
    Observable<BooksResult> getSearchBook(@Query("q") String name,
                                          @Query("tag") String tag,
                                          @Query("start") int start,
                                          @Query("count") int count);


}