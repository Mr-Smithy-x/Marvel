package com.smith.ufc.comic;

import com.smith.ufc.data.base.Repository;
import com.smith.ufc.data.models.MarvelComic;
import com.smith.ufc.data.service.MarvelComicDataSource;

/**
 * Created by Charlton on 10/31/17.
 */

public interface ComicRepository extends Repository<MarvelComic, ComicSpecification>, MarvelComicDataSource {
}
