package rocks.grape.lib.spotify.service.public

import com.google.inject.Inject
import rocks.grape.lib.commons.ServiceResult.ServiceResult
import rocks.grape.lib.spotify.models.{Album, Artist, Track}
import rocks.grape.lib.spotify.service.SpotifyService
import rocks.grape.lib.spotify.utils.WSLoggingClient

class ArtistService @Inject()(wsLog: WSLoggingClient)() extends SpotifyService {

  private val getArtistUrl: String = spotifyBaseUrl + "/artists/%s"
  private val getArtistsUrl: String = spotifyBaseUrl + "/artists?ids=%s"
  private val getArtistAlbumsUrl: String = spotifyBaseUrl + "/artists/%s/albums"
  private val getArtistTopTracksUrl: String = spotifyBaseUrl + "/artists/%s/top-tracks"
  private val getArtistRelatedArtistsUrl: String = spotifyBaseUrl + "/artists/%s/related-artists?country=%s"


  /**
   * Get an Artist
   * Get Spotify catalog information for a single artist identified by their unique Spotify ID.
   * [[https://developer.spotify.com/web-api/get-artist/ Spotify Api Doc]]
   *
   * @param artistId The Spotify ID for the artist.
   * @return Artist or ServiceError as Future
   */
  def getArtist(artistId: String): ServiceResult[Artist] = wsLog.get[Artist](String.format(getArtistUrl, artistId))


  /**
   * Get Several Artists
   * Get Spotify catalog information for several artists based on their Spotify IDs.
   * [[https://developer.spotify.com/web-api/get-several-artists/ Spotify Api Doc]]
   *
   * @param artistIds A list of the Spotify IDs for the artists. Maximum: 50 IDs.
   * @return List of Artists or ServiceError as Future
   */
  def getArtists(artistIds: Seq[String]): ServiceResult[Seq[Artist]] =
    wsLog.get[Seq[Artist]](String.format(getArtistsUrl, artistIds.mkString(",")))(Artist.readsSeq)


  /**
   * Get Albums of an Artist
   * Get Spotify catalog information about an artist’s albums.
   * [[https://developer.spotify.com/web-api/get-artists-albums/ Spotify Api Doc]]
   *
   * @param artistId The Spotify ID for the artist.
   * @return List of Albums or ServiceError as Future
   */
  def getArtistAlbums(artistId: String): ServiceResult[Seq[Album]] =
    wsLog.get[Seq[Album]](String.format(getArtistAlbumsUrl, artistId))(Album.readsSeq)


  /**
   * Get an Artist’s Top Tracks
   * Get Spotify catalog information about an artist’s top tracks by country.
   * [[https://developer.spotify.com/web-api/get-artists-top-tracks/ Spotify Api Doc]]
   *
   * @param artistId The Spotify ID for the artist.
   * @return List of Tracks or ServiceError as Future
   */
  def getArtistTopTracks(artistId: String): ServiceResult[Seq[Track]] =
    wsLog.get[Seq[Track]](String.format(getArtistTopTracksUrl, artistId))(Track.readsSeq)


  /**
   * Get an Artist’s Related Artists
   * Get Spotify catalog information about artists similar to a given artist.
   * Similarity is based on analysis of the Spotify community’s listening history.
   * [[https://developer.spotify.com/web-api/get-related-artists/ Spotify Api Doc]]
   *
   * @param artistId The Spotify ID for the artist.
   * @param country  an ISO 3166-1 alpha-2 country code https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
   * @return List of Artists or ServiceError as Future
   */
  def getArtistRelatedArtists(artistId: String, country: String): ServiceResult[Seq[Artist]] = wsLog
    .get[Seq[Artist]](String.format(getArtistRelatedArtistsUrl, artistId, country))(Artist.readsSeq)
}
