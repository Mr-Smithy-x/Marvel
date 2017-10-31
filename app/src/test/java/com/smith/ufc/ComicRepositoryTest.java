package com.smith.ufc;

import com.smith.ufc.character.CharacterContract;
import com.smith.ufc.character.CharacterGenericAdapter;
import com.smith.ufc.character.CharacterRepository;
import com.smith.ufc.character.MarvelCharacterPresenter;
import com.smith.ufc.comic.ComicRepository;
import com.smith.ufc.comic.ComicRepositoryImpl;
import com.smith.ufc.data.models.MarvelCharacter;
import com.smith.ufc.data.models.MarvelData;
import com.smith.ufc.data.models.MarvelResponse;
import com.smith.ufc.data.models.verbose.MarvelCharacterList;
import com.smith.ufc.data.models.verbose.MarvelComicList;
import com.smith.ufc.data.models.verbose.Thumbnail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import io.reactivex.Observable;
import io.realm.Realm;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Charlton on 10/31/17.
 */

public class ComicRepositoryTest {


    @Mock
    private CharacterGenericAdapter.CharacterGenericView view;

    private CharacterGenericAdapter adapter;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    static String TITLE = "title";
    static String COMIC = "title";
    static String IMAGE = "image";


    @Test
    public void checkViewCallBack() {
        adapter = new CharacterGenericAdapter();
        MarvelCharacter marvelCharacter = new MarvelCharacter();
        marvelCharacter.setName(TITLE);
        Thumbnail thumbnail = new Thumbnail();
        String s = thumbnail.toString();
        marvelCharacter.setThumbnail(thumbnail);
        adapter.setData(Arrays.asList(marvelCharacter));
        adapter.onBindRepositoryRowViewAtPosition(0,view);


        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).setTitle(TITLE);
        inOrder.verify(view, times(1)).setDescription("No Comics");
        inOrder.verify(view, times(1)).setImage(s);

    }



    @Test
    public void failIfListIsEmpty() {
        adapter = new CharacterGenericAdapter();
        MarvelResponse<MarvelData<MarvelComicList>> marvelDataMarvelResponse = new MarvelResponse<>(200);
        marvelDataMarvelResponse.data = new MarvelData<>(new MarvelComicList());
        adapter.onBindRepositoryRowViewAtPosition(0,view);




        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).setTitle("");
        inOrder.verify(view, times(1)).setDescription("");
        inOrder.verify(view, times(1)).setImage("");

    }



    @Test
    public void failIfDataNull() {
        adapter = new CharacterGenericAdapter();
        MarvelResponse<MarvelData<MarvelComicList>> marvelDataMarvelResponse = new MarvelResponse<>(200);
        marvelDataMarvelResponse.data = new MarvelData<>(new MarvelComicList());
        adapter.setData(Arrays.asList(new MarvelCharacter()));
        adapter.onBindRepositoryRowViewAtPosition(0,view);





        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).setTitle(null);
        inOrder.verify(view, times(1)).setDescription(null);
        inOrder.verify(view, times(1)).setImage(null);

    }


}
