<%@ page import="java.util.Enumeration" %>
<%
    response.setHeader( "Cache-Control", "no-cache" );
    response.setHeader( "Pragma", "no-cache" );
    response.setDateHeader( "Expires", -1 );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Pubanatomy.yanggang</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style type="text/css" media="screen">
        html, body {
            width: 100%;
            height: 100%;
        }

        body {
            margin: 0;
            padding: 0;
            overflow: hidden;
            text-align: center;
            background-color: #000000;
        }

        object:focus {
            outline: none;
        }

        #plzInstallFlash {
            display: none;
        }
    </style>
    <script type="text/javascript" src="swfobject.js"></script>
    <script type="text/javascript">
        var swfVersionStr = "12.0.0";
        var xiSwfUrlStr = "expressInstall.swf";

        var flashvars = {};
        <%
                Enumeration paramNames = request.getParameterNames();
                while(paramNames.hasMoreElements()){
                      String paramName = (String)paramNames.nextElement();
                      String paramValue = request.getParameter(  paramName );
        %>
        flashvars.<%=paramName%> = "<%=paramValue%>";
        <%
                }
        %>
        flashvars.sessionid = "<%=request.getSession().getId()%>";
        flashvars.pageurl = "<%=request.getRequestURL().toString()%>";

        var params = {};
        params.quality = "high";
        params.bgcolor = "#000000";
        params.allowscriptaccess = "sameDomain";
        params.allowFullScreenInteractive = "true";
        params.renderMode = "gpu";
        params.wmode = "direct";
        var attributes = {};
        attributes.id = "pubanatomy.app.pubanatomy.desktop.web.integrate.yanggang.swf.mxml-0.3-SNAPSHOT";
        attributes.name = "pubanatomy.app.pubanatomy.desktop.web.integrate.yanggang.swf.mxml-0.3-SNAPSHOT";
        attributes.align = "middle";
        swfobject.embedSWF(
                "pubanatomy.app.pubanatomy.desktop.web.integrate.yanggang.swf.mxml-0.3-SNAPSHOT.swf", "plzInstallFlash",
                "100%", "100%",
                swfVersionStr, xiSwfUrlStr,
                flashvars, params, attributes );
        swfobject.createCSS( "#plzInstallFlash", "display:block;text-align:left;" );
    </script>
</head>
<body onload="document.getElementById( 'pubanatomy.app.pubanatomy.desktop.web.integrate.yanggang.swf.mxml-0.3-SNAPSHOT' ).focus();" onmouseover="document.getElementById( 'pubanatomy.app.pubanatomy.desktop.web.integrate.yanggang.swf.mxml-0.3-SNAPSHOT' ).focus();" scroll="no">
<div id="plzInstallFlash" style="color:#ffffff">
    <p>
        PubAnatomy requires Adobe Flash Player version
        12.0.0 or greater, click on the icon below to install:
    </p>
    <script type="text/javascript">
        var pageHost = ((document.location.protocol == "https:")?"https://":"http://");
        document.write( "<a href='http://www.adobe.com/go/getflashplayer'><img src='" + pageHost +
                        "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" );
    </script>
</div>

<noscript>
    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="pubanatomy.app.pubanatomy.desktop.web.integrate.yanggang.swf.mxml-0.3-SNAPSHOT">
        <param name="movie" value="pubanatomy.app.pubanatomy.desktop.web.integrate.yanggang.swf.mxml-0.3-SNAPSHOT.swf"/>
        <param name="quality" value="high"/>
        <param name="bgcolor" value="#000000"/>
        <param name="allowScriptAccess" value="sameDomain"/>
        <param name="allowFullScreenInteractive" value="true"/>
        <param name="renderMode" value="gpu"/>
        <param name="wmode" value="direct"/>
        <!--[if !IE]>-->
        <object type="application/x-shockwave-flash" data="pubanatomy.app.pubanatomy.desktop.web.integrate.yanggang.swf.mxml-0.3-SNAPSHOT.swf" width="100%" height="100%">
            <param name="quality" value="high"/>
            <param name="bgcolor" value="#000000"/>
            <param name="allowScriptAccess" value="sameDomain"/>
            <param name="allowFullScreenInteractive" value="true"/>
            <param name="renderMode" value="gpu"/>
            <param name="wmode" value="direct"/>
            <!--<![endif]-->
            <!--[if gte IE 6]>-->
            <p>
                Either scripts and active content are not permitted to run or Adobe Flash Player
                PubAnatomy requires Adobe Flash Player version
                12.0.0 or greater, click on the icon below to install:
            </p>
            <!--<![endif]-->
            <a href="http://www.adobe.com/go/getflashplayer">
                <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
                     alt="Get Adobe Flash Player"/>
            </a>
            <!--[if !IE]>-->
        </object>
        <!--<![endif]-->
    </object>
</noscript>
</body>
</html>
