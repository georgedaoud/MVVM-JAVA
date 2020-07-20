package com.georges.mvvm.room;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({Apis.ARTICLES, Apis.ARTICLE_DETAIL})
public @interface Apis {
    int ARTICLES = 1;
    int ARTICLE_DETAIL = 2;
}