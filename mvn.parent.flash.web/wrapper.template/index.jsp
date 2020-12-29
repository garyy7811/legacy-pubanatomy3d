<%@ page import="java.util.Enumeration" %>
<%
    response.setHeader( "Cache-Control", "no-cache" );
    response.setHeader( "Pragma", "no-cache" );
    response.setDateHeader( "Expires", -1 );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>${title}</title>
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
            background-color: ${bgcolor};
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
        var swfVersionStr = "${version_major}.${version_minor}.${version_revision}";
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
        params.bgcolor = "${bgcolor}";
        params.allowscriptaccess = "sameDomain";
        params.allowFullScreenInteractive = "true";
        params.renderMode = "gpu";
        params.wmode = "direct";
        var attributes = {};
        attributes.id = "${swf}";
        attributes.name = "${swf}";
        attributes.align = "middle";
        swfobject.embedSWF(
                "${swf}.swf", "plzInstallFlash",
                "100%", "100%",
                swfVersionStr, xiSwfUrlStr,
                flashvars, params, attributes );
        swfobject.createCSS( "#plzInstallFlash", "display:block;text-align:left;" );
    </script>
</head>
<body onload="document.getElementById( '${swf}' ).focus();" onmouseover="document.getElementById( '${swf}' ).focus();" scroll="no">
<div id="plzInstallFlash" style="color:#ffffff">
    <p>
        PubAnatomy requires Adobe Flash Player version
        ${version_major}.${version_minor}.${version_revision} or greater, click on the icon below to install:
    </p>
    <script type="text/javascript">
        var pageHost = ((document.location.protocol == "https:")?"https://":"http://");
        document.write( "<a href='http://www.adobe.com/go/getflashplayer'><img src='" + pageHost +
                        "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" );
    </script>
</div>

<noscript>
    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="${swf}">
        <param name="movie" value="${swf}.swf"/>
        <param name="quality" value="high"/>
        <param name="bgcolor" value="${bgcolor}"/>
        <param name="allowScriptAccess" value="sameDomain"/>
        <param name="allowFullScreenInteractive" value="true"/>
        <param name="renderMode" value="gpu"/>
        <param name="wmode" value="direct"/>
        <!--[if !IE]>-->
        <object type="application/x-shockwave-flash" data="${swf}.swf" width="${width}" height="${height}">
            <param name="quality" value="high"/>
            <param name="bgcolor" value="${bgcolor}"/>
            <param name="allowScriptAccess" value="sameDomain"/>
            <param name="allowFullScreenInteractive" value="true"/>
            <param name="renderMode" value="gpu"/>
            <param name="wmode" value="direct"/>
            <!--<![endif]-->
            <!--[if gte IE 6]>-->
            <p>
                Either scripts and active content are not permitted to run or Adobe Flash Player
                PubAnatomy requires Adobe Flash Player version
                ${version_major}.${version_minor}.${version_revision} or greater, click on the icon below to install:
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
