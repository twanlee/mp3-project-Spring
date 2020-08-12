package com.meo.mp3.services;

import com.meo.mp3.models.songs.Playlist;

import java.util.List;

public interface IPlaylistService extends IService<Playlist> {
    List<Playlist> findPlaylistsByUserId(Long userId);
}
