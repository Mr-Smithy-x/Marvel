package com.smith.ufc;

import com.smith.ufc.character.presenters.CharacterViewPagerPresenter;
import com.smith.ufc.data.models.MarvelCharacter;
import com.smith.ufc.data.models.MarvelData;
import com.smith.ufc.data.models.MarvelResponse;
import com.smith.ufc.data.models.verbose.MarvelComicList;
import com.smith.ufc.data.models.verbose.Thumbnail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.times;

/**
 * Created by Charlton on 10/31/17.
 */

public class ComicViewPagerPresenterTest {


    private static final String TITLE = "title";
    
    private static final String NO_COMIC = "No Comics";

    @Mock
    private CharacterViewPagerPresenter.CharacterGenericView view;

    private CharacterViewPagerPresenter adapter;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void checkViewCallBack() {
        adapter = new CharacterViewPagerPresenter();
        MarvelCharacter marvelCharacter = new MarvelCharacter();
        marvelCharacter.setName(TITLE);
        Thumbnail thumbnail = new Thumbnail();
        String s = thumbnail.toString();
        marvelCharacter.setThumbnail(thumbnail);
        adapter.setData(Arrays.asList(marvelCharacter));
        adapter.onBindRepositoryRowViewAtPosition(0, view);


        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).setTitle(TITLE);
        inOrder.verify(view, times(1)).setDescription(NO_COMIC);
        inOrder.verify(view, times(1)).setImage(s);

    }


    @Test
    public void failIfListIsEmpty() {
        adapter = new CharacterViewPagerPresenter();
        MarvelResponse<MarvelData<MarvelComicList>> marvelDataMarvelResponse = new MarvelResponse<>(200);
        marvelDataMarvelResponse.data = new MarvelData<>(new MarvelComicList());
        adapter.onBindRepositoryRowViewAtPosition(0, view);


        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).setTitle("");
        inOrder.verify(view, times(1)).setDescription("");
        inOrder.verify(view, times(1)).setImage("");

    }


    @Test
    public void failIfDataNull() {
        adapter = new CharacterViewPagerPresenter();
        MarvelResponse<MarvelData<MarvelComicList>> marvelDataMarvelResponse = new MarvelResponse<>(200);
        marvelDataMarvelResponse.data = new MarvelData<>(new MarvelComicList());
        adapter.setData(Arrays.asList(new MarvelCharacter()));
        adapter.onBindRepositoryRowViewAtPosition(0, view);


        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).setTitle(null);
        inOrder.verify(view, times(1)).setDescription(null);
        inOrder.verify(view, times(1)).setImage(null);

    }


}
