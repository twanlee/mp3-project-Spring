package com.meo.mp3.services;

import com.meo.mp3.models.songs.Song;

public interface ILikeService {
    Song likeASong(Long songId, Long userId);

}
