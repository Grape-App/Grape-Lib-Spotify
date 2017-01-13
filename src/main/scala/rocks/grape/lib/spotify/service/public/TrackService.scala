package rocks.grape.lib.spotify.service.public

import com.google.inject.Inject
import play.api.Configuration
import rocks.grape.lib.commons.ConfigHelper
import rocks.grape.lib.commons.ServiceResult.ServiceResult
import rocks.grape.lib.spotify.models.Track
import rocks.grape.lib.spotify.utils.WSLoggingClient

class TrackService @Inject()(
    wsLog: WSLoggingClient,
    val config: Configuration)()
  extends ConfigHelper {

    private val getTrackUrl: String = configString("services.spotify.track.getTrackUrl")

    /**
     * Get Track from Spotify by id
     * @param trackId the tracks id
     * @return Track or ServiceError as Future
     */
    def getTrack(trackId: String): ServiceResult[Track] = wsLog
      .get[Track](String.format(getTrackUrl, trackId))
}
