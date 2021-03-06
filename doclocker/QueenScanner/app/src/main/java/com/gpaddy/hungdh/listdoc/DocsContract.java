package com.gpaddy.hungdh.listdoc;

import com.gpaddyv1.queenscanner.document.DocumentModel;

import java.io.File;
import java.util.List;


public interface DocsContract {
    interface IDocsView {
        void onItemClick(File file);

        void onItemLongClick(File file);

    }

    interface IDocsPresenter {
        void onItemClick(File file);

        void onItemLongClick(File file);

        String bindLastModify(long time);

        String getPagePdf(File folder);

        int getNumberOfImage(File folder);

        String getImagePath(File folder);

        List<DocumentModel> getListDocs(String folder);
    }

    interface IDocsModel {
        List<String> getLstPathDoc();

    }
}
