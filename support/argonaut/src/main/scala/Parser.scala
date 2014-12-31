package jawn
package support.argonaut

import scala.collection.mutable
import argonaut._

object Parser extends SupportParser[Json] {
  implicit val facade: Facade[Json] =
    new Facade[Json] {
      def jnull() = Json.jNull
      def jfalse() = Json.jFalse
      def jtrue() = Json.jTrue
      def jnum(s: String) = Json.jNumberOrNull(java.lang.Double.parseDouble(s))
      def jint(s: String) = {
        if(s.length <= 18){
          Json.jNumber(JsonLong(java.lang.Long.parseLong(s)))
        }else{
          val n = BigDecimal(s)
          if(n.isValidLong){
            Json.jNumber(JsonLong(n.longValue()))
          }else{
            Json.jNumber(JsonBigDecimal(n))
          }
        }
      }
      def jstring(s: String) = Json.jString(s)

      def singleContext() = new FContext[Json] {
        var value: Json = null
        def add(s: String) { value = jstring(s) }
        def add(v: Json) { value = v }
        def finish: Json = value
        def isObj: Boolean = false
      }

      def arrayContext() = new FContext[Json] {
        val vs = mutable.ListBuffer.empty[Json]
        def add(s: String) { vs += jstring(s) }
        def add(v: Json) { vs += v }
        def finish: Json = Json.jArray(vs.toList)
        def isObj: Boolean = false
      }

      def objectContext() = new FContext[Json] {
        var key: String = null
        var vs = JsonObject.empty
        def add(s: String): Unit =
          if (key == null) { key = s } else { vs = vs + (key, jstring(s)); key = null }
        def add(v: Json): Unit =
        { vs = vs + (key, v); key = null }
        def finish = Json.jObject(vs)
        def isObj = true
      }
    }
}
