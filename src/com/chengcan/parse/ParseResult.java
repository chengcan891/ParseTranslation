package com.chengcan.parse;

import com.chengcan.entity.StringResource;

public interface ParseResult {
    /**
     * 正确匹配
     * @param original 初始资源，被匹配的资源
     * @param aim 匹配的资源
     */
    void onMatched(StringResource original, StringResource aim);

    /**
     * 找到了相关资源，但是匹配错误
     * @param original 初始资源，被匹配的资源
     * @param aim 匹配的资源
     */
    void onNotMatched(StringResource original,StringResource aim);

    /**
     * 没有找到相关资源
     * @param original 初始资源，被匹配的资源
     */
    void onNotFind(StringResource original);
}
