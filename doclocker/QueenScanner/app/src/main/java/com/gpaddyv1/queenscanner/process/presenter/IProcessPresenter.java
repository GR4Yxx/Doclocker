package com.gpaddyv1.queenscanner.process.presenter;

import com.gpaddyv1.queenscanner.process.model.FilterModel;

import java.util.List;

public interface IProcessPresenter {
    void onItemClick(FilterModel adjuster);

    List<FilterModel> getListModel();

    String getFolderPath(String folderPath);

}
