package rocks.grape.lib.spotify.models

import play.api.libs.json._

case class Album(
    artists: Seq[Artist],
    available_markets: Option[Seq[String]],
    copyrights: Option[Map[String, String]],
    external_ids: Option[Map[String, String]],
    external_urls: Option[Map[String, String]],
    genres: Option[Seq[String]],
    href: String,
    id: String,
    images: Seq[Image],
    label: Option[String],
    name: String,
    popularity: Option[Int],
    release_date: Option[String],
    release_date_precision: Option[String],
    tracks: Option[Seq[Track]],
    uri: String
)

object Album {
  implicit val format: Format[Album] = Json.format[Album]
  val readsSeq: Reads[Seq[Album]] = new Reads[Seq[Album]] {
    def reads(json: JsValue): JsResult[Seq[Album]] = JsSuccess((json \ "albums").as[Seq[Album]])
  }
}
