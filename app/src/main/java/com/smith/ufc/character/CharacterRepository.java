package com.smith.ufc.character;

import com.smith.ufc.data.base.Repository;
import com.smith.ufc.data.models.MarvelCharacter;
import com.smith.ufc.data.service.MarvelCharacterDataSource;

/**
 * Created by Charlton on 10/31/17.
 */

public interface CharacterRepository extends Repository<MarvelCharacter, CharacterSpecification>, MarvelCharacterDataSource {}
