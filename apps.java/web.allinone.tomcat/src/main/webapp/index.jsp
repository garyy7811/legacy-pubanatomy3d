<%
    response.setHeader( "Cache-Control", "no-cache" );
    response.setHeader( "Pragma", "no-cache" );
    response.setDateHeader( "Expires", 0 );
%>
<html lang="en">
<style type="text/css">
    <!--
    .style1 {
        color: #3300FF;
        font-weight: bold;
        font-size: 18px;
    }

    .style3 {
        color: #000000;
        font-weight: bold;
    }

    .style8 {
        color: #CC0000
    }

    -->
</style>

<table width="790" border="0" align="center" cellpadding="5">
    <tr align="center">
        <td><img src="pubanatomytxt.png"><br>
            <span class="style1">Integrated Exploration of Biomedical Literature and Data</span></td>
    </tr>
    <tr align="center">
        <td><a href="main"><img src="icon.png" width="174" height="149"></a></td>
    </tr>
    <tr>
        <td><p align="justify" class="style3 style5">The <a href="main">PubAnatomy 3D</a> project is NOT an extension of the
            <a href="http://brainarray.mbni.med.umich.edu/Brainarray/prototype/PubAnatomy/">original
                PubAnatomy</a>.
            The complexity of the structure and function of brain makes text- or even 2D-graphics based exploration of
            high throughput data sets and literature related to brain ineffective. We hope to build a web-based system
            to facilitate the understanding of brain structures, circuits, gene expression levels and disease by taking
            advantage of large data sets in a centralized database. We choose to use the Adobe Flash Platform for 3D
            development due to its cross browser/platform support. The newly released Adobe released Flash Player and
            AIR
            support the Stage3D API, which is a set of low-level GPU-accelerated APIs enabling advanced 2D and 3D
            capabilities.
        </p>

            <p align="justify" class="style3 style5">We build brain 3D structure models using voxel-level data provided
                by the Allen Brain Institute. When requested by users, 3D models will be loaded from server to the Flash
                player in users' browser.
                We believe this is the first implementation of interactive 3D biological structure display in Flash and
                we
                plan to integrate this implementation with gene expression, function annotation and literature data to
                help
                the understanding of high throughput neurobiology data in the context of brain structure and
                functions. </p>

            <p align="justify" class="style3 style5">PubAnatomy3D is still under development. We are in the process of
                integrating full PubAnatomy literature and
                database exploration capabilities to PubAnatomy 3D. Please send your suggestions and comments to
                brainarray@umich.edu or <a href="https://groups.google.com/forum/?fromgroups=#!forum/pubanatomy3d">Pubanatomy3d
                    forum</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="User%20Guide%20Pubanatomy3D_Nov2013.pdf">Click here to Download PubAnatomy User Guide ( pdf file
                )</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="https://groups.google.com/forum/?fromgroups=#!topic/pubanatomy3d/0jXBe5HUh9I">
                Click here to See Important Known issues in Pubanatomy3d forum</a></p>

            <p align="justify" class="style3 style5">More Video Tutorials are coming soon ... </p>
        </td>
    </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="5">
    <tr>
        <td width="100%" align="center">
            <iframe width="1280" height="720" src="//www.youtube.com/embed/FMJZOGCFRX8?rel=0" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <iframe width="1280" height="720" src="//www.youtube.com/embed/NbDeH-7xP_4?rel=0" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <iframe width="960" height="720" src="//www.youtube.com/embed/j9qm78WyVRw?rel=0" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>
    <tr>
        <td>
            Below are old videos for V0.2:
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <iframe width="800" height="600" src="http://www.youtube.com/embed/-AQUcg_i61s" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <iframe width="800" height="600" src="http://www.youtube.com/embed/g7xvcbrVyao" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <iframe width="800" height="600" src="http://www.youtube.com/embed/T405Pi5atcQ" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <iframe width="800" height="600" src="http://www.youtube.com/embed/7m7F5iPcy7w" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <iframe width="800" height="600" src="http://www.youtube.com/embed/g1SiQUDk-hg" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <iframe width="800" height="600" src="http://www.youtube.com/embed/_pKt_Ei_bMM" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <iframe width="800" height="600" src="http://www.youtube.com/embed/OpuSfvg56g0" frameborder="0"
                    allowfullscreen></iframe>
        </td>
    </tr>

    <tr>
        <td>
            Major Development Technologies:
            <a href="http://www.adobe.com/products/flashruntimes.html">Adobe Flash</a>,
            <a href="http://flex.apache.org">Apache Flex</a>,
            <a href="http://away3d.com/">Away3D</a>,
            <a href="http://lucene.apache.org/solr/">Apache Solr</a>,
            <a href="http://spring.io">Java Spring</a>,
            <a href="http://www.vtk.org/">Visualization Toolkit (VTK)</a>,
            <a href="http://www.mongodb.org/">MongoDB</a>
        </td>
    </tr>
</table>
<p align="center">&nbsp;</p>
</html>