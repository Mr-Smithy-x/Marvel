package com.smith.ufc.data.repositories.comic

import com.smith.ufc.data.base.BaseRepository
import com.smith.ufc.data.models.MarvelComic
import com.smith.ufc.data.service.MarvelComicDataSource

/**
 * Created by Charlton on 10/31/17.
 */

interface ComicRepository : BaseRepository<MarvelComic, ComicSpecification>, MarvelComicDataSource
