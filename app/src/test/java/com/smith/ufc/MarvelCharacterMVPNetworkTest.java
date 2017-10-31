package com.smith.ufc;

import com.smith.ufc.character.CharacterContract;
import com.smith.ufc.data.repositories.character.CharacterRepository;
import com.smith.ufc.character.presenters.MarvelCharacterPresenter;
import com.smith.ufc.data.models.verbose.MarvelCharacterList;
import com.smith.ufc.data.models.MarvelData;
import com.smith.ufc.data.models.MarvelResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * MarvelComic local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MarvelCharacterMVPNetworkTest {

    @Mock
    private CharacterRepository marvelDataSource;

    @Mock
    private CharacterContract.Interactor marvelInteractor;

    @Mock
    private CharacterContract.View view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {
        MarvelResponse<MarvelData<MarvelCharacterList>> marvelResponse = new MarvelResponse<>(200);
        marvelResponse.data = new MarvelData<>();
        marvelResponse.data.results = new MarvelCharacterList();
        when(marvelDataSource.getCharacters())
                .thenReturn(Observable.just(marvelResponse));


        MarvelCharacterPresenter mainPresenter = new MarvelCharacterPresenter(
                this.marvelDataSource,
                this.marvelInteractor,
                this.view
        );

        mainPresenter.getLatest();


        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).onFetchDataSuccess(marvelResponse.data.results);
        verify(view, times(1)).onFetchDataCompleted();
    }


    @Test
    public void fetchErrorShouldReturnErrorToView() {

        Exception exception = new Exception();

        when(marvelDataSource.getCharacters())
                .thenReturn(Observable.error(exception));


        MarvelCharacterPresenter mainPresenter = new MarvelCharacterPresenter(
                this.marvelDataSource,
                this.marvelInteractor,
                this.view
        );

        mainPresenter.getLatest();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).onFetchDataError(exception);
        verify(view, never()).onFetchDataCompleted();
    }
}