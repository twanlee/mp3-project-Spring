package com.meo.mp3.services;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.response.PlaylistResponse;

import java.util.List;

public interface PlaylistService extends IService<Playlist> {
    List<Playlist> findPlaylistsByUserId(Long userId);
    Playlist createPlaylist(Long userId, Playlist playlist);
    Playlist addSongToPlaylist(Long playlistId, Long songId);
    Playlist removeSongFromPlaylist(Long playlistId, Long songId);
    List<Playlist> getTop9PlaylistByLikes();
    List<Playlist> getTop9PlaylistByViews();
    PlaylistResponse convertToResponse(Playlist playlist);
    List<PlaylistResponse> convertToListResponse(List<Playlist> playlists);
}
