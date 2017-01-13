package rocks.grape.lib.spotify.service.public

import com.google.inject.Inject
import play.api.Configuration
import rocks.grape.lib.commons.ConfigHelper
import rocks.grape.lib.commons.ServiceResult.ServiceResult
import rocks.grape.lib.spotify.models.{Album, Artist}
import rocks.grape.lib.spotify.utils.WSLoggingClient

class ArtistService @Inject()(wsLog: WSLoggingClient)() {

  private val getArtistUrl: String = "https://api.spotify.com/v1/artists/%s"
  private val getArtistsUrl: String = "https://api.spotify.com/v1/artists?ids=%s"
  private val getArtistRelatedArtistsUrl: String = "https://api.spotify.com/v1/artists/%s/related-artists?country=%s"

  /**
   * Get Artist from Spotify by id
   * @param artistId the artist's id
   * @return Artist or ServiceError as Future
   */
  def getArtist(artistId: String): ServiceResult[Artist] = wsLog.get[Artist](String.format(getArtistUrl, artistId))

  /**
   * Get Artists from Spotify by ids
   * @param artistIds the artists ids
   * @return List of Artists or ServiceError as Future
   */
  def getArtists(artistIds: Seq[String]): ServiceResult[Seq[Artist]] =
    wsLog.get[Seq[Artist]](String.format(getArtistsUrl, artistIds.mkString(",")))(Artist.readsSeq)

  /**
   * Get Albums of an Artist
   * @param artistId the artist's id
   * @return List of Albums or ServiceError as Future
   */
  def getArtistAlbums(artistId: String): ServiceResult[Seq[Album]] =
    wsLog.get[Seq[Album]](String.format(getArtistsUrl, artistId))(Album.readsSeq)

//  def getArtistTopTracks(artistId: String): ServiceResult[Seq[SpotifyTrack]] = {}
//

  /**
   * Get related Artists for artistId from Spotify
   * @param artistId the artist id
   * @param country an ISO 3166-1 alpha-2 country code https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
   * @return List of Artists or ServiceError as Future
   */
  def getArtistRelatedArtists(artistId: String, country: String): ServiceResult[Seq[Artist]] = wsLog
    .get[Seq[Artist]](String.format(getArtistRelatedArtistsUrl, artistId, country))(Artist.readsSeq)
}
