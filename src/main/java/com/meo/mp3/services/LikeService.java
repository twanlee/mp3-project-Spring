package com.meo.mp3.services;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;

public interface LikeService {
    Song likeASong(Long songId, Long userId);
    Playlist likeAPlaylist(Long playlistId, Long userId);
}
