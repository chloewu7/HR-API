package app;

import interface_adapter.ViewManagerModel;
import search.data_access.SearchDataAccessObject;
import search.entity.NewResponseFactory;
import search.entity.ResponseFactory;
import search.service.SearchDataAccessInterface;
import search.service.SearchInteractor;
import search.service.interface_adapter.SearchPresenter;
import search.service.interface_adapter.SearchState;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsDataAccessInterface;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInteractor;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsOutputBoundary;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsController;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsPresenter;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchViewModel;
import search.service.SearchDataAccessInterface;
import search.service.SearchOutputBoundary;
import view.SearchView;
import view.ShowMyReviewsView;
import view.UserCenter.UserCenterView;
import view.UserCenter.UserCenterViewModel;
import view.ViewManager;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCenterFactory {
    private UserCenterFactory(){}

    public static List<JPanel> create(ViewManagerModel viewManagerModel, UserCenterViewModel userCenterViewModel,
                                      ShowMyReviewsViewModel showMyReviewsViewModel, FileReviewDataAccessObject reviewDataAccessObject,
                                      SearchViewModel searchViewModel, SearchDataAccessObject searchDataAccessObject) {
        try {
            //TODO:创建showMyHistoryController和showMyCollectionController
            ShowMyReviewsController showMyReviewsController = createShowMyReviewUseCase(viewManagerModel, showMyReviewsViewModel, reviewDataAccessObject);

            SearchController searchController = createSearchUseCase(viewManagerModel,searchViewModel, searchDataAccessObject);

            //TODO：用刚才新建的的Controller和ViewModel创建一个新的userCenterView（加在参数里）
            List<JPanel> userManageViewList = new ArrayList<>();
            UserCenterView userCenterView =  new UserCenterView(viewManagerModel, userCenterViewModel, showMyReviewsController, showMyReviewsViewModel,
                    searchController, searchViewModel);
            userManageViewList.add(userCenterView);
            ShowMyReviewsView showMyReviewsView = new ShowMyReviewsView(viewManagerModel, showMyReviewsController, showMyReviewsViewModel);
            userManageViewList.add(showMyReviewsView);
            //TODO：新建showMyHistoryView和showMyCollectionView
            //TODO：把新建的View加到 userManageViewList
            return userManageViewList;
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static ShowMyReviewsController createShowMyReviewUseCase(ViewManagerModel viewManagerModel,
                                                                   ShowMyReviewsViewModel showMyReviewsViewModel,
                                                                   ShowMyReviewsDataAccessInterface reviewsDataAccessObject) throws IOException {
        ShowMyReviewsOutputBoundary showMyReviewsPresenter = new ShowMyReviewsPresenter(viewManagerModel, showMyReviewsViewModel);

        ShowMyReviewsInteractor showMyReviewsInteractor = new ShowMyReviewsInteractor(
                reviewsDataAccessObject, showMyReviewsPresenter);

        return new ShowMyReviewsController(showMyReviewsInteractor);
    }

    //TODO：创建Controller的Methods
    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
                                                        SearchDataAccessInterface searchDataAccessObject) {
        SearchOutputBoundary searchPresenter = new SearchPresenter(searchViewModel);
        ResponseFactory responseFactory = new NewResponseFactory();
        SearchInteractor searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter, responseFactory);

        SearchController searchController = new SearchController(searchInteractor);

        return searchController;

    }
}