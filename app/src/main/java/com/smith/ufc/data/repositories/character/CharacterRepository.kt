package com.smith.ufc.data.repositories.character

import com.smith.ufc.data.base.BaseRepository
import com.smith.ufc.data.models.MarvelCharacter
import com.smith.ufc.data.service.MarvelCharacterDataSource

/**
 * Created by Charlton on 10/31/17.
 */

interface CharacterRepository : BaseRepository<MarvelCharacter, CharacterSpecification>, MarvelCharacterDataSource
