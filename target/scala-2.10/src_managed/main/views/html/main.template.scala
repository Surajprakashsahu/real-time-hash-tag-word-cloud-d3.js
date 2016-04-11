
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
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*9.59*/routes/*9.65*/.Assets.at("images/favicon.png"))),format.raw/*9.97*/("""">
        <link href=""""),_display_(Seq[Any](/*10.22*/routes/*10.28*/.Assets.at("stylesheets/style.css"))),format.raw/*10.63*/("""" rel="stylesheet" type="text/css">
        <script src=""""),_display_(Seq[Any](/*11.23*/routes/*11.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*11.74*/("""" type="text/javascript"></script>
    </head>

<body>

<div id="wrapper"> 

  <div id="header"> 

    <div class="top_banner">
      <h1>Your Application will start in 30 seconds</h1>
      <!--<p>Output is</p>-->
    </div>

  </div>

  <div id="page_content">

    
      <div class="common_content">
        <h2>Output can be seen on Terminal</h2>
        </div>

    <div class="clear"></div>
    
    
  </div>

</div>
</body></html>
"""))}
    }
    
    def render(title:String,content:Html): play.api.templates.HtmlFormat.Appendable = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Feb 26 08:06:41 IST 2016
                    SOURCE: E:/Project/KAFKA/twitterStreaming/Play-Spark-Scala-master/app/views/main.scala.html
                    HASH: 2eea38a0891dedf40ad9524c551f1214505518e0
                    MATRIX: 560->1|684->31|772->84|798->89|895->151|909->157|964->191|1060->252|1074->258|1127->290|1187->314|1202->320|1259->355|1353->413|1368->419|1435->464
                    LINES: 19->1|22->1|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|31->10|32->11|32->11|32->11
                    -- GENERATED --
                */
            