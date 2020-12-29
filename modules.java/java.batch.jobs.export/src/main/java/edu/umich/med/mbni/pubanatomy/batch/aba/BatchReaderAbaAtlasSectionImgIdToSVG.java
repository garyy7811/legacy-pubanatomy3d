package edu.umich.med.mbni.pubanatomy.batch.aba;

import edu.umich.med.mbni.pubanatomy.batch.domain.AbaAtlasSectionImgToStructure;
import edu.umich.med.mbni.pubanatomy.batch.domain.AbaAtlasSectionImgToStructureID;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/7/13
 * Time: 2:39 PM
 */
public class BatchReaderAbaAtlasSectionImgIdToSVG extends AbstractPagingItemReader<AbaAtlasSectionImgToStructure>
        implements ItemStream{

    private FlatFileItemReader<Integer> flatFileItemReader;

    public FlatFileItemReader<Integer> getFlatFileItemReader(){
        return flatFileItemReader;
    }

    public void setFlatFileItemReader( FlatFileItemReader<Integer> flatFileItemReader ){
        this.flatFileItemReader = flatFileItemReader;
    }

    @Override
    public void open( ExecutionContext executionContext ) throws ItemStreamException{
        flatFileItemReader.open( executionContext );
    }

    @Override
    public void update( ExecutionContext executionContext ) throws ItemStreamException{
        flatFileItemReader.update( executionContext );
    }

    @Override
    public void close() throws ItemStreamException{
        flatFileItemReader.close();
    }


    @Override
    protected void doReadPage(){
        if( results == null ){
            results = new CopyOnWriteArrayList<>();
        }
        else{
            results.clear();
        }
        CopyOnWriteArrayList<AbaAtlasSectionImgToStructure> pg = null;
        while( true ){
            pg = nextAbaAtlasSectionImgToStructures();
            if( pg.size() > 0 ){
                break;
            }
        }

        results.addAll( pg );
    }

    private CopyOnWriteArrayList<AbaAtlasSectionImgToStructure> nextAbaAtlasSectionImgToStructures(){

        CopyOnWriteArrayList<AbaAtlasSectionImgToStructure> rt = new CopyOnWriteArrayList<>();

        long atlasSecImgId = -1;
        try{
            atlasSecImgId = flatFileItemReader.read();
            if( flatFileItemReader == null ){
                return rt;
            }
        }
        catch( Exception e ){
            if( logger.isDebugEnabled() ){
                logger.debug( this, e );
            }
            throw new IllegalArgumentException( e.getMessage() );
        }

        String currentPageUrl = "http://api.brain-map.org/api/v2/svg/" + atlasSecImgId;
        InputStream is = null;
        try{
            is = Channels.newInputStream( Channels.newChannel( new URL( currentPageUrl ).openStream() ) );
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse( is );
            NodeList paths = document.getElementsByTagName( "path" );

            for( int x = 0, size = paths.getLength(); x < size; x++ ){
                String structureId = paths.item( x ).getAttributes().getNamedItem( "structure_id" ).getNodeValue();
                rt.add( new AbaAtlasSectionImgToStructure( new AbaAtlasSectionImgToStructureID( atlasSecImgId,
                        Integer.parseInt( structureId ) ), null ) );
            }

        }
        catch( Exception e ){
            if( logger.isDebugEnabled() ){
                logger.debug( this, e );
            }
            throw new IllegalArgumentException( e.getMessage() );
        }

        if( rt.size() == 0 ){
            if( logger.isDebugEnabled() ){
                logger.debug( "N/A:" + currentPageUrl );
            }
        }
        return rt;
    }

    @Override
    protected void doJumpToPage( int itemIndex ){
    }
}
