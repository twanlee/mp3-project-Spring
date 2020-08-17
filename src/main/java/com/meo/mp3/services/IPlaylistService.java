package com.meo.mp3.services;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;

import java.util.List;

public interface IPlaylistService extends IService<Playlist> {
    List<Playlist> findPlaylistsByUserId(Long userId);
    Playlist createPlaylist(Long userId, Playlist playlist);
    Playlist addSongToPlaylist(Long playlistId, Long songId);
    Playlist removeSongFromPlaylist(Long playlistId, Long songId);
    List<Playlist> getTop10PlaylistByLikes();
    List<Playlist> getTop10PlaylistByViews();
}
