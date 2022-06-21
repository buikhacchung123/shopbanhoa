package com.example.testcontentprovider.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class FavoriteHistory {
    Context context;
    public static ArrayList<SanPham> favorHistory = new ArrayList<>();

    public FavoriteHistory(Context context){
        this.context = context;
        //favorHistory = new ArrayList<>();
    }

    public void addFavoriteHistorry(SanPham s) {
        if (favorHistory.size() == 0)
            this.favorHistory.add(0, s);
        else if (favorHistory.indexOf(s) > 0){

        }else
            this.favorHistory.add(s);
    }

    public ArrayList<SanPham> getFavorHistory() {
        if (this.favorHistory.size()!=0)
            return this.favorHistory;
        return new ArrayList<>();
    }

    public void removeFavoriteHistory(SanPham s){
        if (checkExist(s)) {
            for (int i = 0;i<favorHistory.size(); i++)
                if(s.getMaSp()==favorHistory.get(i).getMaSp())
                    favorHistory.remove(i);

        }
    }

    public boolean checkExist(SanPham s){
        if(favorHistory == null || favorHistory.size()==0)
            return false;
        for(int i = 0;i<favorHistory.size(); i++)
            if(s.getMaSp()==favorHistory.get(i).getMaSp())
                return true;
        return false;
    }
}
