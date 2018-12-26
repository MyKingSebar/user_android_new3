package com.yijia.common_yijia.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte.ui.recycler.DataConverter;
import com.example.latte.ui.recycler.ItemType;
import com.example.latte.ui.recycler.MultipleFields;
import com.example.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

public class YjIndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONObject dataObject = JSON.parseObject(getJsonData()).getJSONObject("data");
        final JSONArray cricleList = dataObject.getJSONArray("cricleList");
        final int size = cricleList.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = cricleList.getJSONObject(i);

            final Long userId = data.getLong("userId");
            final String userNickname = data.getString("userNickname");
            final String userRealName = data.getString("userRealName");
            final String userHead = data.getString("userHead");
            final int isOwn = data.getInteger("isOwn");
            final int circleId = data.getInteger("circleId");
            final int contentType = data.getInteger("contentType");
            final String content = data.getString("content");
            final String url = data.getString("url");
            final String location = data.getString("location");
            final String longitude = data.getString("longitude");
            final String latitude = data.getString("latitude");
            final String createdTime = data.getString("createdTime");
            final JSONArray commentList = data.getJSONArray("commentList");
            final String[] imgs = url.split(",");


            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            switch (contentType) {
                //1-文本，2-照片，3-语音，4-视频
                case 1:
                    type=ItemType.TEXT;
                    break;
                case 2:
                    if(imgs.length>1){
                        type=ItemType.IMAGES;
                    }else {
                        type=ItemType.IMAGE;
                    }
                    break;
                case 3:
                    type=ItemType.RIDEO;
                    break;
                case 4:
                    type=ItemType.VIDEO;
                    break;
                default:
                    break;

            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.ID, userId)
                    .setField(MultipleFields.TEXT, content)
                    .setField(MultipleFields.IMAGE_URL, userHead)
                    .build();

            ENTITIES.add(entity);


        }


        return null;
    }

}
