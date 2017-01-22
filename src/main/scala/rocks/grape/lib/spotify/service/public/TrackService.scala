package rocks.grape.lib.spotify.service.public

import com.google.inject.Inject
import rocks.grape.lib.commons.ServiceResult.ServiceResult
import rocks.grape.lib.spotify.models.Track
import rocks.grape.lib.spotify.service.SpotifyService
import rocks.grape.lib.spotify.utils.WSLoggingClient

class TrackService @Inject()(wsLog: WSLoggingClient)() extends SpotifyService {

  private val getTrackUrl: String = spotifyBaseUrl + "/tracks/%s"
  private val getTracksUrl: String = spotifyBaseUrl + "/tracks?ids=%s"


  /**
   * Get a Track
   * Get Spotify catalog information for a single track identified by its unique Spotify ID.
   * [[https://developer.spotify.com/web-api/get-track/ Spotify Api Doc]]
   *
   * @param trackId The Spotify ID for the track.
   * @return Track or ServiceError as Future
   */
  def getTrack(trackId: String): ServiceResult[Track] = wsLog.get[Track](String.format(getTrackUrl, trackId))


  /**
   * Get Several Tracks
   * Get Spotify catalog information for a single track identified by its unique Spotify ID.
   * [[https://developer.spotify.com/web-api/get-several-tracks/ Spotify Api Doc]]
   *
   * @param trackIds A list of the Spotify IDs for the tracks. Maximum: 50 IDs.
   * @return Tracks or ServiceError as Future
   */
  def getTracks(trackIds: Seq[String]): ServiceResult[Seq[Track]] =
    wsLog.get[Seq[Track]](String.format(getTracksUrl, trackIds.mkString(",")))(Track.readsSeq)
}
