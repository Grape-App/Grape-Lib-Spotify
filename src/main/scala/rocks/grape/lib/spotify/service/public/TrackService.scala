package rocks.grape.lib.spotify.service.public

import com.google.inject.Inject
import rocks.grape.lib.commons.ServiceResult.ServiceResult
import rocks.grape.lib.spotify.models.Track
import rocks.grape.lib.spotify.utils.WSLoggingClient

class TrackService @Inject()(wsLog: WSLoggingClient)() {

  private val getTrackUrl: String = "https://api.spotify.com/v1/tracks/%s"

  /**
   * Get Track from Spotify by id
   * @param trackId the tracks id
   * @return Track or ServiceError as Future
   */
  def getTrack(trackId: String): ServiceResult[Track] = wsLog.get[Track](String.format(getTrackUrl, trackId))
}
