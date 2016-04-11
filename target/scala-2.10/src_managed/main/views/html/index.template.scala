
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(Seq[Any](/*3.2*/main("Welcome to Spark")/*3.26*/ {_display_(Seq[Any](format.raw/*3.28*/("""

    """),_display_(Seq[Any](/*5.6*/message)),format.raw/*5.13*/("""

""")))})),format.raw/*7.2*/("""
"""))}
    }
    
    def render(message:String): play.api.templates.HtmlFormat.Appendable = apply(message)
    
    def f:((String) => play.api.templates.HtmlFormat.Appendable) = (message) => apply(message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Feb 26 08:06:41 IST 2016
                    SOURCE: E:/Project/KAFKA/twitterStreaming/Play-Spark-Scala-master/app/views/index.scala.html
                    HASH: fb00db94afa89332e323c28acdf4eb02987758e5
                    MATRIX: 556->1|667->18|704->21|736->45|775->47|816->54|844->61|877->64
                    LINES: 19->1|22->1|24->3|24->3|24->3|26->5|26->5|28->7
                    -- GENERATED --
                */
            