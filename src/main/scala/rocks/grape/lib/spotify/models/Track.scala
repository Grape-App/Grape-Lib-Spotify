package rocks.grape.lib.spotify.models

import play.api.libs.json._

case class Track(
    album: Album,
    artists: Seq[Artist],
    available_markets: Seq[String],
    disc_number: Long,
    duration_ms: Long,
    explicit: Boolean,
    external_ids: Option[Map[String, String]],
    external_urls: Option[Map[String, String]],
    href: String,
    id: String,
    name: String,
    popularity: Int,
    is_playable: Option[Boolean],
    preview_url: Option[String],
    track_number: Int,
    uri: String
)

object Track {
  implicit val formats: Format[Track] = Json.format[Track]
  val readsSeq: Reads[Seq[Track]] = new Reads[Seq[Track]] {
    def reads(json: JsValue): JsResult[Seq[Track]] = JsSuccess((json \ "tracks").as[Seq[Track]])
  }
}
